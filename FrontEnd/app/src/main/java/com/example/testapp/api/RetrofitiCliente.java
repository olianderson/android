package com.example.testapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitiCliente {

    private static  final String BASE_URL = "http://192.168.100.215:8080/math-school/";
    private static RetrofitiCliente mInstance;
    private Retrofit retrofit;

    private  RetrofitiCliente() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitiCliente getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitiCliente();
        }
        return mInstance;
    }

    public DataService getApi() {
        return retrofit.create(DataService.class);
    }
}
