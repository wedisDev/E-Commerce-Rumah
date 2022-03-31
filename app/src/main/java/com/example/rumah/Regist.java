package com.example.rumah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Regist extends AppCompatActivity {
    Button signUp;
    ImageButton cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        signUp = (Button) findViewById(R.id.signUp);

        customDialog customDialog = new customDialog(Regist.this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.startDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        customDialog.dismissDialog();
                        Intent login = new Intent(Regist.this, login.class);
                        Regist.this.startActivity(login);
                        finish();
                    }
                },4000);
            }
        });



    }
}