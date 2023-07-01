package com.example.crudretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudretrofit.API.PegawaiInterface;
import com.example.crudretrofit.configuration.RetroServer;
import com.example.crudretrofit.model.MessageResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahPegawai extends AppCompatActivity {

    private EditText txtNama, txtAlamat, txtGolongan;
    private Button btnUpdate, btnHapus;
    private int id;
    private PegawaiInterface pi;

    public UbahPegawai() {
        pi = RetroServer.konekRetrofit().create(PegawaiInterface.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_pegawai);
        setConfData();
        setData();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doUpdate();
            }
        });
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteMesssage();
            }
        });
    }
    private void setConfData()
    {
        txtNama = findViewById(R.id.txt_ubah_nama);
        txtAlamat = findViewById(R.id.txt_ubah_alamat);
        txtGolongan = findViewById(R.id.txt_ubah_golongan);
        btnUpdate = findViewById(R.id.button_ubah_pegawai);
        btnHapus = findViewById(R.id.button_hapus_pegawai);
    }
    private void setData()
    {
        Bundle bd = getIntent().getExtras();
        id = bd.getInt("id");
        txtNama.setText(bd.getString("nama"));
        txtAlamat.setText(bd.getString("alamat"));
        txtGolongan.setText(bd.getString("golongan"));
    }
    private void doUpdate()
    {
        String nama = txtNama.getText().toString();
        String golongan = txtGolongan.getText().toString();
        String alamat = txtAlamat.getText().toString();
        if(nama.trim().equals(""))
        {
            Toast.makeText(this, "Nama anda masih kosong", Toast.LENGTH_SHORT).show();
        } else if(golongan.trim().equals("")){
            Toast.makeText(this, "Golongan anda masih kosong", Toast.LENGTH_SHORT).show();
        } else if(alamat.trim().equals("")){
            Toast.makeText(this, "Alamat anda masih kosong", Toast.LENGTH_SHORT).show();
        } else{
            Call<MessageResponse> data_response = pi.updateData(id, nama, alamat, golongan);
            data_response.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    Toast.makeText(UbahPegawai.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {
                    Toast.makeText(UbahPegawai.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            finish();
        }
    }
    private void showDeleteMesssage()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Apakah anda yakin ingin menghapus data?");
        alert.setCancelable(true);
        alert.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(UbahPegawai.this, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                doDeleteData();
            }
        });
        alert.show();
    }
    private void doDeleteData()
    {

        Call<MessageResponse> hapus_data = pi.hapusData(id);
        hapus_data.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                Toast.makeText(UbahPegawai.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(UbahPegawai.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}