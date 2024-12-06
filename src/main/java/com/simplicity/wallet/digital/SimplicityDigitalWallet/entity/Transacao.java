package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Transacao {
    @Id
    private String id;

    private Timestamp dataTransacao;
    private Long idTipo;
    private BigDecimal valor;

    private Long idContaOrigem;
    private Long idContaDestino;


}
