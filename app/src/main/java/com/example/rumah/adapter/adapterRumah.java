package com.example.rumah.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rumah.R;
import com.example.rumah.model.modelRumah;
import androidx.activity.result.contract.ActivityResultContracts;

import java.util.List;


public class adapterRumah extends RecyclerView.Adapter<adapterRumah.ViewHolder> {

    private List<modelRumah> rumahlist;
    String judul,alamat,kamar,mandi;

    public adapterRumah (List<modelRumah>rumahlist){
        this.rumahlist=rumahlist;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_iklan,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        judul = rumahlist.get(position).getJudul();
        alamat = rumahlist.get(position).getAlamat();
        kamar = rumahlist.get(position).getkamar();
        mandi = rumahlist.get(position).getmandi();

        holder.setData(judul,alamat,kamar,mandi);
    }

    @Override
    public int getItemCount() {
        return rumahlist.size() ;
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
