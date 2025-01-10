package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "saque", schema = "simplicity_digital_db")
public class Saque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "valor", precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(name = "dataSaque")
    private Timestamp dataSaque;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConta")
    private Conta idConta;

}