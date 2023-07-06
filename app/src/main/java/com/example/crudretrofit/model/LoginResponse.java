package com.example.crudretrofit.model;

import java.util.List;

public class LoginResponse {
    private int status;
    private String pesan;

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

    public List<Login> getData() {
        return data;
    }

    public void setData(List<Login> data) {
        this.data = data;
    }

    private List<Login> data;

}
