package com.simplicity.wallet.digital.SimplicityDigitalWallet.service.impl;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.AtualizarEnderecoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.CadastrarEnderecoDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Endereco;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.EnderecoRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public Endereco salvar(CadastrarEnderecoDTO cadastrarEnderecoDto) {
        Endereco endereco = new Endereco();

        endereco.setRua(cadastrarEnderecoDto.rua());
        endereco.setNumero(cadastrarEnderecoDto.numero());
        endereco.setComplemento(cadastrarEnderecoDto.complemento());
        endereco.setBairro(cadastrarEnderecoDto.bairro());
        endereco.setCidade(cadastrarEnderecoDto.cidade());
        endereco.setUf(cadastrarEnderecoDto.uf());
        endereco.setCep(cadastrarEnderecoDto.cep());


        return enderecoRepository.save(endereco);
    }

    @Override
    public void deletar(Long id) {
        enderecoRepository.findById(id).map(enderecoId -> {
            enderecoRepository.deleteById(id);
            return enderecoId;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
    }

    @Override
    public Endereco atualizarEndereco(AtualizarEnderecoDTO atualizarEnderecoDTO) {
        return enderecoRepository.findById(atualizarEnderecoDTO.id())
                .map(enderecoEncontrado -> {

                    if (atualizarEnderecoDTO.rua() != null) enderecoEncontrado.setRua(atualizarEnderecoDTO.rua());
                    if (atualizarEnderecoDTO.numero() != null) enderecoEncontrado.setNumero(atualizarEnderecoDTO.numero());
                    if (atualizarEnderecoDTO.complemento() != null) enderecoEncontrado.setComplemento(atualizarEnderecoDTO.complemento());
                    if (atualizarEnderecoDTO.bairro() != null) enderecoEncontrado.setBairro(atualizarEnderecoDTO.bairro());
                    if (atualizarEnderecoDTO.cidade() != null) enderecoEncontrado.setCidade(atualizarEnderecoDTO.cidade());
                    if (atualizarEnderecoDTO.uf() != null) enderecoEncontrado.setUf(atualizarEnderecoDTO.uf());
                    if (atualizarEnderecoDTO.cep() != null) enderecoEncontrado.setCep(atualizarEnderecoDTO.cep());


                    return enderecoRepository.save(enderecoEncontrado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
    }

    @Override
    public Endereco enderecoById(Long id) {
        return enderecoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
    }
}
