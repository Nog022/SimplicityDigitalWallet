package com.simplicity.wallet.digital.SimplicityDigitalWallet.config;


import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter  extends OncePerRequestFilter {

    public static Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("Inicio doFilterInternal");
        var token = this.recoverToken(request);
        logger.info("1");
        if(token != null){
            logger.info("2");
            var login = tokenService.validateToken(token);
            UserDetails user = usuarioRepository.findByCpf(login)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com CPF: " + login));


            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        logger.info("Inicio recoverToken");
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}