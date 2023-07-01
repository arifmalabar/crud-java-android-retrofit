package com.example.crudretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudretrofit.API.PegawaiInterface;
import com.example.crudretrofit.configuration.RetroServer;
import com.example.crudretrofit.model.MessageResponse;
import com.example.crudretrofit.model.Pegawai;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahPegawai extends AppCompatActivity {
    private EditText txtNama, txtGolongan, txtAlamat;
    private Button btnTambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pegawai);
        setConfData();
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doTambahData();
            }
        });
    }
    private void setConfData()
    {
        txtNama = findViewById(R.id.txt_tambah_nama);
        txtGolongan = findViewById(R.id.txt_tambah_golongan);
        txtAlamat = findViewById(R.id.txt_tambah_alamat);
        btnTambah = findViewById(R.id.button_tambah_pegawai);
    }
    private void doTambahData()
    {
        String nama = txtNama.getText().toString();
        String alamat = txtAlamat.getText().toString();
        String golongan = txtGolongan.getText().toString();
        if(nama.trim().equals("")){
            Toast.makeText(this, "Nama anda masih kosong", Toast.LENGTH_SHORT).show();
        } else if(alamat.trim().equals("")){
            Toast.makeText(this, "Alamat anda masih kosong", Toast.LENGTH_SHORT).show();
        } else if(golongan.trim().equals("")){
            Toast.makeText(this, "Golongan anda masih kosong", Toast.LENGTH_SHORT).show();
        } else {
            PegawaiInterface pi = RetroServer.konekRetrofit().create(PegawaiInterface.class);
            Call<MessageResponse> tambah_data = pi.tambahData(nama, alamat, golongan);
            tambah_data.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    Toast.makeText(TambahPegawai.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {
                    Toast.makeText(TambahPegawai.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}