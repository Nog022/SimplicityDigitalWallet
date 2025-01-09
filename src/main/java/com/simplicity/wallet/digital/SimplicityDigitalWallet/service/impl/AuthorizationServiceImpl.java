package com.simplicity.wallet.digital.SimplicityDigitalWallet.service.impl;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.config.SecurityFilter;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.config.TokenService;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.AuthorizationDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.LoginResponseDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.RegisterDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Usuario;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.UsuarioRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    public static Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        return usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com CPF: " + cpf));

    }

    @Override
    public LoginResponseDTO login(AuthorizationDTO data) {
        try {
            logger.info("Iniciando login");
            logger.info("Dados recebidos: {}", data);

            var usernamePassword = new UsernamePasswordAuthenticationToken(data.cpf(), data.password());
            logger.info("Token de autenticação gerado");

            logger.info("usernamePassword: {}", usernamePassword);

            var auth = this.authenticationManager.authenticate(usernamePassword);
            logger.info("Autenticação bem-sucedida");

            var usuario = (Usuario) auth.getPrincipal();
            logger.info("Usuário autenticado: {}", usuario.getUsername());

            var token = tokenService.generateToken(usuario);
            logger.info("Token gerado");

            return new LoginResponseDTO(token);
        } catch (Exception e) {
            logger.error("Erro durante o login: {}", e.getMessage(), e);
            return new LoginResponseDTO("Erro: " + e.getMessage());
        }

    }

    @Override
    public ResponseEntity<Usuario> register(RegisterDTO registerDTO) {

        logger.info("Iniciando registro");
        logger.info("Dados recebidos: {}", registerDTO);

        if(this.usuarioRepository.findByCpf(registerDTO.cpf()).isPresent()) return ResponseEntity.badRequest().build();

        String encryptPassword = new BCryptPasswordEncoder().encode(registerDTO.senha());
        Usuario usuario = new Usuario(
                registerDTO.cpf(),
                registerDTO.nome(),
                encryptPassword,
                registerDTO.dataNascimento(),
                registerDTO.idEndereco(),
                registerDTO.idContato(),
                registerDTO.role()
        );

        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }
}
