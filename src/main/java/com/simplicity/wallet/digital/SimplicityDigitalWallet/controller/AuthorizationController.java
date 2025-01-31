package com.simplicity.wallet.digital.SimplicityDigitalWallet.controller;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.config.TokenService;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.AuthorizationDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.LoginResponseDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.MudarRoleResponseDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.RegisterDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Contato;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Endereco;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Usuario;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.ContatoRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.EnderecoRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.UsuarioRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.ContaService;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth")
@RestController
public class AuthorizationController {

    public static Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ContaService contaService;


    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated AuthorizationDTO data) {
        try {
            logger.info("Iniciando login");
            logger.info("Dados recebidos: CPF: {}, Senha: {}", data.cpf(), data.password());


            var usernamePassword = new UsernamePasswordAuthenticationToken(data.cpf(), data.password());



            var auth = this.authenticationManager.authenticate(usernamePassword);
            logger.info("Autenticação bem-sucedida");


            var usuario = (Usuario) auth.getPrincipal();
            logger.info("Usuário autenticado: {}", usuario.getUsername());


            var token = tokenService.generateToken(usuario);
            logger.info("Token JWT gerado com sucesso");

            Usuario usuarioEncontrado = usuarioService.usuarioByCpf(data.cpf());
            List<Conta> contaEncontrada = contaService.findByUsuario_Id(usuarioEncontrado.getId());


            return ResponseEntity.ok(new LoginResponseDTO(token, contaEncontrada));
        } catch (Exception e) {
            logger.error("Erro durante o login: {}", e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponseDTO("Erro durante o login: " + e.getMessage(), null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody @Validated RegisterDTO registerDTO) {



        if(this.usuarioRepository.findByCpf(registerDTO.cpf()).isPresent()) return ResponseEntity.badRequest().build();

        enderecoRepository.save(registerDTO.idEndereco());
        contatoRepository.save(registerDTO.idContato());

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
