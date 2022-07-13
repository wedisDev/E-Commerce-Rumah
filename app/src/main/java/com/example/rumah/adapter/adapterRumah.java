package com.example.rumah.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rumah.R;
import com.example.rumah.data.Constant;
import com.example.rumah.data.local.SharedPref;
import com.example.rumah.data.network.ApiClient;
import com.example.rumah.data.network.EndPoint;
import com.example.rumah.data.network.response.get_rumah.DataItem;
import com.example.rumah.data.network.response.login.ResponseLogin;
import com.example.rumah.data.network.response.success.ResponseSuccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;


public class adapterRumah extends RecyclerView.Adapter<adapterRumah.ViewHolder> {

    private List<DataItem> rumahlist;
    boolean isPenjual;

    public adapterRumah(List<DataItem> rumahlist, boolean isPenjual) {
        this.rumahlist = rumahlist;
        this.isPenjual = isPenjual;
    }

    @NonNull
    @Override
    public adapterRumah.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_rumah, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataItem mr = rumahlist.get(position);
        Glide.with(holder.itemView.getContext()).load(Constant.baseImageURL + mr.getGambar()).into(holder.iv_gambar);
        holder.tjudul.setText(mr.getJudulRumah());
        holder.talamat.setText("Harga :"+mr.getHargaRumah());


        if(isPenjual){
            holder.tv_status_daftar_rumah.setVisibility(View.VISIBLE);
            if(Objects.equals(mr.getStatus(), "pending")) {
                holder.tv_status_daftar_rumah.setText("status : " +"Tersedia");
            }else{
                holder.tv_status_daftar_rumah.setText("status : " +mr.getStatus());
            }
        }else{
            if(!mr.getStatus().equals("pending")){
                holder.itemView.setVisibility(View.GONE);
                ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
                params.height = 0;
                holder.itemView.setLayoutParams(params);
            }
        }

        holder.btn_detail_daftar_rumah.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                AlertDialog builder = new AlertDialog.Builder(view.getContext()).create();
                View dialogView= LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_detail_rumah,null);
                TextView tv_detail_judul, tv_detail_alamat, tv_detail_harga, tv_detail_desc, tv_detail_pemilik, tv_detail_tgl;
                Button btn_detail_beli;
                ImageView iv_detail_gambar;

                tv_detail_judul = dialogView.findViewById(R.id.tv_detail_judul);
                tv_detail_tgl = dialogView.findViewById(R.id.tv_detail_tgl);
                tv_detail_alamat = dialogView.findViewById(R.id.tv_detail_alamat);
                tv_detail_harga = dialogView.findViewById(R.id.tv_detail_harga);
                tv_detail_desc = dialogView.findViewById(R.id.tv_detail_desc);
                iv_detail_gambar = dialogView.findViewById(R.id.iv_detail_gambar);
                tv_detail_pemilik = dialogView.findViewById(R.id.tv_detail_pemilik);
                btn_detail_beli = dialogView.findViewById(R.id.btn_detail_beli);

                Glide.with(holder.itemView.getContext())
                        .load(Constant.baseImageURL + mr.getGambar())
                        .into(iv_detail_gambar);

                tv_detail_pemilik.setText("Pemilik : " + mr.getPenjual());
                tv_detail_judul.setText("Judul Rumah : " + mr.getJudulRumah());
                tv_detail_alamat.setText("Alamat Rumah : " + mr.getKelurahan()+", "+mr.getAlamatRumah());
                tv_detail_harga.setText("Harga Rumah : " + mr.getHargaRumah());
                tv_detail_desc.setText("Deskripsi Rumah : " + mr.getDescRumah());
                tv_detail_tgl.setText("Tanggal Penjualan : " + mr.getTgl());

                if(isPenjual){
                    btn_detail_beli.setVisibility(View.GONE);
                } else{
                    btn_detail_beli.setVisibility(View.VISIBLE);
                }

                String idPengguna = SharedPref.getIdPengguna(view.getContext());

                btn_detail_beli.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EndPoint endPoint = ApiClient.getClient().create(EndPoint.class);
                        Call<ResponseSuccess> call = endPoint.beliRumah(idPengguna, String.valueOf(mr.getId()),
                                mr.getEmailPenjual());
                        call.enqueue(new retrofit2.Callback<ResponseSuccess>() {
                            @Override
                            public void onResponse(Call<ResponseSuccess> call, retrofit2.Response<ResponseSuccess> response) {
                                if(response.body().getMessage().equals("OK")){
                                    Toast.makeText(view.getContext(), "Sukses", Toast.LENGTH_SHORT).show();
                                    builder.dismiss();
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

                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return rumahlist == null ? 0 : rumahlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tjudul, talamat, tv_status_daftar_rumah;
        ImageView iv_gambar;
        CardView cv_rumah;
        Button btn_detail_daftar_rumah;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tjudul = itemView.findViewById(R.id.tv_judul_daftar_rumah);
            iv_gambar = itemView.findViewById(R.id.iv_gambar);
            cv_rumah = itemView.findViewById(R.id.cv_rumah);
            tv_status_daftar_rumah = itemView.findViewById(R.id.tv_status_daftar_rumah);
            btn_detail_daftar_rumah = itemView.findViewById(R.id.btn_detail_daftar_rumah);
            talamat = itemView.findViewById(R.id.tv_alamat_daftar_rumah);
        }

    }
}
