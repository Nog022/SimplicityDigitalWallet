package com.simplicity.wallet.digital.SimplicityDigitalWallet.controller;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.ExtratoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Transacao;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/extrato/{idConta}")
    public ResponseEntity<List<ExtratoDTO>> extrato(@PathVariable Long idConta) {
        List<ExtratoDTO> extrato = transacaoService.extrato(idConta);
        return ResponseEntity.ok(extrato);  // Retorna os dados com status 200
    }
}
