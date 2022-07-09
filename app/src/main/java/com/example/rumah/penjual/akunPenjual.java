package com.example.rumah.penjual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.rumah.R;
import com.example.rumah.data.local.SharedPref;
import com.example.rumah.dialog.CustomDialog;
import com.example.rumah.Login;

public class akunPenjual extends AppCompatActivity {
    ImageButton back;
    Button out,editAkun;
    Intent dahsboard,edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun_penjual);
        back = (ImageButton) findViewById(R.id.back);
        out = (Button) findViewById(R.id.signOut);
        editAkun = (Button) findViewById(R.id.editAkun);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dahsboard = new Intent(akunPenjual.this, DashboardPenjual.class);
                        akunPenjual.this.startActivity(dahsboard);
                        finish();
                    }
                });
            }
        });

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPref.logout(akunPenjual.this);
                finish();
                Intent intent = new Intent(akunPenjual.this, Login.class);
                akunPenjual.this.startActivity(intent);
            }
        });

        editAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editAkun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        edit = new Intent(akunPenjual.this, editAkunPenjual.class);
                        akunPenjual.this.startActivity(edit);
                        finish();
                    }
                });
            }
        });
    }
}