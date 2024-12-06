package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Usuario {
    @Id
    private Long numeroDocumento;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String senha;

    private Date dataNascimento;


    private Long idEndereco;


    private Long idContato;


    private Long idPerfil;

    // Getters e Setters
}
