package com.simplicity.wallet.digital.SimplicityDigitalWallet.service.impl;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.config.SecurityConfiguration;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.dto.ContaDTO;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Conta;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.entity.Usuario;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.exceptions.ContaNaoEncontradaException;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.ContaRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.repository.UsuarioRepository;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.ContaService;
import com.simplicity.wallet.digital.SimplicityDigitalWallet.service.PixService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;

@Service
public class ContaServiceImpl implements ContaService {

    public static Logger logger = LoggerFactory.getLogger(ContaServiceImpl.class);


    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    @Lazy
    private PixService pixService;

    private final SecureRandom random = new SecureRandom();

    @Override
    public Conta salvarConta(ContaDTO contaDto) {
        Usuario usuario = usuarioRepository.findByCpf(String.valueOf(contaDto.idUsuario()))
                .orElseThrow(() -> new IllegalArgumentException("Usuário com CPF " + contaDto.idUsuario() + " não encontrado."));

        Conta novaConta = new Conta();

        novaConta.setNumeroConta(gerarNumeroConta());

        novaConta.setSaldo(BigDecimal.ZERO);
        novaConta.setDataCriacao(Timestamp.from(Instant.now()));
        novaConta.setUsuario(usuario);
        contaRepository.save(novaConta);

        novaConta.setChavePix(pixService.criarPix(novaConta));
        return contaRepository.save(novaConta);

    }

    @Override
    public Conta buscarContaPorId(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta com ID " + id + " não encontrada."));
    }

    @Override
    public void deletarConta(Long numeroConta) {
        Conta conta = contaRepository.findByNumeroConta(numeroConta)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta com número " + numeroConta + " não encontrada."));
        contaRepository.delete(conta);
    }

    @Override
    public BigDecimal buscarSaldoConta(Long numeroConta) {
        Conta conta = contaRepository.findByNumeroConta(numeroConta)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta com número " + numeroConta + " não encontrada."));
        return conta.getSaldo();
    }

    @Override
    public Conta buscarConta(Long numeroConta) {
        return contaRepository.findByNumeroConta(numeroConta)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta com número " + numeroConta + " não encontrada."));

    }

    @Override
    public void atualizarSaldoConta(Long numeroConta, BigDecimal valor, boolean isSaida) {
        Conta conta = contaRepository.findByNumeroConta(numeroConta)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta com número " + numeroConta + " não encontrada."));

        BigDecimal ajuste = isSaida ? valor.negate() : valor;

        if (isSaida && conta.getSaldo().add(ajuste).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar a operação.");
        }

        conta.setSaldo(conta.getSaldo().add(ajuste));
        contaRepository.save(conta);
    }

    private Long gerarNumeroConta() {
        Long numeroConta;
        do {
            numeroConta = random.nextLong(10000000, 100000000);
        } while (contaRepository.findByNumeroConta(numeroConta).isPresent());
        return numeroConta;
    }


}