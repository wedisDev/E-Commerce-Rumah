package com.example.rumah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class dashboardAdmin extends AppCompatActivity implements View.OnClickListener {
    Button signOut;
    CardView a,b,c,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);

        signOut = findViewById(R.id.signOut);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        d = findViewById(R.id.d);

        customDialog customDialog = new customDialog(dashboardAdmin.this);
        Intent out = new Intent(dashboardAdmin.this, login.class);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.startDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        customDialog.dismissDialog();
                        dashboardAdmin.this.startActivity(out);
                        finish();
                    }
                },3000);
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.a:
                Intent update = new Intent(dashboardAdmin.this, updateRumah.class);
                dashboardAdmin.this.startActivity(update);
                finish();
                break;
            case R.id.b:
                Intent jual = new Intent(dashboardAdmin.this, iklanRumah.class);
                dashboardAdmin.this.startActivity(jual);
                finish();
                break;
            case R.id.c:
                Toast.makeText(this, "Halaman C", Toast.LENGTH_SHORT).show();
                break;
            case R.id.d:
                Toast.makeText(this, "Halaman D", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}