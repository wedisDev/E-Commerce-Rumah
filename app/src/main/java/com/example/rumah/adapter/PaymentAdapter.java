package com.example.rumah.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.rumah.data.network.response.get_penjual_by_pengguna.DataItem;
import com.example.rumah.pembeli.TransferActivity;

import java.util.ArrayList;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {

    private ArrayList<DataItem> paymentList;

    public PaymentAdapter(ArrayList<DataItem> paymentList) {
        this.paymentList = paymentList;
    }

    @NonNull
    @Override
    public PaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_payment, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataItem mr = paymentList.get(position);
        Glide.with(holder.itemView.getContext()).load(Constant.baseImageURL + mr.getGambar()).into(holder.iv_payment_gambar);
        holder.tv_payment_judul.setText(mr.getJudulRumah());
        holder.tv_payment_owner.setText("Pemilik : " + mr.getPenjual());
        holder.tv_payment_alamat.setText("Alamat : " + mr.getAlamatRumah());
        holder.btn_payment.setVisibility(View.GONE);
        holder.tv_payment_status.setText("Status : " + mr.getStatus());

        if(mr.getStatus().equals("transfer")){
            holder.tv_payment_status.setText("Status : Menunggu konfirmasi");
        }
        if(mr.getStatus().equals("ditolak") || mr.getStatus().equals("pending")){
            holder.btn_payment.setVisibility(View.VISIBLE);
        }

        holder.btn_payment.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TransferActivity.class);
                intent.putExtra("idRumah", String.valueOf(mr.getId()));
                intent.putExtra("rek", mr.getRekeningPenjual());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return paymentList == null ? 0 : paymentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_payment_judul, tv_payment_owner, tv_payment_alamat, tv_payment_status;
        ImageView iv_payment_gambar;
        Button btn_payment;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_payment_judul = itemView.findViewById(R.id.tv_payment_judul);
            iv_payment_gambar = itemView.findViewById(R.id.iv_payment_gambar);
            tv_payment_owner = itemView.findViewById(R.id.tv_payment_owner);
            tv_payment_alamat = itemView.findViewById(R.id.tv_payment_alamat);
            btn_payment = itemView.findViewById(R.id.btn_payment);
            tv_payment_status = itemView.findViewById(R.id.tv_payment_status);
        }

    }
}
