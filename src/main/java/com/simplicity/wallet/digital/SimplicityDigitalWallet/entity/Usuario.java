package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuario", schema = "simplicity_digital_db")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cpf", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Column(name = "dataNascimento")
    private LocalDate dataNascimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEndereco")
    private Endereco idEndereco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idContato")
    private Contato idContato;

    @Enumerated(EnumType.STRING)
    private UserRole role;

}