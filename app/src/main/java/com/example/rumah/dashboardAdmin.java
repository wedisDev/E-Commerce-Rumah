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

        signOut = ( Button) findViewById(R.id.signOut);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        d = findViewById(R.id.d);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.a:
                Intent list = new Intent(dashboardAdmin.this, daftarRumah.class);
                dashboardAdmin.this.startActivity(list);
                finish();
                break;
            case R.id.b:
                Intent jual = new Intent(dashboardAdmin.this, iklanRumah.class);
                dashboardAdmin.this.startActivity(jual);
                finish();
                break;
            case R.id.c:
                Intent update = new Intent(dashboardAdmin.this, updateRumah.class);
                dashboardAdmin.this.startActivity(update);
                finish();
                break;
            case R.id.d:
                Intent akun = new Intent(dashboardAdmin.this, akunAdmin.class);
                dashboardAdmin.this.startActivity(akun);
                finish();
                break;
        }
    }
}