package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.enums.Pago;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "deposito", schema = "simplicity_digital_db")
public class Deposito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "valor", precision = 15, scale = 2)
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conta")
    private Conta idConta;

    @Column(name = "data_transacao")
    private Timestamp dataTransacao;


    @Enumerated(EnumType.STRING)
    @Column(name = "pago", nullable = false)
    private Pago pago;

    @NotNull
    @Column(name = "boleto")
    private String boleto;


}