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
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        //TODO Colocar os endpoints com as roles que cada url precisa ser acessado
//                        .requestMatchers(HttpMethod.POST, "/contato/save").hasRole("USER")
//                        .requestMatchers(HttpMethod.GET, "/contato/contatoById/{id}").hasRole("USER")
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