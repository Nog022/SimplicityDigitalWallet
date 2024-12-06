package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Conta {

    @Id
    private Long id;
    private Long numeroConta;
    private Long saldo;

    // Relacionamento de uma Conta para muitas Transações de Origem
    @OneToMany(mappedBy = "contaOrigiem")
    private List<Transacao> transacoesOrigem;

    // Relacionamento de uma Conta para muitas Transações de Destino
    @OneToMany(mappedBy = "contaDestino")
    private List<Transacao> transacoesDestino;
    private LocalDateTime dataCriacao;


    private Long usuario_id;


}
