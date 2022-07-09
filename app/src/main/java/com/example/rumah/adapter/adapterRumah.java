package com.example.rumah.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rumah.R;
import com.example.rumah.data.Constant;
import com.example.rumah.data.network.response.get_rumah.DataItem;

import java.util.ArrayList;


public class adapterRumah extends RecyclerView.Adapter<adapterRumah.ViewHolder> {

    private ArrayList<DataItem> rumahlist;
//    String judul,alamat,kamar,mandi;

    public adapterRumah(ArrayList<DataItem> rumahlist) {
        this.rumahlist = rumahlist;
    }


    @NonNull
    @Override
    public adapterRumah.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_rumah, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataItem mr = rumahlist.get(position);
        Glide.with(holder.itemView.getContext()).load(Constant.baseImageURL + mr.getGambar()).into(holder.iv_gambar);
        holder.tjudul.setText(mr.getJudulRumah());
        holder.talamat.setText(mr.getHargaRumah());

        holder.btn_detail_daftar_rumah.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                View dialogView= LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_detail_rumah,null);
                TextView tv_detail_judul, tv_detail_alamat, tv_detail_harga, tv_detail_desc;
                ImageView iv_detail_gambar;

                tv_detail_judul = dialogView.findViewById(R.id.tv_detail_judul);
                tv_detail_alamat = dialogView.findViewById(R.id.tv_detail_alamat);
                tv_detail_harga = dialogView.findViewById(R.id.tv_detail_harga);
                tv_detail_desc = dialogView.findViewById(R.id.tv_detail_desc);
                iv_detail_gambar = dialogView.findViewById(R.id.iv_detail_gambar);

                Glide.with(holder.itemView.getContext())
                        .load(Constant.baseImageURL + mr.getGambar())
                        .into(iv_detail_gambar);

                tv_detail_judul.setText("Judul Rumah: " + mr.getJudulRumah());
                tv_detail_alamat.setText("Alamat Rumah: " + mr.getKelurahan()+", "+mr.getAlamatRumah());
                tv_detail_harga.setText("Harga Rumah: " + mr.getHargaRumah());
                tv_detail_desc.setText("Deskripsi Rumah: " + mr.getDescRumah());

                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return rumahlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tjudul, talamat;
        ImageView iv_gambar;
        Button btn_detail_daftar_rumah;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tjudul = itemView.findViewById(R.id.tv_judul_daftar_rumah);
            iv_gambar = itemView.findViewById(R.id.iv_gambar);
            btn_detail_daftar_rumah = itemView.findViewById(R.id.btn_detail_daftar_rumah);
            talamat = itemView.findViewById(R.id.tv_alamat_daftar_rumah);
        }

    }
}
