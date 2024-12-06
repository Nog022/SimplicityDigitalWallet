package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
public class Boleto {
    @Id
    private String id;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private Date dataVencimento;

    @Column(nullable = false)
    private String status;

    private Timestamp dataGeracao;

    private Long idTransacao;


}
