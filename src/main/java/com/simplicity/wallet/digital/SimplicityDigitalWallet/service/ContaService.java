package com.simplicity.wallet.digital.SimplicityDigitalWallet.service;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;

import java.math.BigDecimal;
import java.util.List;

public interface ContaService {
    Conta salvarConta(Conta conta);
    Conta buscarContaPorId(Long id);
    List<Conta> buscarTodasContas();
    List<Conta> buscarContasPorCliente(Long cpf);
    void deletarConta(Long id);
    BigDecimal buscarSaldoConta(Long id);
}
