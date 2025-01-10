package com.simplicity.wallet.digital.SimplicityDigitalWallet.controller;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.DepositoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.PagarBoletoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Deposito;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.DepositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deposito")
public class DepositoController {

    @Autowired
    private DepositoService depositoService;

    @PostMapping("/gerarBoleto")
    public String gerarBoleto(@RequestBody DepositoDTO deposito) {
        return depositoService.gerarBoleto(deposito);

    }

    @PostMapping("/pagarBoleto")
    public String pagarBoleto(@RequestBody PagarBoletoDTO pagarBoletoDTO) {
        return depositoService.pagarBoleto(pagarBoletoDTO);

    }
}
