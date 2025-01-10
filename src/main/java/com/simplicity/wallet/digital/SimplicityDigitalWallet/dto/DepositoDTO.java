package com.simplicity.wallet.digital.SimplicityDigitalWallet.dto;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record DepositoDTO(BigDecimal valor, Conta idConta, Timestamp dataTransacao) {

}
