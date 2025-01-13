package com.simplicity.wallet.digital.SimplicityDigitalWallet.service;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.SaqueDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Transacao;

public interface SaqueService {
    String realizarSaque(SaqueDTO saqueDTO);
}
