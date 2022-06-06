package com.example.rumah.penjual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.rumah.R;
import com.example.rumah.adapter.adapterRumah;

import java.util.ArrayList;

public class daftarRumah extends AppCompatActivity {
    ImageButton back;
    RecyclerView rcvRumah;
    ArrayList dataRumah;
    View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_rumah);

        back = (ImageButton) findViewById(R.id.back);
        rcvRumah = (RecyclerView) findViewById(R.id.rcvRumah);


//        v = inflater.inflate(R.layout.list_rumah);
        rcvRumah.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dataRumah = new ArrayList<>(3);
        dataRumah.add(1,"uybb");
        rcvRumah.setAdapter(new adapterRumah(dataRumah));



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dahsboard = new Intent(daftarRumah.this, dashboardAdmin.class);
                daftarRumah.this.startActivity(dahsboard);
                finish();
            }
        });
    }
}