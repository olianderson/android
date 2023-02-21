package com.example.testapp.model;

import java.util.UUID;

public class Usuario {
    /** Atributos do usuário
     */
    private UUID codigo;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String caminhoFoto;

    /** Construtor
     */
    public Usuario() {
    }
    public Usuario(String nome, String sobrenome, String email, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
    }
    public Usuario(Usuario usuario) {
    }

    /** Getters and Setters
     */
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

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
}
