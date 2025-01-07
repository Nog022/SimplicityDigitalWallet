package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import com.simplicity.wallet.digital.SimplicityDigitalWallet.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "usuario", schema = "simplicity_digital_db")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {
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

    public Usuario(String nome, String senha, LocalDate dataNascimento, Endereco idEndereco, Contato idContato, UserRole role) {
        this.nome = nome;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.idEndereco = idEndereco;
        this.idContato = idContato;
        this.role = role;
    }

//UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + this.role.name());
    }


    @Override
    public String getUsername() {
        return this.nome;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}