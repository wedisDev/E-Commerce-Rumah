package com.example.rumah.penjual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.rumah.R;
import com.example.rumah.adapter.adapterUpdate;
import com.example.rumah.data.local.SharedPref;
import com.example.rumah.data.network.ApiClient;
import com.example.rumah.data.network.EndPoint;
import com.example.rumah.data.network.response.get_rumah_not_pending.DataItem;
import com.example.rumah.data.network.response.get_rumah_not_pending.ResponseRumahNotPending;
import com.example.rumah.data.network.response.success.ResponseSuccess;
import com.example.rumah.listener.StatusClickListener;
import com.example.rumah.model.ModelUpdate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import retrofit2.Call;

public class updateRumah extends AppCompatActivity implements StatusClickListener {
    ImageButton back;
    RecyclerView rcvupdaterumah;
    private ArrayList<ModelUpdate> updateRumah = new ArrayList<>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_rumah);

        back = (ImageButton) findViewById(R.id.back);
        rcvupdaterumah = (RecyclerView) findViewById(R.id.rcvupdaterumah);

        rcvupdaterumah.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getRumah();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getRumah() {
        String idPengguna = SharedPref.getIdPengguna(getApplicationContext());
        if(!idPengguna.isEmpty()){
            EndPoint endPoint = ApiClient.getClient().create(EndPoint.class);
            Call<ResponseRumahNotPending> call = endPoint.getRumahNotPending(idPengguna);

            call.enqueue(new retrofit2.Callback<ResponseRumahNotPending>() {
                @Override
                public void onResponse(Call<ResponseRumahNotPending> call, retrofit2.Response<ResponseRumahNotPending> response) {
                    if(response.isSuccessful()){
                        if(response.body().getData() != null){
                            updateRumah.clear();
                            ArrayList<DataItem> data = response.body().getData();
                            Set<String> nameSet = new HashSet<>();
                            List<DataItem> dataUniqe = data.stream()
                                    .filter(e -> nameSet.add(e.getGambar()))
                                    .collect(Collectors.toList());
                            adapterUpdate rumah = new adapterUpdate(dataUniqe);
                            rcvupdaterumah.setAdapter(rumah);
                            rumah.setSuccessClickListener(updateRumah.this);
                        }
                    }
                }
                @Override
                public void onFailure(Call<ResponseRumahNotPending> call, Throwable t) {
                    Log.d("response", t.getMessage());
                }
            });
        }
    }


    @Override
    public void onSuccessClick(View view, String status, String idRumah) {
        updateStatus(status, idRumah, getApplicationContext());
        getRumah();
    }

    @Override
    public void onFailedClick(View view, String status, String idRumah) {
        updateStatus(status, idRumah, getApplicationContext());
        getRumah();
    }

    private void updateStatus(String status, String idRumah, Context context) {
        EndPoint endPoint = ApiClient.getClient().create(EndPoint.class);
        Call<ResponseSuccess> call = endPoint.updateStatusPenjualan(idRumah,status);

        call.enqueue(new retrofit2.Callback<ResponseSuccess>() {
            @Override
            public void onResponse(Call<ResponseSuccess> call, retrofit2.Response<ResponseSuccess> response) {
                if (response.body().getMessage().equals("OK")) {
                    Toast.makeText(context, "Sukses update data", Toast.LENGTH_SHORT).show();
                    getRumah();
                } else {
                    Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseSuccess> call, Throwable t) {
                Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

}