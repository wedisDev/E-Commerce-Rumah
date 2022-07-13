package com.example.rumah.listener;

import android.view.View;

public interface StatusClickListener {
    public void onSuccessClick(View view, String status, String idRumah);
    public void onFailedClick(View view, String status, String idRumah);
}
