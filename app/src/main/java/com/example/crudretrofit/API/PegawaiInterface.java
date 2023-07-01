package com.example.crudretrofit.API;

import android.os.Message;

import com.example.crudretrofit.model.MessageResponse;
import com.example.crudretrofit.model.Pegawai;
import com.example.crudretrofit.model.PegawaiResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PegawaiInterface {
    @GET("tampil_pegawai_api.php")
    Call<PegawaiResponse> getData();

    @FormUrlEncoded
    @POST("update_pegawai_api.php")
    Call<MessageResponse> updateData(@Query("id") int id, @Field("nama") String nama, @Field("alamat") String alamat, @Field("golongan") String golongan);

    @FormUrlEncoded
    @POST("add_pegawai_api.php")
    Call<MessageResponse> tambahData(@Field("nama") String nama, @Field("alamat") String alamat, @Field("golongan") String golongan);

    @GET("delete_pegawai_api.php")
    Call<MessageResponse> hapusData(@Query("id") int id);
}
