package com.simplicity.wallet.digital.SimplicityDigitalWallet.repository;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Deposito;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.enums.Pago;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface DepositoRepository extends CrudRepository<Deposito, Long> {



    @Query("SELECT d FROM Deposito d WHERE d.idConta.id = :idConta AND d.pago = :pago AND d.valor = :valor")
    Deposito findDepositoByIdContaAndPagoAndValor(@Param("idConta") Long idConta, @Param("pago") Pago pago, @Param("valor") BigDecimal valor);


    Deposito findDepositoByBoleto(String boleto);
}
