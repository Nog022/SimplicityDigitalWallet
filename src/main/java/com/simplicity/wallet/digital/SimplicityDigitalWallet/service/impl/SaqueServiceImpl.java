package com.simplicity.wallet.digital.SimplicityDigitalWallet.service.impl;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.SaqueDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Saque;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.exceptions.ContaNaoEncontradaException;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.ContaRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.SaqueRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.SaqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SaqueServiceImpl implements SaqueService {

    @Autowired
    private SaqueRepository saqueRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public void realizarSaque(SaqueDTO saqueDTO) {
        if (saqueDTO.valor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
        }
        Conta conta = contaRepository.findByNumeroConta(saqueDTO.idConta())
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta de número: " + saqueDTO.idConta() + " não foi encontrada."));

        BigDecimal saldoAtual = conta.getSaldo();
        if (saldoAtual.compareTo(saqueDTO.valor()) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar o saque.");
        }

        conta.setSaldo(saldoAtual.subtract(saqueDTO.valor()));
        contaRepository.save(conta);

        Saque saque = new Saque();
        saque.setValor(saqueDTO.valor());
        saque.setDataSaque(saqueDTO.dataSaque());
        saque.setIdConta(conta);
        saqueRepository.save(saque);
    }
}
