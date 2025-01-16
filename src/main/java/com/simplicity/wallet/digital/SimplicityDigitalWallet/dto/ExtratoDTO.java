package com.simplicity.wallet.digital.SimplicityDigitalWallet.dto;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.enums.TipoTransacao;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record ExtratoDTO(
        Long id,
        Timestamp dataTransacao,
        BigDecimal valor,
        Long idContaOrigem,
        Long idContaDestino,
        TipoTransacao tipoTransacao
) {
}