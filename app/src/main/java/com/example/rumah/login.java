package com.example.rumah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class login extends AppCompatActivity {
    EditText username, password;
    Button signIn;
    TextView signUp;
    Intent in,admin;
    String usernameKey,passwordKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signIn = (Button)findViewById(R.id.signIn);
        signUp = (TextView) findViewById(R.id.signUp);



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 usernameKey = username.getText().toString();
                 passwordKey = password.getText().toString();

                if (username.getText().toString().trim().isEmpty() || password.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Lengkapi data untuk masuk !!",
                            Toast.LENGTH_SHORT).show();
                } else  {
                    if (usernameKey.equals("joko") && passwordKey.equals("123")){
                        //jika login berhasil
                        Toast.makeText(getApplicationContext(), "Selamat Datang !", Toast.LENGTH_SHORT).show();
                         admin = new Intent(login.this, dashboardAdmin.class);
                        login.this.startActivity(admin);
                        finish();
                    }else  if (usernameKey.equals("nadya") && passwordKey.equals("123")){
                        //jika login berhasil
                        Toast.makeText(getApplicationContext(), "Selamat Datang !", Toast.LENGTH_SHORT).show();
                         in = new Intent(login.this, dashboardPembeli.class);
                        login.this.startActivity(in);
                        finish();
                    }else {
                        //jika login gagal
                        AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                        builder.setMessage("Data yang anda input salah !")
                                .setNegativeButton("Retry", null).create().show();
                    }
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent up = new Intent(login.this, Regist.class);
                login.this.startActivity(up);
                finish();
            }
        });
    }
}