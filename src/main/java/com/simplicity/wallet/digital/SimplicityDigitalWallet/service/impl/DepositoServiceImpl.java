package com.simplicity.wallet.digital.SimplicityDigitalWallet.service.impl;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.DepositoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.PagarBoletoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Deposito;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.enums.Pago;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.exceptions.ContaNaoEncontradaException;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.ContaRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.DepositoRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.DepositoService;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.TransacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Base64;
import java.util.Random;

@Service
public class DepositoServiceImpl implements DepositoService {

    public static Logger logger = LoggerFactory.getLogger(DepositoServiceImpl.class);

    @Autowired
    private DepositoRepository depositoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransacaoService transacaoService;

    @Override
    public String pagarBoleto(PagarBoletoDTO pagarBoletoDTO) {
        Deposito deposito =  procurarDeposito(pagarBoletoDTO.boleto());
        Conta contaEncontrada = findContaByNumeroConta(pagarBoletoDTO.numeroConta());

        if(deposito != null && contaEncontrada.getSaldo().compareTo(deposito.getValor()) >= 0) {
            if(deposito.getPago() != Pago.TRUE){
                //pegar o valor do boleto e depositar na conta que está no deposito
                BigDecimal valorBoleto = somarValores(deposito.getValor(), deposito.getIdConta());
                deposito.getIdConta().setSaldo(valorBoleto);
                deposito.setPago(Pago.TRUE);
                depositoRepository.save(deposito);

                //retirando valor da conta que vai pagar o boleto
                contaEncontrada.setSaldo(contaEncontrada.getSaldo().subtract(deposito.getValor()));
                contaRepository.save(contaEncontrada);

                transacaoService.transacao(deposito,contaEncontrada);


                return "O valor foi depositado com sucesso!";

            }else{
                return "Esse boleto ja foi pago anteriormente";
            }

        }

        return "Erro ao depositar boleto!";


    }

    @Override
    public String gerarBoleto(DepositoDTO dto) {
        String boleto = salvarDeposito(dto);
        if(boleto !=null){
            logger.info("Boleto gerado: {}", boleto);
            return boleto;
        }
        logger.error("Erro ao salvar depósito");
        return "Erro ao salvar depósito";

    }

    private BigDecimal somarValores(BigDecimal valorDoBoleto, Conta conta) {
        BigDecimal valorBoleto = new BigDecimal(String.valueOf(valorDoBoleto));
        BigDecimal saldoConta = new BigDecimal(String.valueOf(conta.getSaldo()));
        return valorBoleto.add(saldoConta);
    }


    private String salvarDeposito(DepositoDTO dto) {
        Deposito deposito = new Deposito();
        deposito.setBoleto(gerarCodigoBarras());
        deposito.setPago(Pago.FALSE);
        deposito.setValor(dto.valor());
        deposito.setIdConta(findContaByNumeroConta(dto.numeroConta()));
        deposito.setDataTransacao(Timestamp.from(Instant.now()));
        depositoRepository.save(deposito);
        return deposito.getBoleto();

    }


    private static String gerarCodigoBarras() {
        Random random = new Random();
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 44; i++) { // Gera 44 dígitos
            codigo.append(random.nextInt(10)); // Adiciona um número de 0 a 9
        }
        return codigo.toString();
    }

    private Deposito procurarDeposito(String boleto) {
        return depositoRepository.findDepositoByBoleto(boleto);
    }

    private Conta findContaByNumeroConta(Conta conta) {
        return contaRepository.findByNumeroConta(conta.getNumeroConta())
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta com o numero " + conta.getNumeroConta() + " não encontrada."));
    }

    private Conta findContaByNumeroConta(Long numeroConta) {
        return contaRepository.findByNumeroConta(numeroConta)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta com o numero " + numeroConta + " não encontrada."));
    }

    private Deposito findDepositoByNumeroConta(Conta idConta, BigDecimal valor) {
        Deposito deposito = depositoRepository.findDepositoByIdContaAndPagoAndValor(idConta.getId(), Pago.FALSE , valor);


        if(deposito != null){
            deposito.setPago(Pago.TRUE);
            return depositoRepository.save(deposito);

        }

        return null;

    }
}
