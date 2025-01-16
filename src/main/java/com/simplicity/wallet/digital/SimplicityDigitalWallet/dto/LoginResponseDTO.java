package com.simplicity.wallet.digital.SimplicityDigitalWallet.dto;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;

import java.util.List;

public record LoginResponseDTO(
        String token,
        List<Conta> conta
) {
}
