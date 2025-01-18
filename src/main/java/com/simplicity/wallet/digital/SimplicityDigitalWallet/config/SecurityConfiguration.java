package com.simplicity.wallet.digital.SimplicityDigitalWallet.config;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.impl.AuthorizationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    public static Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        logger.info("Configuring HttpSecurity");
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        //auth
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        //conta
                        .requestMatchers(HttpMethod.POST, "/conta/save").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/conta/delete/{numeroConta}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/conta/{numeroConta}/saldo").hasRole("USER")
                        //contato
                        .requestMatchers(HttpMethod.POST, "/contato/save").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/contato/delete/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/contato/update").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/contato/contatoById/{id}").hasRole("USER")
                        //deposito
                        .requestMatchers(HttpMethod.POST, "/deposito/gerarBoleto").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/deposito/pagarBoletoViaConta").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/deposito/pagarBoleto").hasRole("USER")
                        //endereco
                        .requestMatchers(HttpMethod.POST, "/endereco/save").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/endereco/delete/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/endereco/update").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/endereco/enderecoById/{id}").hasRole("USER")
                        //pix
                        .requestMatchers(HttpMethod.POST, "/pix/save").hasRole("USER")
                        //saque
                        .requestMatchers(HttpMethod.POST, "/saque").hasRole("USER")
                        //transacao
                        .requestMatchers(HttpMethod.GET, "/transacao/extrato/{idConta}").hasRole("USER")
                        //usuario
                        .requestMatchers(HttpMethod.GET, "/usuario/usuarioById/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/usuario/usuarioByCpf/{cpf}").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/usuario/updateUsuario").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/usuario/mudarRole/{cpf}").hasRole("ADMIN")


                        .anyRequest().permitAll()

                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        logger.info("Configuring AuthenticationManager");
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        logger.info("Configuring PasswordEncoder");
        return new BCryptPasswordEncoder();
    }

}