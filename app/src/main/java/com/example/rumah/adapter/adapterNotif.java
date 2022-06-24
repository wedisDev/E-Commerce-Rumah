package com.example.rumah.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rumah.R;
import com.example.rumah.model.ModelNotif;

import java.util.ArrayList;
import java.util.List;

public class adapterNotif extends RecyclerView.Adapter<adapterNotif.ViewHolder>{

    private ArrayList<ModelNotif> notiflist;
    String notif,tanggal,waktu;

    public adapterNotif (ArrayList<ModelNotif>notiflist){
        this.notiflist=notiflist;
    }

    @NonNull
    @Override
    public adapterNotif.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notif,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterNotif.ViewHolder holder, int position) {
        ModelNotif mn = notiflist.get(position);

        holder.ttanggal.setText(mn.getTanggal());
        holder.twaktu.setText(mn.getWaktu());
        holder.tnotif.setText(mn.getTanggal());
    }

    @Override
    public int getItemCount() {
        return notiflist.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tnotif,ttanggal,twaktu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tnotif = itemView.findViewById(R.id.notif);
            ttanggal = itemView.findViewById(R.id.date);
            twaktu = itemView.findViewById(R.id.time);
        }
    }
}
