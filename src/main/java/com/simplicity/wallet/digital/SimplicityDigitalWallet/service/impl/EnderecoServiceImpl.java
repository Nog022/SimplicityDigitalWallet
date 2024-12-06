package com.simplicity.wallet.digital.SimplicityDigitalWallet.service.impl;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Endereco;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.EnderecoRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public Endereco salvar(Endereco endereco) {

        if(endereco != null && enderecoRepository != null){
            return enderecoRepository.save(endereco);
        }

        return null;

    }

    @Override
    public void deletar(Long id) {
        enderecoRepository.findById(id).map(enderecoId -> {
            enderecoRepository.deleteById(id);
            return enderecoId;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
    }
}
