package com.simplicity.wallet.digital.SimplicityDigitalWallet.service;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.ContaDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;

import java.math.BigDecimal;

public interface ContaService {
    Conta salvarConta(ContaDTO conta);
    Conta buscarContaPorId(Long id);
    void deletarConta(Long numeroConta);
    BigDecimal buscarSaldoConta(Long id);
    void atualizarSaldoConta(Long numeroConta, BigDecimal valor, boolean isSaida);
    Conta buscarConta(Long numeroConta);
}
