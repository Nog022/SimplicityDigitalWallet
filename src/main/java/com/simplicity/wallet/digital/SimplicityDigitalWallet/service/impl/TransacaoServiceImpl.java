package com.simplicity.wallet.digital.SimplicityDigitalWallet.service.impl;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Deposito;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Transacao;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.enums.TipoTransacao;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.TransacaoRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Override
    public void transacaoDeposito(Deposito deposito) {
        Transacao transacao = new Transacao();
        transacao.setValor(deposito.getValor());
        transacao.setDataTransacao(deposito.getDataTransacao());
        transacao.setTipoTransacao(TipoTransacao.DEPOSITO);
        transacao.setIdContaOrigem(deposito.getIdConta());
        transacao.setIdContaDestino(null);
        transacaoRepository.save(transacao);
    }
}
