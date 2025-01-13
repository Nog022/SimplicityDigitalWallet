package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pix", schema = "simplicity_digital_db")
public class Pix {

    @Id
    private String id;

    @JoinColumn(name = "idConta")
    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Conta conta;

}
