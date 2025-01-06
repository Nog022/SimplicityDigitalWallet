package com.simplicity.wallet.digital.SimplicityDigitalWallet.service.impl;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.exceptions.ContaNaoEncontradaException;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.ContaRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public Conta salvarConta(Conta conta) {
        return contaRepository.save(conta);
    }

    @Override
    public Conta buscarContaPorId(Long id) {
        Optional<Conta> contaOptional = contaRepository.findById(id);
        if (contaOptional.isEmpty()) {
            throw new ContaNaoEncontradaException("Conta com ID " + id + " não encontrada.");
        }
        return contaOptional.get();
    }

    @Override
    public List<Conta> buscarTodasContas() {
        return contaRepository.findAll();
    }

    @Override
    public List<Conta> buscarContasPorCliente(Long cpf) {
        return contaRepository.findByIdUsuarioId(cpf);
    }

    @Override
    public void deletarConta(Long id) {
        Conta conta = buscarContaPorId(id);
        contaRepository.delete(conta);
    }

    @Override
    public BigDecimal buscarSaldoConta(Long id) {
        Conta conta = buscarContaPorId(id);
        return conta.getSaldo();
    }
}
