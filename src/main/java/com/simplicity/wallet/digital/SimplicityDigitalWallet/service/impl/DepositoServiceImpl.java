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
import java.util.Base64;

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
        Long decode = decodeBase64ToLong(pagarBoletoDTO.boleto());
        Conta conta = findContaByNumeroConta(decode);

        if(conta != null){
            BigDecimal valor1 = new BigDecimal(String.valueOf(pagarBoletoDTO.valor()));
            BigDecimal valor2 = new BigDecimal(String.valueOf(conta.getSaldo()));
            BigDecimal soma = valor1.add(valor2);

            conta.setSaldo(soma);
            contaRepository.save(conta);
            Deposito deposito = findDepositoByNumeroConta(conta,pagarBoletoDTO.valor());

            transacaoService.transacao(deposito);


            return "O valor foi depositado com sucesso!";
        }

        return "Erro ao depositar boleto!";


    }

    @Override
    public String gerarBoleto(DepositoDTO dto) {
        //TODO criar um timer para pagamento do boleto, colocar para a data ser colocada diretamente no codigo
        if(salvarDeposito(dto)){
            String encode = encodeLongToBase64(dto.idConta().getNumeroConta());

            logger.info("boleto gerado: {}", encode);

            return encode;
        }
        logger.error("Erro ao salvar depósito");
        return null;

    }


    private boolean salvarDeposito(DepositoDTO dto) {
        Deposito deposito = new Deposito();
        deposito.setPago(Pago.FALSE);
        deposito.setValor(dto.valor());
        deposito.setIdConta(findContaByNumeroConta(dto.idConta()));
        deposito.setDataTransacao(dto.dataTransacao());
        depositoRepository.save(deposito);
        return true;

    }


    // Método para codificar Long em Base64
    public static String encodeLongToBase64(long value) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(value);
        return Base64.getEncoder().encodeToString(buffer.array());
    }

    // Método para decodificar Base64 para Long
    public static Long decodeBase64ToLong(String base64) {
        byte[] bytes = Base64.getDecoder().decode(base64);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        return buffer.getLong();
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
