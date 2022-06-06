package com.example.rumah.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.rumah.R;

public class otpDialog {
    private Activity activity;
    private AlertDialog alertDialog;

    public otpDialog(Activity activity){
        this.activity = activity;
    }

    public void startDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.otp_dialog, null));
        builder.setCancelable(false);

        alertDialog = builder.create();
        alertDialog.show();

    }

    void otpDismiss(){
        alertDialog.dismiss();
    }
}
