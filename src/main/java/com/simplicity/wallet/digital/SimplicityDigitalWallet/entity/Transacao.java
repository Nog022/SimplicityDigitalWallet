package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.enums.TipoTransacao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transacao", schema = "simplicity_digital_db")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "dataTransacao")
    private Timestamp dataTransacao;

    @Column(name = "valor", precision = 15, scale = 2)
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idContaOrigem")
    private Conta idContaOrigem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idContaDestino")
    private Conta idContaDestino;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;

}