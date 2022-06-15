package com.example.rumah;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rumah.dialog.customDialog;
import com.example.rumah.pembeli.dashboardPembeli;
import com.example.rumah.penjual.dashboardAdmin;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class login extends AppCompatActivity {
    EditText username, password;
    private StringRequest request;
    private RequestQueue queue;
    Button signIn;
    TextView signUp;
    customDialog dia = new customDialog(login.this);
    String usernameKey,passwordKey,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signIn = (Button)findViewById(R.id.signIn);
        signUp = (TextView) findViewById(R.id.signUp);



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 usernameKey = username.getText().toString();
                 passwordKey = password.getText().toString();


                if (usernameKey.trim().isEmpty() || passwordKey.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Lengkapi data untuk masuk !!",
                            Toast.LENGTH_SHORT).show();
                }else{

                    if (usernameKey.equals("joko")&&passwordKey.equals("123")){
                        Intent jual = new Intent(login.this, dashboardAdmin.class);
                        dia.startDialog();
                        signIn.setVisibility(View.INVISIBLE);
                        login.this.startActivity(jual);
                        finish();

                    }else if (usernameKey.equals("juki")&&passwordKey.equals("123")){
                        Intent beli = new Intent(login.this, dashboardPembeli.class);
                        login.this.startActivity(beli);
                        dia.startDialog();
                        signIn.setVisibility(View.INVISIBLE);
                        finish();

                    }
                }

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent up = new Intent(login.this, Regist.class);
                login.this.startActivity(up);
                finish();
            }
        });
    }

//    public void cekLogin() {

//        queue = Volley.newRequestQueue(login.this);
//        String URL = "http://192.168.1.33/rumah/login.php";
//        request = new StringRequest(Request.Method.POST, URL, response -> {
//
//            try {
//                JSONObject jsonObject = new JSONObject(response);
//                // on below line we are displaying a success toast message.
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            // and setting data to edit text as empty
//            username.setText("");
//            password.setText("");
//
//        }, error -> {
//            // method to handle errors.
//            Log.e("Error", error.getMessage());
//            Toast.makeText(login.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
//        }) {
//            @Override
//            public String getBodyContentType() {
//                // as we are passing data in the form of url encoded
//                // so we are passing the content type below
//                return "application/x-www-form-urlencoded; charset=UTF-8";
//            }
//
//            @Override
//            protected Map<String, String> getParams() {
//
//                // below line we are creating a map for storing
//                // our values in key and value pair.
//                HashMap<String, String> data = new HashMap<>();
//
//                // on below line we are passing our
//                // key and value pair to our parameters.
//
//                data.put("usernameKey", usernameKey);
//                data.put("passwordKey", passwordKey);
//
//                // at last we are returning our params.
//                return data;
//            }
//        };
//        // below line is to make
//        // a json object request.
//        queue.add(request);
//    }
}