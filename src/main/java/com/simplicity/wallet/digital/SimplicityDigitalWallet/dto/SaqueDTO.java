package com.simplicity.wallet.digital.SimplicityDigitalWallet.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record SaqueDTO(
        Long idConta,
        Timestamp dataSaque ,
        BigDecimal valor
) {
}
