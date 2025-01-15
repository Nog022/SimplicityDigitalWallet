package com.simplicity.wallet.digital.SimplicityDigitalWallet.service.impl;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.PixDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Pix;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Transacao;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.ContaRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.PixRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.ContaService;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.PixService;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.SecureRandom;

@Service
public class PixServiceImpl implements PixService {

    @Autowired
    private PixRepository pixRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    @Lazy
    private ContaService contaService;

    @Autowired
    private TransacaoService transacaoService;

    @Override
    public Pix criarPix(Conta conta) {
        Pix pix = new Pix();
        pix.setConta(conta);
        pix.setId(gerarChavePix());
        return pixRepository.save(pix);
    }

    @Override
    public String transferenciaPix(PixDTO pix) {
        Conta contaDestino =  findByPix(pix.chavePix());
        Conta contaOrgiem = contaService.buscarConta(pix.numeroContaOrigem()) ;


        if(verificarSaldo(contaOrgiem,pix.valor())){
            contaOrgiem.setSaldo(contaOrgiem.getSaldo().subtract(pix.valor()));
            contaDestino.setSaldo(contaDestino.getSaldo().add(pix.valor()));
            contaRepository.save(contaDestino);
            contaRepository.save(contaOrgiem);

            transacaoService.transacao(contaOrgiem,contaDestino,pix.valor());

            return "Transferencia Pix realizada com sucesso!";
        }
        return "Saldo insuficiente para transferencia!";
    }

    private String gerarChavePix() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom RANDOM = new SecureRandom();
        int tamanhoDaChave = 10;

        if (tamanhoDaChave <= 0) {
            throw new IllegalArgumentException("O comprimento deve ser maior que zero.");
        }

        StringBuilder sb = new StringBuilder(tamanhoDaChave);
        for (int i = 0; i < tamanhoDaChave; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    private boolean verificarSaldo(Conta conta, BigDecimal valor) {
        return conta.getSaldo().compareTo(valor) >= 0;
    }

    public Conta findByPix(String chavePix) {
        Pix pix =  pixRepository.findById(chavePix)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrado"));

        return pix.getConta();



    }
}
