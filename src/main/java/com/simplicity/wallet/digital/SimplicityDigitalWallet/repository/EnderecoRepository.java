package com.simplicity.wallet.digital.SimplicityDigitalWallet.repository;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
