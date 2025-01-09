package com.simplicity.wallet.digital.SimplicityDigitalWallet.controller;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.ContaDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.ContaService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value ="/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Conta save(@RequestBody @Validated ContaDTO contaDto) {
        return contaService.salvarConta(contaDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @Validated Long id) {
        contaService.deletarConta(id);
    }

    @GetMapping("/saldo")
    public BigDecimal mostrarSaldo(Authentication authentication) {
        String numeroConta = authentication.name();
        return contaService.buscarSaldoConta(Long.valueOf(numeroConta));
    }

    @GetMapping("/conta/{id}")
    public Conta contaPorId(@PathVariable Long id) {
        return contaService.buscarContaPorId(id);
    }

}
