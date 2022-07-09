package com.example.rumah.penjual;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rumah.R;
import com.example.rumah.data.Constant;
import com.example.rumah.data.local.SharedPref;
import com.example.rumah.data.network.ApiClient;
import com.example.rumah.data.network.EndPoint;
import com.example.rumah.data.network.response.response_add_iklan.ResponseAddIklan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class iklanRumah extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText judul, alamat, deskripsi, harga;
    Button btnIklan;
    ImageButton imgButton;
    private Spinner kecamatan, kelurahan;
    ArrayList<String> listKecamatan = new ArrayList<>();
    ArrayList<String> listIdKecamatan = new ArrayList<>();
    ArrayList<String> listKelurahan = new ArrayList<>();
    ArrayList<String> listIdKelurahan = new ArrayList<>();
    ArrayAdapter<String> kecamatanAdapter;
    ArrayAdapter<String> kelurahanAdapter;
    ImageButton back;
    RequestQueue requestQueue;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    Bitmap photo;
    String idKecamatan, idKelurahan, idPengguna;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            try {
                imageUri = data.getData();
                photo = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imgButton.setImageBitmap(photo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private File createTempFile(Bitmap bitmap) {
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                , System.currentTimeMillis() + "_image.webp");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.WEBP, 0, bos);
        byte[] bitmapdata = bos.toByteArray();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

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
        btnIklan = (Button) findViewById(R.id.iklan);
        imgButton = (ImageButton) findViewById(R.id.imgButton);
        requestQueue = Volley.newRequestQueue(this);

        getIdPengguna();

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
            }
        });


        btnIklan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (judul.getText().toString().isEmpty() ||
                        alamat.getText().toString().isEmpty() ||
                        deskripsi.getText().toString().isEmpty() ||
                        harga.getText().toString().isEmpty() ||
                        photo == null) {
                    Toast.makeText(iklanRumah.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String, RequestBody> map = new HashMap<>();
                    map.put("id_kelurahan", createPartFromString(idKelurahan));
                    map.put("id_pengguna", createPartFromString(idPengguna));
                    map.put("judul_rumah", createPartFromString(judul.getText().toString()));
                    map.put("alamat_rumah", createPartFromString(alamat.getText().toString()));
                    map.put("desc_rumah", createPartFromString(deskripsi.getText().toString()));
                    map.put("harga_rumah", createPartFromString(harga.getText().toString()));

                    File file = createTempFile(photo);
                    RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("gambar", file.getName(), reqFile);

                    EndPoint endPoint = ApiClient.getClient().create(EndPoint.class);
                    Call<ResponseAddIklan> call = endPoint.addIklan(body, map);

                    call.enqueue(new retrofit2.Callback<ResponseAddIklan>() {
                        @Override
                        public void onResponse(Call<ResponseAddIklan> call, retrofit2.Response<ResponseAddIklan> response) {
                            Toast.makeText(iklanRumah.this, "Berhasil ditambah", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<ResponseAddIklan> call, Throwable t) {
                            Log.i("Error33", t.getMessage());
                            Toast.makeText(iklanRumah.this, "Gagal " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @NonNull
            private RequestBody createPartFromString(String descriptionString) {
                return RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);
            }

        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dahsboard = new Intent(iklanRumah.this, DashboardPenjual.class);
                iklanRumah.this.startActivity(dahsboard);
                finish();
            }
        });

        String url = Constant.baseURL + "provinsi.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("KECAMATAN");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String countryName = jsonObject.optString("NAMA_KECAMATAN");
                        String countryId = jsonObject.optString("ID_KECAMATAN");
                        listKecamatan.add(countryName);
                        listIdKecamatan.add(countryId);
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

    private void getIdPengguna() {
        idPengguna = SharedPref.getIdPengguna(getApplicationContext());
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.spinnerKecamatan) {
            listKelurahan.clear();
            idKecamatan = listIdKecamatan.get(i);
            String selectedCountry = adapterView.getSelectedItem().toString();
            String url = Constant.baseURL + "kota.php?NAMA_KECAMATAN=" + selectedCountry;
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("KELURAHAN");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String cityName = jsonObject.optString("NAMA_KELURAHAN");
                            String kelurahanIdList = jsonObject.optString("ID_KELURAHAN");
                            listKelurahan.add(cityName);
                            listIdKelurahan.add(kelurahanIdList);
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
        } else {
            String selectedId = listIdKelurahan.get(i);
            idKelurahan = selectedId;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}