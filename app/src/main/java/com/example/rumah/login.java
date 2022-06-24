package com.example.rumah;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rumah.dialog.CustomDialog;
import com.example.rumah.pembeli.DashboardPembeli;
import com.example.rumah.penjual.DashboardPenjual;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class login extends AppCompatActivity {
    String status;
    EditText username, password;
    public String ip = "192.168.100.124";
    private StringRequest request;
    private RequestQueue queue;
    Button signIn;
    boolean valid = true;
    TextView signUp;
    CustomDialog dia = new CustomDialog(login.this);
    String usernameKey, passwordKey;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    Intent penjual, pembeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

//        mAuth = FirebaseAuth.getInstance();
//        fStore = FirebaseFirestore.getInstance();

        SharedPreferences.Editor session = getSharedPreferences("Session Login", MODE_PRIVATE).edit();


        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.signIn);
        signUp = (TextView) findViewById(R.id.signUp);


        signIn.setOnClickListener(v -> {

            usernameKey = username.getText().toString();
            passwordKey = password.getText().toString();


            if (usernameKey.trim().isEmpty() || passwordKey.trim().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Lengkapi data untuk masuk !!",
                        Toast.LENGTH_SHORT).show();
            } else {
//                cekLogin();

//                    if (valid){
//                        mAuth.signInWithEmailAndPassword(usernameKey,passwordKey).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                            @Override
//                            public void onSuccess(AuthResult authResult) {
//                                Toast.makeText(login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
//                                checkUserAccessLevel(authResult.getUser().getUid());
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }

                    if (usernameKey.equals("joko")&&passwordKey.equals("123")){
                        Intent jual = new Intent(login.this, DashboardPenjual.class);
                        dia.startDialog();
                        signIn.setVisibility(View.INVISIBLE);
                        login.this.startActivity(jual);
                        finish();

                    }else if (usernameKey.equals("juki")&&passwordKey.equals("123")){
                        Intent beli = new Intent(login.this, DashboardPembeli.class);
                        login.this.startActivity(beli);
                        dia.startDialog();
                        signIn.setVisibility(View.INVISIBLE);
                        finish();

                    }
            }

        });

        signUp.setOnClickListener(view -> {
            Intent up = new Intent(login.this, Regist.class);
            login.this.startActivity(up);
            finish();
        });
    }

//    private void checkUserAccessLevel(String uid) {
//        DocumentReference df =  fStore.collection("Users").document(uid);
//
//        //extract the data from the document
//        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                Log.d("TAG", "onSuccess" + documentSnapshot.getData());
//
//                //identify user access level
//                if (documentSnapshot.getString("isSeller") != null){
//                    // user is admin
//                    startActivity(new Intent(getApplicationContext(), dashboardPenjual.class));
//                    finish();
//                }
//
//                if (documentSnapshot.getString("isBuyer") != null){
//                    startActivity(new Intent(getApplicationContext(), dashboardPembeli.class));
//                    finish();
//                }
//            }
//        });
//    }

//    public void cekLogin() {
//
//        String URL = "http://"+ip+"/rumah/login.php";
//        request = new StringRequest(
//                Request.Method.POST,
//                URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject json = new JSONObject(response);
//                            status = json.getString("status");
//                            if (status.equals("Berhasil")){
//                                Toast.makeText(getApplicationContext(),status,Toast.LENGTH_SHORT).show();
//                            }else if (status.equals("Gagal")){
//                                Toast.makeText(getApplicationContext(),status,Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                }
//        ){
//           protected Map<String,String>getParams(){
//               Map<String,String> data = new HashMap<String,String>();
//               data.put("usernameKey",usernameKey);
//               data.put("passwordKey",passwordKey);
//               return data;
//           }
//        }
//        ;
//        queue = Volley.newRequestQueue(login.this);
//        queue.add(request);
//        }
    //yayayayay
////                } {
//////                        Toast.makeText(getApplicationContext(),"response",Toast.LENGTH_SHORT).show();
//////                        try {
//////                            JSONObject object = new JSONObject(response);
//////                            status = object.getString("status");
//////                            if (status.equals("Berhasil")){
//////                                Toast.makeText(getApplicationContext(),"HALOO ADMIN !",Toast.LENGTH_SHORT).show();
//////                            }else if (status.equals("Gagal")){
//////                                Toast.makeText(getApplicationContext(),"Silahkan melakukan Registrasi",
//////                                        Toast.LENGTH_SHORT).show();
//////                            }
//////                        } catch (JSONException e) {
//////                            e.printStackTrace();
//////                        }
//////                },
//////                error -> Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show()
//////        )
//// {
////            protected Map<String,String>getParams(){
////                Map<String,String> data =new HashMap<String,String>();
////                data.put("usernameKey",usernameKey);
////                data.put("passwordKey",passwordKey);
////                return data;
////            }
////        };
////        queue = Volley.newRequestQueue(login.this);
////        queue.add(request) ;
////    }
////        request = new StringRequest(Request.Method.POST, URL, response -> {
////
////            try {
////                JSONObject jsonObject = new JSONObject(response);
////                // on below line we are displaying a success toast message.
////
////            } catch (JSONException e) {
////                e.printStackTrace();
////            }
////            // and setting data to edit text as empty
////            username.setText("");
////            password.setText("");
////
////        }, error -> {
////            // method to handle errors.
////            Log.e("Error", error.getMessage());
////            Toast.makeText(login.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
////        }) {
////            @Override
////            public String getBodyContentType() {
////                // as we are passing data in the form of url encoded
////                // so we are passing the content type below
////                return "application/x-www-form-urlencoded; charset=UTF-8";
////            }
////
////            @Override
////            protected Map<String, String> getParams() {
////
////                // below line we are creating a map for storing
////                // our values in key and value pair.
////                HashMap<String, String> data = new HashMap<>();
////
////                // on below line we are passing our
////                // key and value pair to our parameters.
////
////                data.put("usernameKey", usernameKey);
////                data.put("passwordKey", passwordKey);
////
////                // at last we are returning our params.
////                return data;
////            }
////        };
////        // below line is to make
////        // a json object request.
////        queue.add(request);
//
//}

}