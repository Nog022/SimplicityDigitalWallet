package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


import java.io.Serializable;
import java.util.Objects;

@Entity
public class PermissaoPerfil implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    @Id
    @ManyToOne
    @JoinColumn(name = "permissao_id")
    private Permissao permissao;

    // Getters e Setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissaoPerfil that = (PermissaoPerfil) o;
        return Objects.equals(perfil, that.perfil) &&
                Objects.equals(permissao, that.permissao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(perfil, permissao);
    }
}
