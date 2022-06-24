package com.example.rumah.model;

public class ModelUpdate {
    private String judul,alamat,kamar,mandi;

    public ModelUpdate(String judul, String alamat, String kamar, String mandi){
        this.judul=judul;
        this.alamat=alamat;
        this.kamar=kamar;
        this.mandi=mandi;
    }
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getkamar() {
        return kamar;
    }

    public void setKamar(String kamar) {
        this.kamar = kamar;
    }

    public String getmandi() {
        return mandi;
    }

    public void setMandi(String mandi) {
        this.mandi = mandi;
    }
}

