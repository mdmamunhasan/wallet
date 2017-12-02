package com.etecharena.wallet.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by mamun on 12/2/17.
 */

public class LocalStorage {
    private SharedPreferences sharedPref;

    LocalStorage(Context context) {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    synchronized protected void writeString(String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    synchronized protected String readString(String key, String defaultValue) {
        String value = sharedPref.getString(key, defaultValue);
        return value;
    }

    synchronized protected void writeLong(String key, long value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    synchronized protected long readLong(String key, long defaultValue) {
        long value = sharedPref.getLong(key, defaultValue);
        return value;
    }

    synchronized protected void writeInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    synchronized protected int readInt(String key, int defaultValue) {
        int value = sharedPref.getInt(key, defaultValue);
        return value;
    }

    synchronized protected void writeFloat(String key, float value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    synchronized protected float readFloat(String key, float defaultValue) {
        float value = sharedPref.getFloat(key, defaultValue);
        return value;
    }

    synchronized protected void writeBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    synchronized protected boolean readBoolean(String key, boolean defaultValue) {
        boolean value = sharedPref.getBoolean(key, defaultValue);
        return value;
    }
}
