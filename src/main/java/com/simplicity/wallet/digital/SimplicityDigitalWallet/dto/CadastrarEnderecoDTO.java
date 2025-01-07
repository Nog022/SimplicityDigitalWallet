package com.simplicity.wallet.digital.SimplicityDigitalWallet.dto;

public record CadastrarEnderecoDTO(
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        String cep
) {
}