package com.simplicity.wallet.digital.SimplicityDigitalWallet.service;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Deposito;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Transacao;
import org.springframework.stereotype.Service;

@Service
public interface TransacaoService {

    void transacaoDeposito(Deposito deposito);
}
