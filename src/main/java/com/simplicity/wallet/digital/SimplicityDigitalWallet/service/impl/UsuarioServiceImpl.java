package com.simplicity.wallet.digital.SimplicityDigitalWallet.service.impl;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.AtualizarUsuarioDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.*;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.enums.UserRole;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.UsuarioRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContatoService contatoService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ContaService contaService;

    @Autowired
    private PixService pixService;

    public static Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);


    @Override
    public Usuario usuarioById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }

    @Override
    public Usuario usuarioByCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }
    @Override
    public Usuario updateUsuario(AtualizarUsuarioDTO dto) {

        Usuario usuarioExistente = usuarioByCpf(dto.usuario().getCpf());


        if (dto.usuario().getCpf() != null) usuarioExistente.setCpf(dto.usuario().getCpf());
        if (dto.usuario().getNome() != null) usuarioExistente.setNome(dto.usuario().getNome());
        if (dto.usuario().getSenha() != null) usuarioExistente.setSenha(new BCryptPasswordEncoder().encode(dto.usuario().getSenha()));


        Contato contato = dto.usuario().getIdContato() != null
                ? contatoService.atualizarContato(dto.usuario().getIdContato())
                : usuarioExistente.getIdContato();
        usuarioExistente.setIdContato(contato);


        Endereco endereco = dto.usuario().getIdEndereco() != null
                ? enderecoService.atualizarEndereco(dto.usuario().getIdEndereco())
                : usuarioExistente.getIdEndereco();
        usuarioExistente.setIdEndereco(endereco);

        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public String mudarRole(String cpf) {
        Usuario usuario =  this.usuarioByCpf(cpf);

        if(usuario.getRole() == UserRole.USER) {
            usuario.setRole(UserRole.ADMIN);
            usuarioRepository.save(usuario);
            return usuario.getNome() + " agora é ADM do sistema!";
        }



        return usuario.getNome() + " já é ADM do sistema.";
    }




}
