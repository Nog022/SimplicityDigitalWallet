package com.simplicity.wallet.digital.SimplicityDigitalWallet.service.impl;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.ExtratoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Deposito;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Saque;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Transacao;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.enums.TipoTransacao;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.ContaRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.TransacaoRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.ContaService;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.TransacaoService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaService contaService;

    @Override
    public void transacao(Saque saque) {
        Transacao transacao = new Transacao();
        transacao.setValor(saque.getValor());
        transacao.setDataTransacao(Timestamp.from(Instant.now()));
        transacao.setTipoTransacao(TipoTransacao.SAQUE);
        transacao.setIdContaOrigem(saque.getIdConta());
        transacao.setIdContaDestino(null);
        transacaoRepository.save(transacao);
    }

    @Override
    public void transacao(Deposito deposito, Conta conta) {
        Transacao transacao = new Transacao();
        transacao.setValor(deposito.getValor());
        transacao.setDataTransacao(deposito.getDataTransacao());
        transacao.setTipoTransacao(TipoTransacao.DEPOSITO);
        transacao.setIdContaOrigem(conta);
        transacao.setIdContaDestino(deposito.getIdConta());
        transacaoRepository.save(transacao);
    }

    @Override
    public void transacao(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
        Transacao transacao = new Transacao();
        transacao.setValor(valor);
        transacao.setIdContaOrigem(contaOrigem);
        transacao.setIdContaDestino(contaDestino);
        transacao.setTipoTransacao(TipoTransacao.PIX);
        transacao.setDataTransacao(Timestamp.from(Instant.now()));
        transacaoRepository.save(transacao);
    }

    @Override
    public List<ExtratoDTO> extrato(Long idConta) {// Busca as transações de origem
        List<Transacao> transacoesOrigem = transacaoRepository.findByIdContaOrigemId(idConta);

        // Busca as transações de destino
        List<Transacao> transacoesDestino = transacaoRepository.findByIdContaDestinoId(idConta);

        // Junta as transações de origem e destino
        List<Transacao> todasTransacoes = new ArrayList<>();
        todasTransacoes.addAll(transacoesOrigem);
        todasTransacoes.addAll(transacoesDestino);

        // Mapeia as transações para DTOs
        return todasTransacoes.stream().map(t -> new ExtratoDTO(
                t.getId(),
                t.getDataTransacao(),
                t.getValor(),
                t.getIdContaOrigem() != null ? t.getIdContaOrigem().getNumeroConta() : null,
                t.getIdContaDestino() != null ? t.getIdContaDestino().getNumeroConta()  : null,
                t.getTipoTransacao()
        )).collect(Collectors.toList());
    }

    @Override
    public void transacao(Deposito deposito) {
        Transacao transacao = new Transacao();
        transacao.setValor(deposito.getValor());
        transacao.setDataTransacao(deposito.getDataTransacao());
        transacao.setTipoTransacao(TipoTransacao.DEPOSITO);
        transacao.setIdContaOrigem(deposito.getIdConta());
        transacao.setIdContaDestino(null);
        transacaoRepository.save(transacao);
    }
}
