package com.example.rumah.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rumah.R;
import com.example.rumah.model.modelRumah;
import androidx.activity.result.contract.ActivityResultContracts;

import java.util.ArrayList;
import java.util.List;


public class adapterRumah extends RecyclerView.Adapter<adapterRumah.ViewHolder> {

    private ArrayList<modelRumah> rumahlist;
//    String judul,alamat,kamar,mandi;

    public adapterRumah (ArrayList<modelRumah>rumahlist){
        this.rumahlist=rumahlist;
    }



    @NonNull
    @Override
    public adapterRumah.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_rumah,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        modelRumah mr=rumahlist.get(position);
//        Glide.with(holder.itemView.getContext()).load(mr.getkamar()).into(holder.tkamar);

//        judul = rumahlist.get(position).getJudul();
//        alamat = rumahlist.get(position).getAlamat();
//        kamar = rumahlist.get(position).getkamar();
//        mandi = rumahlist.get(position).getmandi();

        holder.tjudul.setText(mr.getJudul());
        holder.talamat.setText(mr.getAlamat());
        holder.tkamar.setText(mr.getkamar());
        holder.tmandi.setText(mr.getmandi());
    }

    @Override
    public int getItemCount() {
        return rumahlist.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tjudul,talamat,tkamar,tmandi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tjudul = itemView.findViewById(R.id.judul);
            talamat = itemView.findViewById(R.id.alamat);
            tkamar = itemView.findViewById(R.id.kamar);
            tmandi = itemView.findViewById(R.id.bathup);
        }

    }
}
