package com.simplicity.wallet.digital.SimplicityDigitalWallet.controller;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.LoginResponseDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.PixDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.PixResponseDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.PixService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pix")
public class PixController {

    @Autowired
    private PixService pixService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PixResponseDTO> save(@RequestBody @Validated PixDTO pixDTO) {
        return ResponseEntity.ok(new PixResponseDTO(pixService.transferenciaPix(pixDTO)));

    }
}
