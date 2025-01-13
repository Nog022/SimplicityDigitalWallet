package com.simplicity.wallet.digital.SimplicityDigitalWallet.service;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Deposito;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Saque;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Transacao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface TransacaoService {

    void transacao(Saque saque);
    void transacao(Deposito deposito);
    void transacao(Conta contaOrigem, Conta contaDestino, BigDecimal valor);
}
