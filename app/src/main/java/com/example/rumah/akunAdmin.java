package com.example.rumah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class akunAdmin extends AppCompatActivity {
    ImageButton back;
    Button out,editAkun;
    Intent dahsboard,keluar,edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun_admin);
        back = (ImageButton) findViewById(R.id.back);
        out = (Button) findViewById(R.id.signOut);
        editAkun = (Button) findViewById(R.id.editAkun);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dahsboard = new Intent(akunAdmin.this, dashboardAdmin.class);
                        akunAdmin.this.startActivity(dahsboard);
                        finish();
                    }
                });
            }
        });

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog customDialog = new customDialog(akunAdmin.this);
                keluar = new Intent(akunAdmin.this, login.class);
                customDialog.startDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        customDialog.dismissDialog();
                        akunAdmin.this.startActivity(keluar);
                        finish();
                    }
                },3000);
            }
        });

        editAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editAkun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        edit = new Intent(akunAdmin.this, editAkunAdmin.class);
                        akunAdmin.this.startActivity(edit);
                        finish();
                    }
                });
            }
        });
    }
}