package com.simplicity.wallet.digital.SimplicityDigitalWallet.controller;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.AtualizarUsuarioDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.DeleteResponseDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.MudarRoleResponseDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Usuario;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarioById/{id}")
    public Usuario usuarioById(@PathVariable Long id){
        return usuarioService.usuarioById(id);
    }

    @GetMapping("/usuarioByCpf/{cpf}")
    public Usuario usuarioByCpf(@PathVariable String cpf){
        return usuarioService.usuarioByCpf(cpf);
    }

    @PutMapping("/updateUsuario")
    public Usuario usuarioByCpf(@RequestBody AtualizarUsuarioDTO atualizarUsuarioDTO){
        return usuarioService.updateUsuario(atualizarUsuarioDTO);
    }

    @PostMapping("/mudarRole/{cpf}")
    public ResponseEntity<MudarRoleResponseDTO> mudarRole(@PathVariable String cpf) {
        return ResponseEntity.ok(new MudarRoleResponseDTO(usuarioService.mudarRole(cpf)));

    }



}
