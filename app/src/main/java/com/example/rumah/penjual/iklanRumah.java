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
    private Spinner provinsi,kota;
    ArrayList<String> listProvinsi = new ArrayList<>();
    ArrayList<String> listKota = new ArrayList<>();
    ArrayAdapter<String> negaraAdapter;
    ArrayAdapter<String> kotaAdapter;
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
        provinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        kota = (Spinner) findViewById(R.id.spinnerKota);
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

        String url = "http://192.168.1.17/android/populate_country.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("PROVINSI");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String countryName = jsonObject.optString("NAMA_PROVINSI");
                        listProvinsi.add(countryName);
                        negaraAdapter = new ArrayAdapter<>(iklanRumah.this,
                                android.R.layout.simple_spinner_item, listProvinsi);
                        negaraAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        provinsi.setAdapter(negaraAdapter);

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
        provinsi.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == R.id.spinnerProvinsi){
            listKota.clear();
            String selectedCountry = adapterView.getSelectedItem().toString();
            String url = "http://192.168.1.17/android/populate_city.php?NAMA_PROVINSI="+selectedCountry;
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("KOTA");
                        for(int i=0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String cityName = jsonObject.optString("NAMA_KOTA");
                            listKota.add(cityName);
                            kotaAdapter = new ArrayAdapter<>(iklanRumah.this,
                                    android.R.layout.simple_spinner_item, listKota);
                            kotaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            kota.setAdapter(kotaAdapter);

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
            kota.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}