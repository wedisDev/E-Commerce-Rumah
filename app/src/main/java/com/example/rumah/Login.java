package com.example.rumah;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {
    String status;
    EditText username, password;
    public String ip = "192.168.140.152";
    private StringRequest request;
    private RequestQueue queue;
    Button signIn;
    boolean valid = true;
    TextView signUp;
    CustomDialog dia = new CustomDialog(Login.this);
    String usernameKey, passwordKey;

    public static SharedPreferences.Editor session;
    public  SharedPreferences sp;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    Intent penjual, pembeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

//        mAuth = FirebaseAuth.getInstance();
//        fStore = FirebaseFirestore.getInstance();




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


                if (usernameKey.equals("joko")&&passwordKey.equals("123")){
                    Intent jual = new Intent(Login.this, DashboardPenjual.class);
                    dia.startDialog();
                    signIn.setVisibility(View.INVISIBLE);
                    Login.this.startActivity(jual);
                    finish();

                }else if (usernameKey.equals("juki")&&passwordKey.equals("123")){
                    Intent beli = new Intent(Login.this, DashboardPembeli.class);
                    Login.this.startActivity(beli);
                    dia.startDialog();
                    signIn.setVisibility(View.INVISIBLE);
                    finish();

                }

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

//                    if (usernameKey.equals("joko")&&passwordKey.equals("123")){
//                        Intent jual = new Intent(Login.this, DashboardPenjual.class);
//                        dia.startDialog();
//                        signIn.setVisibility(View.INVISIBLE);
//                        Login.this.startActivity(jual);
//                        finish();
//
//                    }else if (usernameKey.equals("juki")&&passwordKey.equals("123")){
//                        Intent beli = new Intent(Login.this, DashboardPembeli.class);
//                        Login.this.startActivity(beli);
//                        dia.startDialog();
//                        signIn.setVisibility(View.INVISIBLE);
//                        finish();
//
//                    }
            }

        });

        signUp.setOnClickListener(view -> {
            Intent up = new Intent(Login.this, Regist.class);
            Login.this.startActivity(up);
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

    public void cekLogin() {

        String URL = "http://"+ip+"/rumah/login.php";
        request = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            sp = getSharedPreferences("sessionLogin", MODE_PRIVATE);
                            session = sp.edit();
                            JSONObject object = new JSONObject(response);
                            JSONObject objData = object.getJSONObject("data");


                            Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(Login.this, objData.getString("role"), Toast.LENGTH_SHORT).show();

                            //session login berhasil
//                            if(object.getString("status").equals("Berhasil")){
//                                switch (objData.getInt("role")){
//                                    case 1:
//                                        Toast.makeText(Login.this, "admin", Toast.LENGTH_SHORT).show();
//                                        break;
//
//                                    case 2:
//                                        Toast.makeText(Login.this, "penjual", Toast.LENGTH_SHORT).show();
//                                        break;
//
//                                    case 3:
//                                        Toast.makeText(Login.this, "pembeli", Toast.LENGTH_SHORT).show();
//                                        break;
//                                }
//
//                            } else if(object.getString("status").equals("Gagal")){
//                                //gagal
//                                Toast.makeText(Login.this, "gagal", Toast.LENGTH_SHORT).show();
//                            }

                            if(object.getString("status").equals("Berhasil")){
                                switch (objData.getInt("role")){
                                    case 1:
//                                        session.putString("idPengguna", objData.getString("ID_PENGGUNA"));
//                                        session.putString("rolePengguna", objData.getString("ROLE_PENGGUNA"));
//                                        session.putString("userPengguna", objData.getString("USERNAME_PENGGUNA"));
//                                        session.putString("passPengguna", objData.getString("PASS_PENGGUNA"));
                                        Toast.makeText(Login.this, "Admin", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 2:
//                                        session.putString("idPengguna", objData.getString("ID_PENGGUNA"));
//                                        session.putString("rolePengguna", objData.getString("ROLE_PENGGUNA"));
//                                        session.putString("userPengguna", objData.getString("USERNAME_PENGGUNA"));
//                                        session.putString("passPengguna", objData.getString("PASS_PENGGUNA"));

//                                        Toast.makeText(Login.this, "Penjual", Toast.LENGTH_SHORT).show();
                                        penjual = new Intent(Login.this, DashboardPenjual.class);
                                        Login.this.startActivity(penjual);
                                        finish();
                                        break;

                                    case 3:
//                                        session.putString("idPengguna", objData.getString("ID_PENGGUNA"));
//                                        session.putString("rolePengguna", objData.getString("ROLE_PENGGUNA"));
//                                        session.putString("userPengguna", objData.getString("USERNAME_PENGGUNA"));
//                                        session.putString("passPengguna", objData.getString("PASS_PENGGUNA"));
    //                                    Toast.makeText(Login.this, "Pembeli", Toast.LENGTH_SHORT).show();
                                        pembeli = new Intent(Login.this, DashboardPembeli.class);
                                        Login.this.startActivity(pembeli);
                                        finish();
                                        break;
                                }
                            } else if(object.getString("status").equals("Gagal")){
                                //gagal
                                Toast.makeText(Login.this, "gagal", Toast.LENGTH_SHORT).show();
                            }

                            //ccccccccccccccccccccccccccccccccccccccccccccccccccccccccc

//                                if (object.getString("status").equals("Berhasil") && objData.getInt("role")==1) {
//                                    session.putString("idPengguna", objData.getString("ID_PENGGUNA"));
//                                    session.putString("rolePengguna", objData.getString("ROLE_PENGGUNA"));
//                                    session.putString("userPengguna", objData.getString("USERNAME_PENGGUNA"));
//                                    session.putString("passPengguna", objData.getString("PASS_PENGGUNA"));
//                                    Toast.makeText(Login.this, "Admin", Toast.LENGTH_SHORT).show();
//                                }else if ( object.getString("status").equals("Berhasil") && objData.getInt("role")==2) {
//                                    session.putString("idPengguna", objData.getString("ID_PENGGUNA"));
//                                    session.putString("rolePengguna", objData.getString("ROLE_PENGGUNA"));
//                                    session.putString("userPengguna", objData.getString("USERNAME_PENGGUNA"));
//                                    session.putString("passPengguna", objData.getString("PASS_PENGGUNA"));
//
//                                    Toast.makeText(Login.this, "Penjual", Toast.LENGTH_SHORT).show();
////                                    startActivity(new Intent(Login.this, DashboardPenjual.class)
////                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
////                                    finish();
//                                } else if ( object.getString("status").equals("Berhasil") && objData.getInt("role")==3){
//                                    session.putString("idPengguna", objData.getString("ID_PENGGUNA"));
//                                    session.putString("rolePengguna", objData.getString("ROLE_PENGGUNA"));
//                                    session.putString("userPengguna", objData.getString("USERNAME_PENGGUNA"));
//                                    session.putString("passPengguna", objData.getString("PASS_PENGGUNA"));
//                                    Toast.makeText(Login.this, "Pembeli", Toast.LENGTH_SHORT).show();
////                                    startActivity(new Intent(Login.this, DashboardPembeli.class)
////                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
////                                    finish();
//                                } else if ( object.getString("status").equals("Gagal")){
//                                //gagal
//                                Toast.makeText(Login.this, "Gagal Login", Toast.LENGTH_SHORT).show(); }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

//                            session.putString("rolePengguna", object.getString("role"));

//                            if(object.getInt("role") == 1){


//                                session.saveBoolean(sessionManager.IS_LOGGIN, true);
                                //###########
//                                if (object.getString("role") == "1"){
//                                    Toast.makeText(Login.this, "Admin", Toast.LENGTH_SHORT).show();
////                                    startActivity(new Intent(Login.this, MainActivity.class)
////                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
////                                    finish();
//                                }else if (object.getString("role")== "2"){
//                                    startActivity(new Intent(Login.this, DashboardPenjual.class)
//                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
//                                    finish();
//                                }else if (object.getString("role") == "3"){
//                                    startActivity(new Intent(Login.this, DashboardPembeli.class)
//                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
//                                    finish();
//                                }

//                            } else {
//                                Toast.makeText(Login.this, "xxx", Toast.LENGTH_SHORT).show();
//                            }

//                            status = object.getString("status");
//                            if (status.equals("Berhasil")){
//                                Toast.makeText(getApplicationContext(),"OKE",Toast.LENGTH_SHORT).show();
//                            }else if (status.equals("Gagal")){
//                                Toast.makeText(getApplicationContext(),"Silahkan melakukan Registrasi",
//                                        Toast.LENGTH_SHORT).show();
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        ){
           protected Map<String,String> getParams(){
               Map<String,String> data = new HashMap<String,String>();
               data.put("usernameKey",username.getText().toString());
               data.put("passwordKey",password.getText().toString());
               return data;
           }
        }
        ;
        queue = Volley.newRequestQueue(Login.this);
        queue.add(request);
        }
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