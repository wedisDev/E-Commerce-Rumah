package com.example.rumah.model;

public class modelNotif {
    private String notif,waktu,tanggal;

    modelNotif(String notif,String waktu,String tanggal){
        this.notif=notif;
        this.waktu=waktu;
        this.tanggal=tanggal;
    }
    public String getNotif() {
        return notif;
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
