package com.example.rumah.pembeli;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rumah.R;
import com.example.rumah.data.local.SharedPref;
import com.example.rumah.data.network.ApiClient;
import com.example.rumah.data.network.EndPoint;
import com.example.rumah.data.network.response.get_user.Data;
import com.example.rumah.data.network.response.get_user.ResponseGetUser;
import com.example.rumah.dialog.CustomDialog;
import com.example.rumah.Login;
import com.example.rumah.penjual.editAkunPenjual;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AkunFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AkunFragment extends Fragment {
    Button keluar, editAkun;
    TextView tv_alamat_acc, tv_telp_acc, tv_email_acc, tv_name_acc;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AkunFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AkunFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AkunFragment newInstance(String param1, String param2) {
        AkunFragment fragment = new AkunFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_akun,container,false);

        keluar=v.findViewById(R.id.keluar);
        editAkun=v.findViewById(R.id.editAkun);
        tv_alamat_acc = (TextView) v.findViewById(R.id.tv_alamat_acc);
        tv_telp_acc = (TextView) v.findViewById(R.id.tv_telp_acc);
        tv_email_acc = (TextView) v.findViewById(R.id.tv_email_acc);
        tv_name_acc = (TextView) v.findViewById(R.id.tv_name_acc);

        getUser();

        editAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), editAkunPenjual.class);
                startActivity(i);
            }
        });

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SharedPref.logout(getActivity());
                        Intent intent = new Intent(getActivity(), Login.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }, 1000);
            }
        });
        return v;
    }

    private void getUser() {
        String idPengguna = SharedPref.getIdPengguna(getActivity());
        if(!idPengguna.isEmpty()){
            EndPoint endPoint = ApiClient.getClient().create(EndPoint.class);
            Call<ResponseGetUser> call = endPoint.getUser(idPengguna);

            call.enqueue(new Callback<ResponseGetUser>() {
                @Override
                public void onResponse(Call<ResponseGetUser> call, Response<ResponseGetUser> response) {
                    Data acc = null;
                    if (response.body() != null) {
                        acc = response.body().getData();
                        tv_alamat_acc.setText(acc.getAlamat());
                        tv_name_acc.setText(acc.getNama());
                        tv_telp_acc.setText(acc.getTelp());
                        tv_email_acc.setText(acc.getEmail());
                    }
                }

                @Override
                public void onFailure(Call<ResponseGetUser> call, Throwable t) {

                }
            });
        }
    }
}