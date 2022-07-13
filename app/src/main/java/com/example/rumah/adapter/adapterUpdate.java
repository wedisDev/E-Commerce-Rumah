package com.example.rumah.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rumah.R;
import com.example.rumah.data.Constant;
import com.example.rumah.data.network.response.get_rumah_not_pending.DataItem;
import com.example.rumah.listener.StatusClickListener;

import java.util.List;


public class adapterUpdate extends RecyclerView.Adapter<adapterUpdate.ViewHolder> {

    private List<DataItem> updaterumahlist;
    private StatusClickListener statusClickListener;
    public adapterUpdate (List<DataItem> updaterumahlist){
        this.updaterumahlist=updaterumahlist;
    }

    @NonNull
    @Override
    public adapterUpdate.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_iklan,parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull adapterUpdate.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        DataItem mu = updaterumahlist.get(position);
        Glide.with(holder.itemView.getContext()).load(Constant.baseImageURL + mu.getTransfer()).into(holder.iv_update_pic);
        holder.tv_update_judul.setText(mu.getJudulRumah());
        holder.tv_update_harga.setText("Harga : "+mu.getHargaRumah());
        holder.tv_update_status.setText("Status : "+mu.getStatus());
        if(mu.getStatus().equals("transfer")) {
            holder.tv_update_status.setText("Status : Sudah ditransfer");
            holder.btn_update1.setText("Terima");
            holder.btn_update2.setText("Tolak");

            holder.btn_update1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (statusClickListener != null) statusClickListener.onSuccessClick(view, "sukses",String.valueOf(mu.getId()));
                }
            });

            holder.btn_update2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (statusClickListener != null) statusClickListener.onFailedClick(view, "ditolak",String.valueOf(mu.getId()));
                }
            });

        }else{
            if(mu.getStatus().equals("sukses")){
                holder.iv_terjual.setVisibility(View.VISIBLE);
                holder.btn_update1.setVisibility(View.GONE);
                holder.btn_update2.setVisibility(View.GONE);
            }else{
                holder.tv_update_status.setText("Status : gagal terjual");
            }
            holder.btn_update2.setVisibility(View.GONE);
            holder.btn_update2.setWidth(100);
            holder.btn_update1.setText(mu.getStatus().toUpperCase());
        }
    }

    @Override
    public int getItemCount() {
        return updaterumahlist == null ? 0 : updaterumahlist.size() ;
    }

    public void setSuccessClickListener(StatusClickListener statusClickListener) {
        this.statusClickListener = statusClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_update_judul, tv_update_harga, tv_update_status;
        Button btn_update1, btn_update2;
        LinearLayout l_konfirmasi;
        ImageView iv_update_pic, iv_terjual;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_update1 = itemView.findViewById(R.id.btn_update1);
            iv_terjual = itemView.findViewById(R.id.iv_terjual);
            btn_update2 = itemView.findViewById(R.id.btn_update2);
            l_konfirmasi = itemView.findViewById(R.id.l_konfirmasi);
            iv_update_pic = itemView.findViewById(R.id.iv_update_pic);
            tv_update_harga = itemView.findViewById(R.id.tv_update_harga);
            tv_update_judul = itemView.findViewById(R.id.tv_update_judul);
            tv_update_status = itemView.findViewById(R.id.tv_update_status);
        }
    }

}

