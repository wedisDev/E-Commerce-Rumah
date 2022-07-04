package com.example.rumah.penjual;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rumah.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class iklanRumah extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private EditText judul, alamat, deskripsi,harga;
    private Spinner kecamatan,kelurahan;
    ArrayList<String> listKecamatan = new ArrayList<>();
    ArrayList<String> listKelurahan = new ArrayList<>();
    ArrayAdapter<String> kecamatanAdapter;
    ArrayAdapter<String> kelurahanAdapter;
    private ImageView gambar;
    private Bitmap bitmap;
    ImageButton back;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iklan_rumah);
        judul = (EditText) findViewById(R.id.name);
        alamat = (EditText) findViewById(R.id.detail);
        deskripsi = (EditText) findViewById(R.id.deskripsi);
        harga = (EditText) findViewById(R.id.harga);
        kecamatan = (Spinner) findViewById(R.id.spinnerKecamatan);
        kelurahan = (Spinner) findViewById(R.id.spinnerKelurahan);
        back = (ImageButton) findViewById(R.id.back);
        requestQueue = Volley.newRequestQueue(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dahsboard = new Intent(iklanRumah.this, DashboardPenjual.class);
                iklanRumah.this.startActivity(dahsboard);
                finish();
            }
        });

        String url = "http://192.168.1.17/rumah/provinsi.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("KECAMATAN");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String countryName = jsonObject.optString("NAMA_KECAMATAN");
                        listKecamatan.add(countryName);
                        kecamatanAdapter = new ArrayAdapter<>(iklanRumah.this,
                                android.R.layout.simple_spinner_item, listKecamatan);
                        kecamatanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        kecamatan.setAdapter(kecamatanAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
        kecamatan.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == R.id.spinnerKecamatan){
            listKelurahan.clear();
            String selectedCountry = adapterView.getSelectedItem().toString();
            String url = "http://192.168.1.17/rumah/kota.php?NAMA_KECAMATAN="+selectedCountry;
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("KELURAHAN");
                        for(int i=0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String cityName = jsonObject.optString("NAMA_KELURAHAN");
                            listKelurahan.add(cityName);
                            kelurahanAdapter = new ArrayAdapter<>(iklanRumah.this,
                                    android.R.layout.simple_spinner_item, listKelurahan);
                            kelurahanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            kelurahan.setAdapter(kelurahanAdapter);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(jsonObjectRequest);
            kelurahan.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}