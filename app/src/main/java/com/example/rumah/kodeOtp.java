package com.example.rumah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class kodeOtp extends AppCompatActivity {
    EditText kode1, kode2, kode3, kode4;
    Button daftar;
    TextView send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kode_otp);

        kode1 = (EditText) findViewById(R.id.kode1);
        kode2 = (EditText) findViewById(R.id.kode2);
        kode3 = (EditText) findViewById(R.id.kode3);
        kode4 = (EditText) findViewById(R.id.kode4);
        daftar = (Button)findViewById(R.id.daftar);
        send = (TextView) findViewById(R.id.send);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog customDialog = new customDialog(kodeOtp.this);
                Intent daftar = new Intent(kodeOtp.this, login.class);

                customDialog.startDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        customDialog.dismissDialog();
                        finish();
                    }
                },8000);
                kodeOtp.this.startActivity(daftar);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Kode OTP Terkirim",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}