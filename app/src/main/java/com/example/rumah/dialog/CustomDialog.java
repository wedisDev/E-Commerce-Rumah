package com.example.rumah.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.rumah.R;

public class CustomDialog {
    private Activity activity;
    private AlertDialog alertDialog;

    public CustomDialog(Activity activity){
        this.activity = activity;
    }

    public void startDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog, null));
        builder.setCancelable(false);

        alertDialog = builder.create();
//        alertDialog.show();

    }

    public void dismissDialog(){
        alertDialog.dismiss();
    }
}
