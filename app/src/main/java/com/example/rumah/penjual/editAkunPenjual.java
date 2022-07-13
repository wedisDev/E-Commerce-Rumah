package com.example.rumah.penjual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rumah.R;
import com.example.rumah.data.local.SharedPref;
import com.example.rumah.data.network.ApiClient;
import com.example.rumah.data.network.EndPoint;
import com.example.rumah.data.network.response.get_user.Data;
import com.example.rumah.data.network.response.get_user.ResponseGetUser;
import com.example.rumah.data.network.response.login.ResponseLogin;
import com.example.rumah.data.network.response.success.ResponseSuccess;
import com.example.rumah.dialog.CustomDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class editAkunPenjual extends AppCompatActivity {
    EditText nama,alamat,telp,email,username,password, rek;
    Button simpan;
    ImageButton back;
    ImageView pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun_penjual);

        simpan=(Button) findViewById(R.id.simpan);
        back = (ImageButton) findViewById(R.id.back);
        nama = (EditText) findViewById(R.id.nama);
        alamat = (EditText) findViewById(R.id.alamat);
        telp = (EditText) findViewById(R.id.telp);
        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        pic =(ImageView) findViewById(R.id.profile);
        rek =(EditText) findViewById(R.id.rek);

        getUser();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast gambar = Toast.makeText(getApplicationContext(), "HAHAHA", Toast.LENGTH_SHORT);
                        gambar.setGravity(Gravity.CENTER, 0, 0);
                        gambar.show();
                    }
                });
                if (nama.getText().toString().trim().isEmpty() || alamat.getText().toString().trim().isEmpty()
                || telp.getText().toString().trim().isEmpty() || email.getText().toString().trim().isEmpty()
                || username.getText().toString().trim().isEmpty() || password.getText().toString().trim().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Data Tidak Boleh Ada yang Kosong !",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else{
                    String idPengguna = SharedPref.getIdPengguna(getApplicationContext());
                    if(!idPengguna.isEmpty()) {
                        EndPoint endPoint = ApiClient.getClient().create(EndPoint.class);
                        Call<ResponseSuccess> call = endPoint.updateProfile(
                                idPengguna,
                                nama.getText().toString(),
                                alamat.getText().toString(),
                                telp.getText().toString(),
                                email.getText().toString(),
                                username.getText().toString(),
                                password.getText().toString(),
                                rek.getText().toString());
                        call.enqueue(new Callback<ResponseSuccess>() {
                            @Override
                            public void onResponse(Call<ResponseSuccess> call, Response<ResponseSuccess> response) {
                                if (response.body().getMessage().equals("OK")) {
                                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                                    Intent akun = new Intent(editAkunPenjual.this, akunPenjual.class);
                                    editAkunPenjual.this.startActivity(akun);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseSuccess> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }}
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
                        alamat.setText(acc.getAlamat());
                        nama.setText(acc.getNama());
                        username.setText(acc.getUsername());
                        telp.setText(acc.getTelp());
                        password.setText(acc.getPass());
                        email.setText(acc.getEmail());
                        rek.setText(acc.getRekening());
                    }
                }

                @Override
                public void onFailure(Call<ResponseGetUser> call, Throwable t) {

                }
            });
        }
    }
}