package com.example.rumah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rumah.dialog.customDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class kodeOtp extends AppCompatActivity {
    EditText kode1, kode2, kode3, kode4,kode5,kode6;
    Button daftar;
    String OTP,phone,backendotp;
    PhoneAuthCredential phoneAuthCredential;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    TextView send;
    public FirebaseAuth mAuth;
    customDialog dia = new customDialog(kodeOtp.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kode_otp);

        mAuth = FirebaseAuth.getInstance();

        kode1 = (EditText) findViewById(R.id.kode1);
        kode2 = (EditText) findViewById(R.id.kode2);
        kode3 = (EditText) findViewById(R.id.kode3);
        kode4 = (EditText) findViewById(R.id.kode4);
        kode5 = (EditText) findViewById(R.id.kode5);
        kode6 = (EditText) findViewById(R.id.kode6);
        daftar = (Button)findViewById(R.id.daftar);
        send = (TextView) findViewById(R.id.send);

        edtOTP();
        
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daftar();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repeat();
            }
        });
    }

    private void edtOTP() {
        kode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kode2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        kode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kode3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        kode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kode4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        kode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kode5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        kode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                kode6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void daftar()
    {
        if (kode1.getText().toString().isEmpty() || kode2.getText().toString().isEmpty() ||
                kode3.getText().toString().isEmpty() || kode4.getText().toString().isEmpty() ||
                kode5.getText().toString().isEmpty() || kode6.getText().toString().isEmpty()){
        Toast.makeText(kodeOtp.this,"Lengkapi Kode OTP dengan benar !",Toast.LENGTH_LONG).show();
        }else {
           if (backendotp != null){
               OTP = kode1.getText().toString()+""+
                kode2.getText().toString()+""+
                kode3.getText().toString()+""+
                kode4.getText().toString()+""+
                kode5.getText().toString()+""+
                kode6.getText().toString()+"";

               PhoneAuthCredential credential = PhoneAuthProvider.getCredential(backendotp,OTP);
               FirebaseAuth
                       .getInstance()
                       .signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent login = new Intent(kodeOtp.this, login.class);
                                dia.startDialog();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        dia.dismissDialog();
                                        finish();
                                    }
                                },8000);
                                login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(login);
                            } else {
                                Toast.makeText(kodeOtp.this,"Kode OTP Salah !",Toast.LENGTH_LONG).show();
                            }

                        }
                    });
           }
        }
//        OTP = kode1.getText().toString()+""+
//                kode2.getText().toString()+""+
//                kode3.getText().toString()+""+
//                kode4.getText().toString()+""+
//                kode5.getText().toString()+""+
//                kode6.getText().toString()+"";
//
//        phone = getIntent().getStringExtra("phone");
//        backendotp = getIntent().getStringExtra("backendotp");
//
//        if(backendotp != null){
//            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
//                    backendotp, OTP
//            );
//            FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
//                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if(task.isComplete()){
//                                Intent intent = new Intent(kodeOtp.this, login.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(intent);
//                            } else {
//                                Toast.makeText(kodeOtp.this,"Masukkan Kode OTP dengan benar !",Toast.LENGTH_LONG).show();
//                            }
//
//                        }
//                    });
//        }
}

    private void repeat()
    {
        phone = getIntent().getStringExtra("mobile");
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+62"+phone,
                60,
                TimeUnit.SECONDS,
                kodeOtp.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        kode1.setText("");
                        kode2.setText("");
                        kode3.setText("");
                        kode4.setText("");
                        kode5.setText("");
                        kode6.setText("");

                        Toast.makeText(kodeOtp.this, "Kode OTP Berhasil Dikirim", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(kodeOtp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("Error", e.getMessage());
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                    }
                }
        );
    }
}