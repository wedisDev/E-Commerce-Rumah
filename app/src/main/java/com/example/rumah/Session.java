package com.example.rumah;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    public static final String SESSION = "rugit";
    public static final String KEY_ID_PENGGUNA = "id_pengguna";
    public static final String KEY_NAMA_PENGGUNA = "nama_pengguna";
    public static final String KEY_TELP_PENGGUNA = "telp_pengguna";
    public static final String KEY_LEVEL_PENGGUNA = "level";
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
    public static String getKeyIdUser() {
        return sp.getString(KEY_ID_PENGGUNA, "");
    }

    public static String getKeyNamaUser() {
        return sp.getString(KEY_NAMA_PENGGUNA, "");
    }

    public static String getKeyTelpUser() {
        return sp.getString(KEY_TELP_PENGGUNA, "");
    }

    public static String getKeyLevelUser() {
        return sp.getString(KEY_LEVEL_PENGGUNA, "");
    }

    public static Boolean getIsLoggin() {
        return sp.getBoolean(IS_LOGGIN, false);
    }
}
