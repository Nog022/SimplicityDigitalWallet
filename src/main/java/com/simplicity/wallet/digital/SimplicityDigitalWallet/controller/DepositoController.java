package com.simplicity.wallet.digital.SimplicityDigitalWallet.controller;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.DepositoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.DepositoResponseDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.PagarBoletoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.PagarBoletoViaContaDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.DepositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DepositoResponseDTO> gerarBoleto(@RequestBody DepositoDTO deposito) {
        return ResponseEntity.ok(new DepositoResponseDTO(depositoService.gerarBoleto(deposito)));

    }

    @PostMapping("/pagarBoletoViaConta")
    public ResponseEntity<DepositoResponseDTO> pagarBoletoViaConta(@RequestBody PagarBoletoViaContaDTO pagarBoletoViaContaDTO) {
        return ResponseEntity.ok(new DepositoResponseDTO(depositoService.pagarBoletoViaConta(pagarBoletoViaContaDTO)));

    }

    @PostMapping("/pagarBoleto")
    public ResponseEntity<DepositoResponseDTO> pagarBoleto(@RequestBody PagarBoletoDTO  pagarBoletoDTO) {
        return ResponseEntity.ok(new DepositoResponseDTO(depositoService.pagarBoleto(pagarBoletoDTO)));

    }
}
