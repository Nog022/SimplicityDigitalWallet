package com.simplicity.wallet.digital.SimplicityDigitalWallet.service;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.PixDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Pix;
import org.springframework.stereotype.Service;

@Service
public interface PixService {

    Pix criarPix(Conta conta);

    String transferenciaPix(PixDTO pix);
}
