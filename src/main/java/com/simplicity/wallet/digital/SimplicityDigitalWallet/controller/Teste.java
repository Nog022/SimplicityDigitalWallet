package com.simplicity.wallet.digital.SimplicityDigitalWallet.controller;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Endereco;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.EnderecoRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/api/teste")
@Slf4j
public class Teste {
    public static Logger logger = LoggerFactory.getLogger(Teste.class);


    // Injeção do repositório EnderecoRepository
    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody @Validated Endereco endereco) {
        if (endereco != null) {


            enderecoRepository.save(endereco);
            return "entrou no if";
        }
        logger.info("Endereco é nulo");
        return "Não entrou";
    }
}
