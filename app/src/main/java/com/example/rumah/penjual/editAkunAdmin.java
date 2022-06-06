package com.example.rumah.penjual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rumah.R;
import com.example.rumah.dialog.customDialog;

public class editAkunAdmin extends AppCompatActivity {
    EditText nama,alamat,telp,email,username,password;
    Button simpan;
    ImageButton back;
    ImageView pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun_admin);

        simpan=(Button) findViewById(R.id.simpan);
        back = (ImageButton) findViewById(R.id.back);
        nama = (EditText) findViewById(R.id.nama);
        alamat = (EditText) findViewById(R.id.alamat);
        telp = (EditText) findViewById(R.id.telp);
        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        pic =(ImageView) findViewById(R.id.profile);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent akun = new Intent(editAkunAdmin.this, akunAdmin.class);
                        editAkunAdmin.this.startActivity(akun);
                        finish();
                    }
                });
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast gambar = Toast.makeText(getApplicationContext(), "HAHAHA", Toast.LENGTH_SHORT);
                        gambar.setGravity(Gravity.CENTER, 0, 0);
                        gambar.show();
                    }
                });
                if (nama.getText().toString().trim().isEmpty() || alamat.getText().toString().trim().isEmpty()
                || telp.getText().toString().trim().isEmpty() || email.getText().toString().trim().isEmpty()
                || username.getText().toString().trim().isEmpty() || password.getText().toString().trim().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Data Tidak Boleh Ada yang Kosong !",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else{
                customDialog customDialog = new customDialog(editAkunAdmin.this);
                Intent simpan = new Intent(editAkunAdmin.this, akunAdmin.class);

                customDialog.startDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        customDialog.dismissDialog();
                        finish();
                    }
                },8000);
                editAkunAdmin.this.startActivity(simpan);
            }}
        });
    }
}