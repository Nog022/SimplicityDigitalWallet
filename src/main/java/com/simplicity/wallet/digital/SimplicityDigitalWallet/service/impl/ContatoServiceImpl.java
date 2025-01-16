package com.simplicity.wallet.digital.SimplicityDigitalWallet.service.impl;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.AtualizarContatoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.ContatoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Contato;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.ContatoRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContatoServiceImpl implements ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Override
    public Contato salvar(ContatoDTO contatoDTO) {
        Contato contato = new Contato();

        contato.setDdd(contatoDTO.ddd());
        contato.setEmail(contatoDTO.email());
        contato.setTelefone(contatoDTO.telefone());

        return contatoRepository.save(contato);

    }

    @Override
    public void deletar(Long id) {
        contatoRepository.findById(id).map(contatoId -> {
            contatoRepository.deleteById(id);
            return contatoId;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));
    }

    @Override
    public Contato atualizarContato(AtualizarContatoDTO contatoDTO) {
        return contatoRepository.findById(contatoDTO.id())
                .map(contatoEncontrado -> {
                    if (contatoDTO.ddd() != null) contatoEncontrado.setDdd(contatoDTO.ddd());
                    if (contatoDTO.email() != null) contatoEncontrado.setEmail(contatoDTO.email());
                    if (contatoDTO.telefone() != null) contatoEncontrado.setTelefone(contatoDTO.telefone());
                    return contatoRepository.save(contatoEncontrado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));

    }

    @Override
    public Contato atualizarContato(Contato contato) {
        return contatoRepository.findById(contato.getId())
                .map(contatoEncontrado -> {
                    if (contato.getDdd() != null) contatoEncontrado.setDdd(contato.getDdd());
                    if (contato.getEmail() != null) contatoEncontrado.setEmail(contato.getEmail());
                    if (contato.getTelefone() != null) contatoEncontrado.setTelefone(contato.getTelefone());
                    return contatoRepository.save(contatoEncontrado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));

    }

    @Override
    public Contato contatoById(Long id) {
        return contatoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));
    }
}
