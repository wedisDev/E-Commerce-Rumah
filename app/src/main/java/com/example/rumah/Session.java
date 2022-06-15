package com.example.rumah;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    public static final String SESSION = "RUGIT";
//    public static final String KEY_ID_USER = "id_user";
    public static final String key_username = "username";
    public static final String key_pass = "password";
//    public static final String KEY_LEVEL_USER = "level";
    public static final String IS_LOGGIN = "is_login";

    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;

    public Session(Context context) {
        sp = context.getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    // Setter
    public void saveString(String key, String value)
    {
        editor.putString(key, value);
        editor.commit();
    }

    public void saveInt(String key, int value)
    {
        editor.putInt(key, value);
        editor.commit();
    }

    public void saveBoolean(String key, boolean value)
    {
        editor.putBoolean(key, value);
        editor.commit();
    }

    // Getter
    public static String getKey_username() {
        return sp.getString(key_username, "");
    }

    public static String getKey_pass() {
        return sp.getString(key_pass, "");
    }

//    public static String getKeyTelpUser() {
//        return sp.getString(KEY_TELP_USER, "");
//    }
//
//    public static String getKeyLevelUser() {
//        return sp.getString(KEY_LEVEL_USER, "");
//    }

    public static Boolean getIsLoggin() {
        return sp.getBoolean(IS_LOGGIN, false);
    }
}

