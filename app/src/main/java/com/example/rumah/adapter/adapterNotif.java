package com.example.rumah.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rumah.R;
import com.example.rumah.model.modelNotif;

import java.util.List;

public class adapterNotif extends RecyclerView.Adapter<adapterNotif.ViewHolder>{

    private List<modelNotif> notiflist;
    String notif,tanggal,waktu;

    public adapterNotif (List<modelNotif>notiflist){
        this.notiflist=notiflist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notif,parent, false);
        return new adapterNotif.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterNotif.ViewHolder holder, int position) {
        notif = notiflist.get(position).getNotif();
        tanggal = notiflist.get(position).getTanggal();
        waktu = notiflist.get(position).getWaktu();

        holder.setData(notif,tanggal,waktu);
    }

    @Override
    public int getItemCount() {
        return notiflist.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tnotif,ttanggal,twaktu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tnotif = itemView.findViewById(R.id.notif);
            ttanggal = itemView.findViewById(R.id.date);
            twaktu = itemView.findViewById(R.id.time);
        }

        public void setData(String notif, String tanggal, String waktu) {
            tnotif.setText(notif);
            ttanggal.setText(tanggal);
            twaktu.setText(waktu);

        }
    }
}
