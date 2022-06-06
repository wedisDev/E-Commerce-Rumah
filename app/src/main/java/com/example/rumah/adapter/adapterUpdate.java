package com.example.rumah.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rumah.R;
import com.example.rumah.model.modelUpdate;

import java.util.List;

public class adapterUpdate extends RecyclerView.Adapter<adapterUpdate.ViewHolder> {

    private List<modelUpdate> updaterumahlist;
    String judul,alamat,kamar,mandi;

    public adapterUpdate (List<modelUpdate>updaterumahlist){
        this.updaterumahlist=updaterumahlist;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_rumah,parent, false);
        return new adapterUpdate.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterUpdate.ViewHolder holder, int position) {
        judul = updaterumahlist.get(position).getJudul();
        alamat = updaterumahlist.get(position).getAlamat();
        kamar = updaterumahlist.get(position).getkamar();
        mandi = updaterumahlist.get(position).getmandi();

        holder.setData(judul,alamat,kamar,mandi);
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

        public void setData(String judul, String alamat, String kamar, String mandi) {
            tjudul.setText(judul);
            talamat.setText(alamat);
            tkamar.setText(kamar);
            tmandi.setText(mandi);
        }
    }
}

