package com.simplicity.wallet.digital.SimplicityDigitalWallet.controller;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.ContaDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Usuario;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value ="/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Conta save(@RequestBody @Validated ContaDTO conta) {
        return contaService.salvarConta(conta);
    }

    @DeleteMapping("/delete/{numeroConta}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("numeroConta") @Validated Long numeroConta) {
        contaService.deletarConta(numeroConta);
    }

    @GetMapping("/{numeroConta}/saldo")
    public BigDecimal mostrarSaldo(@PathVariable String numeroConta) {
        return contaService.buscarSaldoConta(Long.valueOf(numeroConta));
    }

    @GetMapping("/contaByCpf/{cpf}")
    public List<Conta> contaByCpf(@PathVariable String cpf){
        return contaService.contaByCpf(cpf);
    }

}
