package com.simplicity.wallet.digital.SimplicityDigitalWallet.service;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.AtualizarContatoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.AtualizarEnderecoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.ContatoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Contato;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Endereco;
import org.springframework.stereotype.Service;

@Service
public interface ContatoService {

    Contato salvar(ContatoDTO contato);

    void deletar(Long id);

    Contato atualizarContato(AtualizarContatoDTO contatoDTO);
    Contato atualizarContato(Contato contato);

    Contato contatoById(Long id);

}
