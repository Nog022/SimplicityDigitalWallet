package com.simplicity.wallet.digital.SimplicityDigitalWallet.service;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Endereco;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;

@Service
public interface EnderecoService {

    public Endereco salvar(Endereco endereco);

    public void deletar(Long id);
}
