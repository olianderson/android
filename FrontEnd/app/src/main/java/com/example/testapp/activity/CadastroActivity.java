package com.example.testapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapp.R;
import com.example.testapp.api.DataService;
import com.example.testapp.model.Usuario;
import com.example.testapp.api.RetrofitiCliente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoSobrenome, campoEmail, campoSenha;
    private Button botaoCadastrar;

    private Usuario usuario;

    private Retrofit retrofit;
    private DataService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializarComponentes();

        // Cadastrar usuario
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoNome = campoNome.getText().toString();
                String textoSobrenome = campoSobrenome.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                // Validar campos preenchidos
                if (!textoNome.isEmpty()) {
                    if (!textoSobrenome.isEmpty()) {
                        if (!textoEmail.isEmpty()) {
                            if (!textoSenha.isEmpty()) {

                                usuario = new Usuario();

                                usuario.setNome(textoNome);
                                usuario.setSobrenome(textoSobrenome);
                                usuario.setEmail(textoEmail);
                                usuario.setSenha(textoSenha);

                                cadastrar(usuario);

                            } else {
                                Toast.makeText(CadastroActivity.this, "Preencha o campo senha", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(CadastroActivity.this, "Preencha o campo e-mail", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CadastroActivity.this, "Preencha o campo sobrenome", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroActivity.this, "Preencha o campo nome", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /**
     * Método responsável por cadastrar usuário com e-mail e senha
     * e fazer validações ao fazer o cadastro
     */
    public void  cadastrar (Usuario usuario) {

        inicializarComponentes();

        Call<Usuario> call = RetrofitiCliente
                .getInstance()
                .getApi()
                .cadastrar(usuario);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Usuario cadastroResposta = response.body();

                    campoNome.setText(cadastroResposta.getNome());
                    campoSobrenome.setText(cadastroResposta.getSobrenome());
                    campoEmail.setText(cadastroResposta.getEmail());
                    campoSenha.setText(cadastroResposta.getSenha());

                    Toast.makeText(CadastroActivity.this, "Create account sucessful!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();

                } else {
                    Toast.makeText(CadastroActivity.this, "Couldn't created account", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(CadastroActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void inicializarComponentes () {
        campoNome = findViewById(R.id.editCadastrarNome);
        campoSobrenome = findViewById(R.id.editCadastrarSobrenome);
        campoEmail = findViewById(R.id.editCadastrarEmail);
        campoSenha = findViewById(R.id.editCadastrarSenha);

        botaoCadastrar = findViewById(R.id.buttonRealizarCadastro);
    }
}