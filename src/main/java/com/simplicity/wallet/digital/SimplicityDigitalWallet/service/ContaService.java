package com.simplicity.wallet.digital.SimplicityDigitalWallet.service;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.ContaDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;

import java.math.BigDecimal;
import java.util.List;

public interface ContaService {
    Conta salvarConta(ContaDTO conta);
    Conta buscarContaPorId(Long id);
    void deletarConta(Long id);
    BigDecimal buscarSaldoConta(Long id);
    void atualizarSaldoConta(String numeroConta, BigDecimal valor, boolean isSaida);
}
