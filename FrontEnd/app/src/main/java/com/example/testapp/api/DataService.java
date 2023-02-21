package com.example.testapp.api;

import com.example.testapp.model.LoginUsuario;
import com.example.testapp.model.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/** Classe responsável pela comunicação com a api
 */
public interface DataService {

    @POST("cadastrar")
    Call<Usuario> cadastrar(@Body Usuario usuario);

    @POST("login")
    Call<Usuario> login(@Body Usuario usuario);


}
