package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public abstract  class Usuario {

    @Id
    private Long id;
    private String email;
    private String senha;
    private String endereco;
    private String celular;
}
