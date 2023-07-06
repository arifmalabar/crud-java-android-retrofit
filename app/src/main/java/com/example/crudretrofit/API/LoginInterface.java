package com.example.crudretrofit.API;

import com.example.crudretrofit.model.LoginResponse;
import com.example.crudretrofit.model.MessageResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoginInterface {
    @FormUrlEncoded
    @POST("login_admin_api.php")
    Call<LoginResponse> getDataLogin(@Field("username") String username, @Field("password") String password);
}
