package com.example.rumah.penjual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.rumah.R;
import com.example.rumah.data.local.SharedPref;
import com.example.rumah.data.network.ApiClient;
import com.example.rumah.data.network.EndPoint;
import com.example.rumah.data.network.response.get_user.Data;
import com.example.rumah.data.network.response.get_user.ResponseGetUser;
import com.example.rumah.data.network.response.success.ResponseSuccess;
import com.example.rumah.dialog.CustomDialog;
import com.example.rumah.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class akunPenjual extends AppCompatActivity {
    ImageButton back;
    Button out,editAkun;
    Intent dahsboard,edit;
    TextView tv_alamat_acc, tv_telp_acc, tv_email_acc, tv_name_acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun_penjual);
        back = (ImageButton) findViewById(R.id.back);
        out = (Button) findViewById(R.id.signOut);
        editAkun = (Button) findViewById(R.id.editAkun);
        tv_alamat_acc = (TextView) findViewById(R.id.tv_alamat_acc);
        tv_telp_acc = (TextView) findViewById(R.id.tv_telp_acc);
        tv_email_acc = (TextView) findViewById(R.id.tv_email_acc);
        tv_name_acc = (TextView) findViewById(R.id.tv_name_acc);

        getUser();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dahsboard = new Intent(akunPenjual.this, DashboardPenjual.class);
                        akunPenjual.this.startActivity(dahsboard);
                        finish();
                    }
                });
            }
        });

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPref.logout(akunPenjual.this);
                finish();
                Intent intent = new Intent(akunPenjual.this, Login.class);
                akunPenjual.this.startActivity(intent);
            }
        });

        editAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit = new Intent(akunPenjual.this, editAkunPenjual.class);
                akunPenjual.this.startActivity(edit);
            }
        });
    }

    private void getUser() {
        String idPengguna = SharedPref.getIdPengguna(getApplicationContext());
        if(!idPengguna.isEmpty()){
        EndPoint endPoint = ApiClient.getClient().create(EndPoint.class);
        Call<ResponseGetUser> call = endPoint.getUser(idPengguna);

        call.enqueue(new Callback<ResponseGetUser>() {
            @Override
            public void onResponse(Call<ResponseGetUser> call, Response<ResponseGetUser> response) {
                Data acc = null;
                if (response.body() != null) {
                    acc = response.body().getData();
                    tv_alamat_acc.setText(acc.getAlamat());
                    tv_name_acc.setText(acc.getNama());
                    tv_telp_acc.setText(acc.getTelp());
                    tv_email_acc.setText(acc.getEmail());
                }
            }

            @Override
            public void onFailure(Call<ResponseGetUser> call, Throwable t) {

            }
        });
        }
    }
}