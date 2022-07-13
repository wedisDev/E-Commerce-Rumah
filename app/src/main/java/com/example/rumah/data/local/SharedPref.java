package com.example.rumah.data.local;

import static com.example.rumah.data.local.PreferencesUtility.LOGGED_IN_PREF;
import static com.example.rumah.data.local.PreferencesUtility.USER_ID_PREF;
import static com.example.rumah.data.local.PreferencesUtility.USER_ROLE_PREF;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPref {

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setLoggedIn(
            Context context,
            boolean loggedIn,
            String idPengguna,
            String role) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.putString(USER_ID_PREF, idPengguna);
        editor.putString(USER_ROLE_PREF, role);
        editor.apply();
    }

    public static void logout(Context context) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, false);
        editor.remove(USER_ID_PREF);
        editor.remove(USER_ROLE_PREF);
        editor.apply();
    }

    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    public static String getRole(Context context) {
        return getPreferences(context).getString(USER_ROLE_PREF, "");
    }

    public static String getIdPengguna(Context context) {
        return getPreferences(context).getString(USER_ID_PREF, "");
    }
}
