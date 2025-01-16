package com.simplicity.wallet.digital.SimplicityDigitalWallet.service;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.DepositoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.PagarBoletoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.PagarBoletoViaContaDTO;
import org.springframework.stereotype.Service;

@Service
public interface DepositoService {

    String pagarBoletoViaConta(PagarBoletoViaContaDTO pagarBoletoViaContaDTO);

    String pagarBoleto(PagarBoletoDTO pagarBoletoDTO);


    String gerarBoleto(DepositoDTO deposito);
}
