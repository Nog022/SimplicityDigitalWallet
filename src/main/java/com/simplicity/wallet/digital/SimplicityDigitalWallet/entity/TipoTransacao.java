package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TipoTransacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

}
