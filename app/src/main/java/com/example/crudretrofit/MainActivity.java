package com.example.crudretrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.crudretrofit.API.PegawaiInterface;
import com.example.crudretrofit.adapter.AdapterData;
import com.example.crudretrofit.auth.AuthController;
import com.example.crudretrofit.configuration.RetroServer;
import com.example.crudretrofit.model.Login;
import com.example.crudretrofit.model.Pegawai;
import com.example.crudretrofit.model.PegawaiResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter addData;
    private RecyclerView.LayoutManager lmData;
    private List<Pegawai> listPegawai = new ArrayList<>();
    private ProgressBar pb;
    private SwipeRefreshLayout sp;
    private FloatingActionButton btnAddPegawai;
    private SharedPreferences sharedPreferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrifeData();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuLogout:
                getApplicationContext().getSharedPreferences("shared_prefs", Context.MODE_PRIVATE).edit().clear().apply();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            default:
                getApplicationContext().getSharedPreferences("shared_prefs", Context.MODE_PRIVATE).edit().clear().apply();
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent1);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Halaman Utama");
        rvData = findViewById(R.id.data);
        pb = findViewById(R.id.pb_data);
        sp = findViewById(R.id.sw);

        btnAddPegawai = findViewById(R.id.btn_toadddatapgw);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        retrifeData();
        sp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sp.setRefreshing(true);
                retrifeData();
                sp.setRefreshing(false);
            }
        });
        btnAddPegawai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TambahPegawai.class);
                startActivity(i);
            }
        });

    }
    private void retrifeData()
    {
        pb.setVisibility(View.VISIBLE);
        PegawaiInterface pi = RetroServer.konekRetrofit().create(PegawaiInterface.class);
        Call<PegawaiResponse> tampildata = pi.getData();
        tampildata.enqueue(new Callback<PegawaiResponse>() {
            @Override
            public void onResponse(Call<PegawaiResponse> call, Response<PegawaiResponse> response) {
                int kode = response.body().getStatus();
                String pesan = response.body().getPesan();
                Toast.makeText(MainActivity.this, "kode : "+kode+" Pesan: "+pesan+" ", Toast.LENGTH_SHORT).show();
                listPegawai = response.body().getData();
                addData = new AdapterData(MainActivity.this, listPegawai);
                rvData.setAdapter(addData);
                addData.notifyDataSetChanged();
                pb.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<PegawaiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal menghubungi server "+t.getMessage(), Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.VISIBLE);
            }
        });
    }
}