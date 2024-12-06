package com.simplicity.wallet.digital.SimplicityDigitalWallet.controller;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Endereco;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.EnderecoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/endereco")
@Slf4j
public class EnderecoController {
    public static Logger logger = LoggerFactory.getLogger(EnderecoController.class);

    @Autowired
    private  EnderecoService enderecoService;


    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco save(@RequestBody @Validated Endereco endereco) {
        return enderecoService.salvar(endereco);

    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @Validated Long id) {
        enderecoService.deletar(id);

    }
}
