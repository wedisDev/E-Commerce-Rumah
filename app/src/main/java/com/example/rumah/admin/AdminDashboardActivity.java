package com.example.rumah.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.rumah.R;
import com.example.rumah.adapter.AdapterAdmin;
import com.example.rumah.data.network.ApiClient;
import com.example.rumah.data.network.EndPoint;
import com.example.rumah.data.network.response.get_dashboard_admin.ResponseDashboardAdmin;
import com.example.rumah.penjual.akunPenjual;

import retrofit2.Call;

public class AdminDashboardActivity extends AppCompatActivity {
    RecyclerView rv_dashboard;
    ImageButton imv_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        rv_dashboard = findViewById(R.id.rv_dashboard);
        rv_dashboard.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        imv_profile = findViewById(R.id.imv_profile);
        getDashboard();

        imv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), akunPenjual.class));
            }
        });
    }

    private void getDashboard() {
        ApiClient.getClient().create(EndPoint.class).getDashboardAdmin().enqueue(new retrofit2.Callback<ResponseDashboardAdmin>() {
            @Override
            public void onResponse(Call<ResponseDashboardAdmin> call, retrofit2.Response<ResponseDashboardAdmin> response) {
                if (response.isSuccessful()){
                    rv_dashboard.setAdapter(new AdapterAdmin(response.body().getData(), getApplicationContext()));
                }
            }

            @Override
            public void onFailure(Call<ResponseDashboardAdmin> call, Throwable t) {

            }
        });
    }
}