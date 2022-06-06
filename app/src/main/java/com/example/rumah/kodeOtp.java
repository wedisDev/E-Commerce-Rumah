package com.example.rumah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
    EditText kode1, kode2, kode3, kode4;
    Button daftar;
    String OTP,phone;
    TextView send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kode_otp);

        kode1 = (EditText) findViewById(R.id.kode1);
        kode2 = (EditText) findViewById(R.id.kode2);
        kode3 = (EditText) findViewById(R.id.kode3);
        kode4 = (EditText) findViewById(R.id.kode4);
        daftar = (Button)findViewById(R.id.daftar);
        send = (TextView) findViewById(R.id.send);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daftar();

                customDialog customDialog = new customDialog(kodeOtp.this);
                Intent daftar = new Intent(kodeOtp.this, login.class);

                customDialog.startDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        customDialog.dismissDialog();
                        finish();
                    }
                },8000);
                kodeOtp.this.startActivity(daftar);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Kode OTP Terkirim",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void daftar()
    {
        OTP = kode1.getText().toString()+""+
                kode2.getText().toString()+""+
                kode3.getText().toString()+""+
                kode4.getText().toString()+"";

        phone = getIntent().getStringExtra("phone");
        String verificationId = getIntent().getStringExtra("verificationId");

        if(verificationId != null){
            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                    verificationId, OTP
            );
            FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isComplete()){
                                Intent intent = new Intent(kodeOtp.this, login.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(kodeOtp.this,"Masukkan Kode OTP dengan benar !",Toast.LENGTH_LONG).show();
                            }

                        }
                    });
        }
}

    private void repeat()
    {
        phone = getIntent().getStringExtra("phone");
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+62"+phone,
                60,
                TimeUnit.SECONDS,
                kodeOtp.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        Toast.makeText(kodeOtp.this, "Kode Verifikasi Berhasil Dikirim", Toast.LENGTH_SHORT).show();
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