package com.simplicity.wallet.digital.SimplicityDigitalWallet.service;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.AtualizarUsuarioDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

    Usuario usuarioById(Long id);

    Usuario usuarioByCpf(String cpf);

    Usuario updateUsuario(AtualizarUsuarioDTO atualizarUsuarioDTO);

    String mudarRole(String cpf);

}
