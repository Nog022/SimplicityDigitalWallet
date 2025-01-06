package com.simplicity.wallet.digital.SimplicityDigitalWallet.controller;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping(value ="/api/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping("/{id}/saldo")
    public ResponseEntity<Map<String, Object>> mostrarSaldo(@PathVariable Long id) {
        BigDecimal saldo = contaService.buscarSaldoConta(id);
        return ResponseEntity.ok(Map.of(
                "mensagem", "Saldo encontrado",
                "saldo", saldo
        ));
    }

}
