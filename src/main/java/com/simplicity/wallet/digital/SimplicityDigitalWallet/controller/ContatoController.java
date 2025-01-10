package com.simplicity.wallet.digital.SimplicityDigitalWallet.controller;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.AtualizarContatoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.ContatoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Contato;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Contato save(@RequestBody @Validated ContatoDTO contato) {
        return contatoService.salvar(contato);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @Validated Long id) {
        contatoService.deletar(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Contato atualizarContato(@RequestBody @Validated AtualizarContatoDTO contatoDTO) {
        return contatoService.atualizarContato(contatoDTO);
    }

    @GetMapping("/contatoById/{id}")
    public Contato contatoById(@PathVariable Long id){
        return contatoService.contatoById(id);
    }

}
