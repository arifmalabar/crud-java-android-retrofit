package com.example.crudretrofit.model;

import java.util.List;

public class PegawaiResponse {
    private int status;
    private String pesan;
    private List<Pegawai> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<Pegawai> getData() {
        return data;
    }

    public void setData(List<Pegawai> data) {
        this.data = data;
    }
}
