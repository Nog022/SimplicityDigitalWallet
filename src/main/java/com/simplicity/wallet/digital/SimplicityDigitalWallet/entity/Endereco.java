package com.simplicity.wallet.digital.SimplicityDigitalWallet.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "logradouro", length = 100)
    private String logradouro;

    @Column(name = "numero", length = 20)
    private String numero;

    @Column(name = "complemento", length = 50)
    private String complemento;

    @Column(name = "bairro", length = 50)
    private String bairro;

    @Column(name = "cidade", length = 50)
    private String cidade;

    @Column(name = "estado", length = 2)
    private String estado;

    @Column(name = "cep", length = 10)
    private String cep;

    // Getters e Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//    public String getLogradouro() { return logradouro; }
//    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
//    public String getNumero() { return numero; }
//    public void setNumero(String numero) { this.numero = numero; }
//    public String getComplemento() { return complemento; }
//    public void setComplemento(String complemento) { this.complemento = complemento; }
//    public String getBairro() { return bairro; }
//    public void setBairro(String bairro) { this.bairro = bairro; }
//    public String getCidade() { return cidade; }
//    public void setCidade(String cidade) { this.cidade = cidade; }
//    public String getEstado() { return estado; }
//    public void setEstado(String estado) { this.estado = estado; }
//    public String getCep() { return cep; }
//    public void setCep(String cep) { this.cep = cep; }

}
