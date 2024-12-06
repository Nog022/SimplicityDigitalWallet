package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class PessoaFisica extends Usuario{

    private String cpf;
    private String rg;
    private String nome;
}
