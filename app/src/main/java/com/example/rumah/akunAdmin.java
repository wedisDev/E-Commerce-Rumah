package com.example.rumah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class akunAdmin extends AppCompatActivity {
    ImageButton back;
    Button out,edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun_admin);
        back = (ImageButton) findViewById(R.id.back);
        out = (Button) findViewById(R.id.signOut);
        edit = (Button) findViewById(R.id.edit);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent dahsboard = new Intent(akunAdmin.this, dashboardAdmin.class);
                        akunAdmin.this.startActivity(dahsboard);
                        finish();
                    }
                });
            }
        });

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent out = new Intent(akunAdmin.this, login.class);
                        akunAdmin.this.startActivity(out);
                        finish();
                    }
                });
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent edit = new Intent(akunAdmin.this, editAkunAdmin.class);
                        akunAdmin.this.startActivity(edit);
                        finish();
                    }
                });
            }
        });
    }
}