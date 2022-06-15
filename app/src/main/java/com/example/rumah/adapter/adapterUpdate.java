package com.example.rumah.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rumah.R;
import com.example.rumah.model.modelRumah;
import com.example.rumah.model.modelUpdate;

import java.util.ArrayList;
import java.util.List;

public class adapterUpdate extends RecyclerView.Adapter<adapterUpdate.ViewHolder> {

    private List<modelUpdate> updaterumahlist;
//    String judul,alamat,kamar,mandi;

    public adapterUpdate (ArrayList<modelUpdate> updaterumahlist){
        this.updaterumahlist=updaterumahlist;
    }


    @NonNull
    @Override
    public adapterUpdate.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_iklan,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterUpdate.ViewHolder holder, int position) {
        modelUpdate mu=updaterumahlist.get(position);
//        Glide.with(holder.itemView.getContext()).load(mr.getkamar()).into(holder.tkamar);
        holder.tjudul.setText(mu.getJudul());
        holder.talamat.setText(mu.getAlamat());
        holder.tkamar.setText(mu.getkamar());
        holder.tmandi.setText(mu.getmandi());
    }

    @Override
    public int getItemCount() {
        return updaterumahlist.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tjudul,talamat,tkamar,tmandi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tjudul = itemView.findViewById(R.id.judul);
            talamat = itemView.findViewById(R.id.alamat);
            tkamar = itemView.findViewById(R.id.kamar);
            tmandi = itemView.findViewById(R.id.bathup);
        }
    }
}

