package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.security.Timestamp;

@Data
@Entity
@NoArgsConstructor
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numeroConta;
    private BigDecimal saldo;
    private Timestamp dataCriacao;


    private Long idUsuario;


}
