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
                        .requestMatchers(HttpMethod.DELETE, "/conta/delete/{numeroConta}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/conta/{numeroConta}/saldo").hasAnyRole("USER", "ADMIN")
                        //contato
                        .requestMatchers(HttpMethod.POST, "/contato/save").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/contato/delete/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/contato/update").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/contato/contatoById/{id}").hasAnyRole("USER", "ADMIN")
                        //deposito
                        .requestMatchers(HttpMethod.POST, "/deposito/gerarBoleto").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/deposito/pagarBoletoViaConta").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/deposito/pagarBoleto").hasAnyRole("USER", "ADMIN")
                        //endereco
                        .requestMatchers(HttpMethod.POST, "/endereco/save").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/endereco/delete/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/endereco/update").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/endereco/enderecoById/{id}").hasAnyRole("USER", "ADMIN")
                        //pix
                        .requestMatchers(HttpMethod.POST, "/pix/save").hasAnyRole("USER", "ADMIN")
                        //saque
                        .requestMatchers(HttpMethod.POST, "/saque").hasAnyRole("USER", "ADMIN")
                        //transacao
                        .requestMatchers(HttpMethod.GET, "/transacao/extrato/{idConta}").hasAnyRole("USER","ADMIN")
                        //usuario
                        .requestMatchers(HttpMethod.GET, "/usuario/usuarioById/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuario/usuarioByCpf/{cpf}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/usuario/updateUsuario").hasAnyRole("USER", "ADMIN")
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