package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public abstract class Transacao {

    @Id
    private String id;
    private BigDecimal valor;
    private LocalDateTime dataTransacao;
    // Relacionamento com a Conta de Origem
    @ManyToOne
    @JoinColumn(name = "conta_origiem_id")
    private Conta contaOrigiem;

    // Relacionamento com a Conta de Destino
    @ManyToOne
    @JoinColumn(name = "conta_destino_id")
    private Conta contaDestino;
    private String tipo;
    private LocalDate dataVencimentoBoleto;
}
