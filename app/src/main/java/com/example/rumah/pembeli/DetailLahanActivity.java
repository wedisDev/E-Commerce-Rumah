package com.example.rumah.pembeli;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rumah.R;
import com.example.rumah.data.Constant;
import com.example.rumah.data.local.SharedPref;
import com.example.rumah.data.network.ApiClient;
import com.example.rumah.data.network.EndPoint;
import com.example.rumah.data.network.response.get_rumah.DataItem;
import com.example.rumah.data.network.response.success.ResponseSuccess;

import retrofit2.Call;

public class DetailLahanActivity extends AppCompatActivity {

    TextView tv_detail_judul, tv_detail_alamat, tv_detail_harga, tv_detail_desc, tv_detail_pemilik, tv_detailTgl;
    Button btn_detail_beli;
    ImageButton back_button;
    ImageView iv_detail_gambar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lahan);

        DataItem mr = (DataItem) getIntent().getSerializableExtra("data");
        Boolean isPenjual = getIntent().getExtras().getBoolean("penjual");

        tv_detail_judul = findViewById(R.id.tv_detail_judul);
        tv_detailTgl = findViewById(R.id.tv_detail_tgl);
        tv_detail_harga = findViewById(R.id.tv_detail_harga);
        tv_detail_desc = findViewById(R.id.tv_detail_desc);
        iv_detail_gambar = findViewById(R.id.iv_detail_gambar);
        tv_detail_pemilik = findViewById(R.id.tv_detail_pemilik);
        btn_detail_beli = findViewById(R.id.btn_detail_beli);
        back_button = findViewById(R.id.backButton);

        Glide.with(getApplicationContext())
                .load(Constant.baseImageURL + mr.getGambar())
                .centerCrop()
                .into(iv_detail_gambar);
        tv_detail_pemilik.setText( mr.getPenjual());
        tv_detail_judul.setText(mr.getJudulRumah());
        tv_detail_harga.setText(mr.getHargaRumah());
        tv_detail_desc.setText(mr.getDescRumah());
        tv_detailTgl.setText(mr.getTgl());

        if(isPenjual){
            btn_detail_beli.setVisibility(View.GONE);
        } else{
            btn_detail_beli.setVisibility(View.VISIBLE);
        }
        String idPengguna = SharedPref.getIdPengguna(getApplicationContext());

        btn_detail_beli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EndPoint endPoint = ApiClient.getClient().create(EndPoint.class);
                Call<ResponseSuccess> call = endPoint.beliRumah(idPengguna, String.valueOf(mr.getId()),mr.getEmailPenjual());
                call.enqueue(new retrofit2.Callback<ResponseSuccess>() {
                    @Override
                    public void onResponse(Call<ResponseSuccess> call, retrofit2.Response<ResponseSuccess> response) {
                        if(response.body().getMessage().equals("OK")){
                            Toast.makeText(view.getContext(), "Sukses", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(view.getContext(), "data sudah tersedia", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseSuccess> call, Throwable t) {
                        Log.d("response", t.getMessage());
                    }
                });
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}