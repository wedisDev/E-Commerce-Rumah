package com.example.rumah.pembeli;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rumah.R;
import com.example.rumah.data.network.ApiClient;
import com.example.rumah.data.network.EndPoint;
import com.example.rumah.data.network.response.response_add_iklan.ResponseAddIklan;
import com.example.rumah.data.network.response.success.ResponseSuccess;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class TransferActivity extends AppCompatActivity {
    Button btn_payment_submit;
    TextView tv_payment_title;
    ImageButton ib_payment_back, ib_payment_upload;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    Bitmap photo;
    String idRumah = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        tv_payment_title = findViewById(R.id.tv_payment_title);
        btn_payment_submit = findViewById(R.id.btn_payment_submit);
        ib_payment_back = findViewById(R.id.ib_payment_back);
        ib_payment_upload = findViewById(R.id.ib_payment_upload);

        Bundle extras = getIntent().getExtras();
        idRumah = extras.getString("idRumah");
        String rekeningPenjual = extras.getString("rek");
        if(!idRumah.isEmpty()){
            tv_payment_title.setText("BCA Transfer : " + rekeningPenjual);
        }

        ib_payment_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ib_payment_upload.setOnClickListener(v -> {
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, PICK_IMAGE);
        });

        btn_payment_submit.setOnClickListener(v -> {
            if(photo != null){
                HashMap<String, RequestBody> map = new HashMap<>();
                map.put("id", createPartFromString(idRumah));
                map.put("status", createPartFromString("transfer"));

                File file = createTempFile(photo);
                RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("gambar", file.getName(), reqFile);

                EndPoint endPoint = ApiClient.getClient().create(EndPoint.class);
                Call<ResponseSuccess> call = endPoint.updateStatus(body, map);
                call.enqueue(new retrofit2.Callback<ResponseSuccess>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseSuccess> call, @NonNull retrofit2.Response<ResponseSuccess> response) {
                        if(response.body().getMessage().equals("OK")){
                            Toast.makeText(TransferActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseSuccess> call, @NonNull Throwable t) {
                        Toast.makeText(TransferActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                    }
                });

            }else{
                Toast.makeText(this, "Bukti transfer tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            try {
                imageUri = data.getData();
                photo = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ib_payment_upload.setImageBitmap(photo);
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
}