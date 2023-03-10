package com.example.testapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapp.R;
import com.example.testapp.api.RetrofitiCliente;
import com.example.testapp.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button botaoEntrar;

    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializarComponentes();
//        verificaUsuarioLogado();

        // Fazer login do usuario
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                if (!textoEmail.isEmpty()) {
                    if (!textoSenha.isEmpty()) {

                        usuario = new Usuario();

                        usuario.setSenha(textoSenha);
                        usuario.setEmail(textoEmail);

                        login(usuario);

                    } else {
                        Toast.makeText(LoginActivity.this,"Preencha a senha.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Preencha o e-mail.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /** Método responsável por validar usuário com e-mail e senha
     */
    public void login(Usuario usuario) {

        Call<Usuario> call = RetrofitiCliente
                .getInstance()
                .getApi()
                .login(usuario);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(LoginActivity.this, "Login sucessful!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();

                }else {
                    Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Throwable" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    /*// verifica se o usuário está logado
    public void verificaUsuarioLogado() {
        // fazer a chamada de API para obter as informações do usuário
        Call<Usuario> call = RetrofitiCliente
                .getInstance()
                .getApi()
                .login();

        Response<Usuario> response = call.execute();

        // verificar se a resposta foi bem-sucedida e se contém informações do usuário
        return response.isSuccessful() && response.body() != null;
    }*/

    public void abrirCadastro(View view) {
        Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(i);
        finish();
    }

    public void inicializarComponentes () {
        campoEmail = findViewById(R.id.editLoginEmail);
        campoSenha = findViewById(R.id.editLoginSenha);

        botaoEntrar = findViewById(R.id.buttonEntrar);
    }
}