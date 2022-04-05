package com.example.rumah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Regist extends AppCompatActivity {
    Button signUp,back;
    EditText name,address,phone,username,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        username = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        signUp =  findViewById(R.id.signUp);
        back = findViewById(R.id.back);



        customDialog customDialog = new customDialog(Regist.this);
        Intent login = new Intent(Regist.this, login.class);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill out name field !",
                            Toast.LENGTH_SHORT).show();
                } else  if (address.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill out address field !",
                            Toast.LENGTH_SHORT).show();
                }else  if (phone.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill out phone field !",
                            Toast.LENGTH_SHORT).show();
                }else  if (username.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill out username field !",
                            Toast.LENGTH_SHORT).show();
                }else  if (pass.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill out password field !",
                            Toast.LENGTH_SHORT).show();

                }else {
                    customDialog.startDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            customDialog.dismissDialog();
                            Regist.this.startActivity(login);
                            finish();
                            Toast.makeText(getApplicationContext(), "Regist Success ! ",
                                    Toast.LENGTH_SHORT).show();
                        }
                    },3000);
                }


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Regist.this.startActivity(login);
                finish();
            }
        });


    }
    private void registAccount(String name,String address,String phone,String username,String pass){

    }
}