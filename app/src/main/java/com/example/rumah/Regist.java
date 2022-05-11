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

        otpDialog otpDialog = new otpDialog(Regist.this);
        Intent otp = new Intent(Regist.this, kodeOtp.class);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString().trim().isEmpty()) {
                    name.setError("Please fill out this field !");
                } else  if (address.getText().toString().trim().isEmpty()) {
                    address.setError("Please fill out this field !");
                }else  if (phone.getText().toString().trim().isEmpty()) {
                    phone.setError("Please fill out this field !");
                }else  if (username.getText().toString().trim().isEmpty()) {
                    username.setError("Please fill out this field !");
                }else  if (pass.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Lengkapi data!",
                            Toast.LENGTH_SHORT).show();

                }else {
                    otpDialog.startDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            otpDialog.otpDismiss();
                            Regist.this.startActivity(otp);
                            finish();
                        }
                    },3000);
                }


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(Regist.this, login.class);
                Regist.this.startActivity(login);
                finish();
            }
        });


    }

}