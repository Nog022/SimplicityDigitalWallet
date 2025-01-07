package com.simplicity.wallet.digital.SimplicityDigitalWallet.repository;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByRuaAndNumeroAndBairro(String rua, String numero, String bairro);
}
