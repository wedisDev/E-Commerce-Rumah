package com.example.rumah.penjual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rumah.R;

public class DashboardPenjual extends AppCompatActivity implements View.OnClickListener {
    Button signOut;
    CardView a,b,c,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_penjual);

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
                Intent list = new Intent(DashboardPenjual.this, daftarRumah.class);
                DashboardPenjual.this.startActivity(list);
                finish();
                break;
            case R.id.b:
                Intent jual = new Intent(DashboardPenjual.this, iklanRumah.class);
                DashboardPenjual.this.startActivity(jual);
                finish();
                break;
            case R.id.c:
                Intent update = new Intent(DashboardPenjual.this, updateRumah.class);
                DashboardPenjual.this.startActivity(update);
                finish();
                break;
            case R.id.d:
                Intent akun = new Intent(DashboardPenjual.this, akunPenjual.class);
                DashboardPenjual.this.startActivity(akun);
                finish();
                break;
        }
    }
}