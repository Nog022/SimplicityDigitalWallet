package com.simplicity.wallet.digital.SimplicityDigitalWallet.dto;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;

import java.math.BigDecimal;

public record DepositoDTO(BigDecimal valor, Long numeroConta) {

}
