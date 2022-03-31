package com.example.rumah;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText username, password;
    Button signIn;
    TextView signUp;
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

                String usernameKey = username.getText().toString();
                String passwordKey = password.getText().toString();

                if (usernameKey.equals("admin") && passwordKey.equals("123")){
                    //jika login berhasil
                    Toast.makeText(getApplicationContext(), "Sign In Success",
                            Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(login.this, dashboardAdmin.class);
                    login.this.startActivity(in);
                    finish();
                }else  if (usernameKey.equals("nadya") && passwordKey.equals("123")){
                    //jika login berhasil
                    Toast.makeText(getApplicationContext(), "Sign In Success",
                            Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(login.this, dashboardPembeli.class);
                    login.this.startActivity(in);
                    finish();
                }else {
                    //jika login gagal
                    AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                    builder.setMessage("Incorrect username or password !")
                            .setNegativeButton("Retry", null).create().show();
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
