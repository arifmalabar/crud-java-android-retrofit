package com.example.crudretrofit;

import com.example.crudretrofit.API.PegawaiInterface;
import com.example.crudretrofit.configuration.RetroServer;
import com.example.crudretrofit.model.PegawaiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainTes {
    public static void main(String[] args) {
        PegawaiInterface pi = RetroServer.konekRetrofit().create(PegawaiInterface.class);
        Call<PegawaiResponse> tampildata = pi.getData();
        tampildata.enqueue(new Callback<PegawaiResponse>() {
            @Override
            public void onResponse(Call<PegawaiResponse> call, Response<PegawaiResponse> response) {
                System.out.println(response.body().getPesan());
            }

            @Override
            public void onFailure(Call<PegawaiResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
