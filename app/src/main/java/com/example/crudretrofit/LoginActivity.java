package com.example.crudretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudretrofit.API.LoginInterface;
import com.example.crudretrofit.configuration.RetroServer;
import com.example.crudretrofit.model.Login;
import com.example.crudretrofit.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView txtUsername, txtPassword;
    private Button btnProses;
    private SharedPreferences sp;
    private int user_id;

    @Override
    protected void onStart() {
        super.onStart();
        if(user_id != 0)
        {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        setDataVal();
        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }
    private void setDataVal() {
        txtUsername = findViewById(R.id.txtLoginUsername);
        txtPassword = findViewById(R.id.txtLoginPassword);
        btnProses = findViewById(R.id.btnLoginProses);
        sp = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        user_id = sp.getInt("id_user", 0);
    }
    private void doLogin() {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        if(username.trim().equals("")) {
            txtUsername.setError("Username harap diisi");
        } else if(password.trim().equals("")){
            txtPassword.setError("Password harap diisi");
        } else {
            LoginInterface loginInterface = RetroServer.konekRetrofit().create(LoginInterface.class);
            Call<LoginResponse> loginResponse = loginInterface.getDataLogin(username, password);
            loginResponse.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if(response.body().getStatus() > 0)
                    {
                        SharedPreferences.Editor editor = sp.edit();
                        for(Login login : response.body().getData())
                        {
                            editor.putInt("id_user", login.getId_user());
                            editor.putString("nama", login.getNama());
                            editor.putString("username", login.getUsername());
                            editor.putString("password", login.getPassword());
                            editor.apply();
                        }
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}