package com.example.rumah.penjual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.rumah.R;
import com.example.rumah.adapter.adapterRumah;
import com.example.rumah.model.modelRumah;

import java.util.ArrayList;
import java.util.List;

public class daftarRumah extends AppCompatActivity {
    ImageButton back;
    RecyclerView rcvRumah;
//    ArrayList dataRumah;
    private ArrayList<modelRumah> dataRumah = new ArrayList<>(3);
//    List<modelRumah> dataRumah;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_rumah);

        back = (ImageButton) findViewById(R.id.back);
        rcvRumah = (RecyclerView) findViewById(R.id.rcvRumah);

        rcvRumah.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dataRumah = new ArrayList<>(3);
        dataRumah.add(new modelRumah("Rumah Ceunah","Gubeng Barat","2","1"));
        dataRumah.add(new modelRumah("Rumah Membahana","Gubeng Barat","2","1"));
        dataRumah.add(new modelRumah("Rumah Ceunah","Gubeng Barat","2","1"));

        Log.d("Rumah", "onCreate: "+dataRumah.toString());
        adapterRumah rumah= new adapterRumah(dataRumah);
        rcvRumah.setAdapter(rumah);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dahsboard = new Intent(daftarRumah.this, dashboardAdmin.class);
                daftarRumah.this.startActivity(dahsboard);
                finish();
            }
        });

//        return ;
    }
}