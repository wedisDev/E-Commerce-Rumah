package com.example.rumah.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rumah.R;
import com.example.rumah.admin.DashboardDetailAdmin;
import com.example.rumah.data.Constant;
import com.example.rumah.data.network.response.get_dashboard_admin.DataItem;
import com.example.rumah.pembeli.DetailLahanActivity;

import java.util.ArrayList;
import java.util.List;


public class AdapterAdmin extends RecyclerView.Adapter<AdapterAdmin.ViewHolder> {

    private List<DataItem> rumahlist;
    private Context context;

    public AdapterAdmin(List<DataItem> rumahlist, Context context) {
        this.rumahlist = rumahlist;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterAdmin.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_admin, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataItem mr = rumahlist.get(position);
        Glide.with(holder.itemView.getContext()).load(Constant.baseImageURL + mr.getGambar()).into(holder.iv_gambar);
        holder.tjudul.setText(mr.getJudulRumah());
        holder.tv_admin_pemilik.setText("Pemilik :"+mr.getPenjual());
        String pembeli = mr.getPembeli() == null ? "-" : mr.getPembeli();
        holder.tv_admin_pembeli.setText("Pembeli :" + pembeli);

        holder.btn_detail_admin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, DashboardDetailAdmin.class);
                i.putExtra("data", mr);
//                i.putExtra("penjual", isPenjual);
                view.getContext().startActivity(i);
//                startActivity(i);
//                AlertDialog builder = new AlertDialog.Builder(view.getContext()).create();
//                View dialogView= LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_detail_rumah,null);
//                TextView tv_detail_judul, tv_detail_alamat, tv_detail_harga, tv_detail_desc,tv_detail_status, tv_detail_pemilik, tv_detail_tgl, tv_detail_pembeli;
//                ImageView iv_detail_gambar;
//                Button btn_detail_beli;
//
//                tv_detail_judul = dialogView.findViewById(R.id.tv_detail_judul);
//                tv_detail_alamat = dialogView.findViewById(R.id.tv_detail_alamat);
//                tv_detail_harga = dialogView.findViewById(R.id.tv_detail_harga);
//                tv_detail_desc = dialogView.findViewById(R.id.tv_detail_desc);
//                iv_detail_gambar = dialogView.findViewById(R.id.iv_detail_gambar);
//                tv_detail_pemilik = dialogView.findViewById(R.id.tv_detail_pemilik);
//                btn_detail_beli = dialogView.findViewById(R.id.btn_detail_beli);
//                tv_detail_pembeli = dialogView.findViewById(R.id.tv_detail_pembeli);
//                tv_detail_status = dialogView.findViewById(R.id.tv_detail_status);
//                tv_detail_pembeli.setVisibility(View.VISIBLE);
//                btn_detail_beli.setVisibility(View.GONE);
//
//
//                Glide.with(holder.itemView.getContext())
//                        .load(Constant.baseImageURL + mr.getGambar())
//                        .into(iv_detail_gambar);
//
//                String status = mr.getStatus() == null ? "tersedia" : mr.getStatus();
//                tv_detail_status.setText("Status : " + status);
//                tv_detail_pemilik.setText("Pemilik : " + mr.getPenjual());
//                tv_detail_pembeli.setText("Pembeli : " + mr.getPembeli());
//                tv_detail_judul.setText("Judul Rumah : " + mr.getJudulRumah());
//                tv_detail_alamat.setText("Alamat Rumah : " + mr.getKelurahan()+", "+mr.getAlamatRumah());
//                tv_detail_harga.setText("Harga Rumah : " + mr.getHargaRumah());
//                tv_detail_desc.setText("Deskripsi Rumah : " + mr.getDescRumah());
//
//                builder.setView(dialogView);
//                builder.setCancelable(true);
//                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return rumahlist == null ? 0 : rumahlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tjudul, tv_admin_pemilik, tv_status_admin, tv_admin_pembeli;
        ImageView iv_gambar;
        CardView cv_rumah;
        Button btn_detail_admin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tjudul = itemView.findViewById(R.id.tv_judul_admin);
            iv_gambar = itemView.findViewById(R.id.iv_gambar);
            cv_rumah = itemView.findViewById(R.id.cv_rumah);
            tv_status_admin = itemView.findViewById(R.id.tv_status_admin);
            btn_detail_admin = itemView.findViewById(R.id.btn_detail_admin);
            tv_admin_pemilik = itemView.findViewById(R.id.tv_admin_pemilik);
            tv_admin_pembeli = itemView.findViewById(R.id.tv_admin_pembeli);
        }

    }
}
