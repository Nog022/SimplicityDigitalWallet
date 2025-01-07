package com.simplicity.wallet.digital.SimplicityDigitalWallet.dto;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Contato;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Endereco;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.enums.UserRole;

import java.time.LocalDate;

public record RegisterDTO(
        String nome,
        String senha,
        LocalDate dataNascimento,
        Endereco idEndereco,
        Contato idContato,
        UserRole role) {
}
