package com.simplicity.wallet.digital.SimplicityDigitalWallet.repository;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}