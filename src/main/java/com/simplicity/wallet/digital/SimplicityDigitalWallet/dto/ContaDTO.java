package com.simplicity.wallet.digital.SimplicityDigitalWallet.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record ContaDTO(
        Long numeroConta,
        BigDecimal saldo,
        Timestamp dataCriacao,
        Long idUsuario
) {
}
