package com.simplicity.wallet.digital.SimplicityDigitalWallet.repository;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Saque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaqueRepository extends JpaRepository<Saque, Long> {
}
