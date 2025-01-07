package com.simplicity.wallet.digital.SimplicityDigitalWallet.service;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.AuthorizationDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.LoginResponseDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.RegisterDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthorizationService {

    UserDetails loadUserByUsername(String username);

    LoginResponseDTO login(AuthorizationDTO data);

    ResponseEntity<Usuario> register(RegisterDTO registerDTO);
}
