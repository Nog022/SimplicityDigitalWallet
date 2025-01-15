package com.simplicity.wallet.digital.SimplicityDigitalWallet.controller;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.SaqueDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.SaqueResponseDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Transacao;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.SaqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saque")
public class SaqueController {

    @Autowired
    private SaqueService saqueService;

    @PostMapping
    public ResponseEntity<SaqueResponseDTO> Saque(@RequestBody SaqueDTO saqueDTO) {
        try {
            String result = saqueService.realizarSaque(saqueDTO);
            return ResponseEntity.ok(new SaqueResponseDTO(result));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new SaqueResponseDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new SaqueResponseDTO("Erro ao processar o saque"));
        }
    }
}
