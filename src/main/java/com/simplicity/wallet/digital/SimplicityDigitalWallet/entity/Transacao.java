package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private Conta contaOrigiem;
    private Conta contaDestino;
    private String tipo;
    private LocalDate dataVencimentoBoleto;
}
