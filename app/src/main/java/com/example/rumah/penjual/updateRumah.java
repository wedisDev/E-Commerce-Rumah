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
import com.example.rumah.adapter.adapterUpdate;
import com.example.rumah.model.modelRumah;
import com.example.rumah.model.modelUpdate;

import java.util.ArrayList;

public class updateRumah extends AppCompatActivity {
    ImageButton back;
    RecyclerView rcvupdaterumah;
    //    ArrayList dataRumah;
    private ArrayList<modelUpdate> updateRumah = new ArrayList<>(3);
    //    List<modelRumah> dataRumah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_rumah);

        back = (ImageButton) findViewById(R.id.back);
        rcvupdaterumah = (RecyclerView) findViewById(R.id.rcvupdaterumah);

        rcvupdaterumah.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        updateRumah = new ArrayList<>(3);
        updateRumah.add(new modelUpdate("Rumah A","Gubeng Grgr","2","1"));
        updateRumah.add(new modelUpdate("Rumah B","Gubeng Timt","1","1"));
        updateRumah.add(new modelUpdate("Rumah C","Gubeng Barat","5","2"));

        adapterUpdate rumah= new adapterUpdate(updateRumah);
        rcvupdaterumah.setAdapter(rumah);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dahsboard = new Intent(updateRumah.this, dashboardAdmin.class);
                updateRumah.this.startActivity(dahsboard);
                finish();
            }
        });
    }
}