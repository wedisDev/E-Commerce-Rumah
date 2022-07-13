package com.example.rumah.pembeli;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rumah.R;
import com.example.rumah.adapter.PaymentAdapter;
import com.example.rumah.data.local.SharedPref;
import com.example.rumah.data.network.ApiClient;
import com.example.rumah.data.network.EndPoint;
import com.example.rumah.data.network.response.get_penjual_by_pengguna.ResponseGetPenjualByPengguna;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView rv_payment;
    private ProgressDialog progress;

    public PaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentFragment newInstance(String param1, String param2) {
        PaymentFragment fragment = new PaymentFragment();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_payment = view.findViewById(R.id.rcvbayar);
        rv_payment.setHasFixedSize(true);

        if(getActivity() != null){
            getPayment();
        }
    }

    private void getPayment() {
        String penggunaId = SharedPref.getIdPengguna(getActivity());
        EndPoint endPoint = ApiClient.getClient().create(EndPoint.class);
        Call<ResponseGetPenjualByPengguna> call = endPoint.getPenjualanByPengguna(penggunaId);

        call.enqueue(new retrofit2.Callback<ResponseGetPenjualByPengguna>() {
            @Override
            public void onResponse(Call<ResponseGetPenjualByPengguna> call, retrofit2.Response<ResponseGetPenjualByPengguna> response) {
                if (response.isSuccessful()) {
                    rv_payment.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_payment.setAdapter(new PaymentAdapter(response.body().getData()));
                }
            }

            @Override
            public void onFailure(Call<ResponseGetPenjualByPengguna> call, Throwable t) {
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            getPayment();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }
}