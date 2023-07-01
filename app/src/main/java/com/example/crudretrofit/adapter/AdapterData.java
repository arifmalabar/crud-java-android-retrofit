package com.example.crudretrofit.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudretrofit.API.PegawaiInterface;
import com.example.crudretrofit.MainActivity;
import com.example.crudretrofit.R;
import com.example.crudretrofit.UbahPegawai;
import com.example.crudretrofit.configuration.RetroServer;
import com.example.crudretrofit.model.MessageResponse;
import com.example.crudretrofit.model.Pegawai;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private Context context;
    private List<Pegawai> list_pegawai = new ArrayList<>();

    public AdapterData(Context context, List<Pegawai> list_pegawai) {
        this.context = context;
        this.list_pegawai = list_pegawai;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        Pegawai pgw = list_pegawai.get(position);
        holder.txtId.setText(String.valueOf(pgw.getId()));
        holder.txtNama.setText(pgw.getNama());
        holder.txtGolongan.setText(pgw.getGolongan());
        holder.txtAlamat.setText(pgw.getAlamat());
    }

    @Override
    public int getItemCount() {
        return list_pegawai.size();
    }
    public class HolderData extends RecyclerView.ViewHolder{
        TextView txtNama, txtAlamat, txtGolongan, txtId;
        String nama, alamat, golongan;
        int id;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txt_id);
            txtNama = itemView.findViewById(R.id.txt_nama);
            txtAlamat = itemView.findViewById(R.id.txt_alamat);
            txtGolongan = itemView.findViewById(R.id.txt_golongan);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    id = Integer.parseInt(txtId.getText().toString());
                    nama = txtNama.getText().toString();
                    alamat = txtAlamat.getText().toString();
                    golongan = txtGolongan.getText().toString();
                    id = Integer.parseInt(txtId.getText().toString());
                    ubahData();
                }
            });
        }
        public void hapusData()
        {
            PegawaiInterface pi = RetroServer.konekRetrofit().create(PegawaiInterface.class);
            Call<MessageResponse> response_data = pi.hapusData(id);
            response_data.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    Toast.makeText(context, "Berhasil mengahpus data"+String.valueOf(id), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {

                }
            });
        }
        public void ubahData()
        {
            Intent intent = new Intent(context.getApplicationContext(), UbahPegawai.class);
            intent.putExtra("id", id);
            intent.putExtra("nama", nama);
            intent.putExtra("alamat", alamat);
            intent.putExtra("golongan", golongan);
            context.getApplicationContext().startActivity(intent);
        }
    }
}
