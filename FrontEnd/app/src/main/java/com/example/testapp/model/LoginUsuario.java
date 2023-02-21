package com.example.testapp.model;

public class LoginUsuario {

    private String email;
    private String senha;

    public LoginUsuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public LoginUsuario(LoginUsuario loginUsuario) {

    }

    public LoginUsuario() {

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
