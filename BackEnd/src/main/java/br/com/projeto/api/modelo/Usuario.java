package br.com.projeto.api.modelo;

import java.io.Serializable;

import java.util.UUID;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID codigo;

    // @Column(nullable = false)
    private String nome;

    // @Column(nullable = false)
    private String sobrenome;

    @Email(message = "Insira uma e-mail válido!")
    @NotBlank(message = "Campo e-mail é obrigatório!")
    @Column(nullable = false, unique = true)
    private String email;
    
    @NotBlank(message = "Campo senha é obrigatório!")
    @Column(nullable = false)
    private String senha;
    
    // Constructors
    public Usuario() {
    }

    //Getts and Settrs
    public UUID getCodigo() {
        return codigo;
    }
    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
