package com.example.crudretrofit.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static Retrofit retrofit;
    public static Retrofit konekRetrofit()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                            .baseUrl(EndPoint.url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        }
        return retrofit;
    }
}
