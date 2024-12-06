package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import com.mysql.cj.log.Log;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;



@Entity
@Data
public class Pix {
    @Id
    private String chavePix;

    private Long idConta;


}
