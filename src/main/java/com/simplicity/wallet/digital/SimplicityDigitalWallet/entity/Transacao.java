package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public abstract class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private BigDecimal valor;
    private LocalDateTime dataTransacao;

    private Long  contaOrigiem;

    private Long  contaDestino;
    private String tipo;
    private LocalDate dataVencimentoBoleto;
}
