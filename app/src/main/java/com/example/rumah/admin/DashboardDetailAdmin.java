package com.example.rumah.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rumah.R;
import com.example.rumah.data.Constant;
import com.example.rumah.data.network.response.get_dashboard_admin.DataItem;

public class DashboardDetailAdmin extends AppCompatActivity {

    TextView statusTransaksi, judulRumah, pemilikRumah, pembeliRumah, alamatRumah, deskripsiRumah, hargaRumah;
    ImageView picHome;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_detail_admin);

        DataItem mr = (DataItem) getIntent().getSerializableExtra("data");

//        tglTransaksi = findViewById(R.id.tgl_transaksi);
        statusTransaksi = findViewById(R.id.status_transaksi);
        judulRumah = findViewById(R.id.judul_rumah);
        pemilikRumah = findViewById(R.id.pemilikRUmah);
        pembeliRumah = findViewById(R.id.pembeliRumah);
        alamatRumah = findViewById(R.id.alamatRumah);
        deskripsiRumah = findViewById(R.id.deskripsiRumah);
        hargaRumah = findViewById(R.id.hargaRumah);
        picHome = findViewById(R.id.pic_home);

        Glide.with(getApplicationContext())
                .load(Constant.baseImageURL + mr.getGambar())
                .into(picHome);

        String status = mr.getStatus() == null ? "tersedia" : mr.getStatus();
        statusTransaksi.setText(status);
        pemilikRumah.setText(mr.getPenjual());
        pembeliRumah.setText(mr.getPembeli());
        judulRumah.setText(mr.getJudulRumah());
        alamatRumah.setText(mr.getKelurahan()+" "+mr.getAlamatRumah());
        hargaRumah.setText( mr.getHargaRumah());
        deskripsiRumah.setText(mr.getDescRumah());
    }
}