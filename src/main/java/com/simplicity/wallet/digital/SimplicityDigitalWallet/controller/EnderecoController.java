package com.simplicity.wallet.digital.SimplicityDigitalWallet.controller;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.AtualizarEnderecoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.EnderecoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Endereco;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/endereco")
@RestController
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco save(@RequestBody @Validated EnderecoDTO endereco) {
        return service.salvar(endereco);

    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @Validated Long id) {
        service.deletar(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Endereco atualizarEndereco(@RequestBody @Validated AtualizarEnderecoDTO endereco) {
        return service.atualizarEndereco(endereco);
    }

    @GetMapping("/enderecoById/{id}")
    public Endereco enderecoById(@PathVariable Long id){
        return service.enderecoById(id);
    }
}
