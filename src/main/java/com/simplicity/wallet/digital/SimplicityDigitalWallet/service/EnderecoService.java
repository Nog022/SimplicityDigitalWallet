package com.simplicity.wallet.digital.SimplicityDigitalWallet.service;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.AtualizarEnderecoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.EnderecoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Endereco;

public interface EnderecoService {

    Endereco salvar(EnderecoDTO endereco);

    void deletar(Long id);

    Endereco atualizarEndereco(AtualizarEnderecoDTO endereco);

    Endereco enderecoById(Long id);

    Endereco atualizarEndereco(Endereco endereco);
}
