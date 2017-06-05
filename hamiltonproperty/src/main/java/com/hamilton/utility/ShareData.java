package com.hamilton.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareData {

    private final static String PREF_NAME = "hamilton";
    private final static ShareData shareData = new ShareData();
    private static SharedPreferences mPref = null;
    private static SharedPreferences.Editor mEditor = null;

    private ShareData() {
    }

    public static ShareData getInstance(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return shareData;
    }


    public void addToSharedPref(String key, String data) {

        mEditor = mPref.edit();
        mEditor.putString(key, data);
        mEditor.apply();
    }

    public void addToSharedPref(String key, Integer data) {
        mEditor = mPref.edit();
        mEditor.putInt(key, data);
        mEditor.apply();

    }

    public void addToSharedPref(String key, Long value) {
        mEditor = mPref.edit();
        mEditor.putLong(key, value);
        mEditor.apply();
    }

    public void addToSharedPref(String key, boolean value) {
        mEditor = mPref.edit();
        mEditor.putBoolean(key, value);
        mEditor.apply();
    }

    public void clearSharedPref(String key) {
        mEditor = mPref.edit();
        mEditor.remove(key);
        mEditor.apply();
    }

    /**
     * Default value is null
     *
     * @param key
     * @return
     */
    public String getDataFromSharedPref(String key) {
        return mPref.getString(key, null);
    }

    public Boolean getBooleanFromSharedPref(String key, Boolean defaultValue) {
        return mPref.getBoolean(key, defaultValue);
    }

    public void addToSharedPref(String key, Boolean value) {
        mEditor = mPref.edit();
        mEditor.putBoolean(key, value);
        mEditor.apply();
    }

    public Integer getIntegerFromSharedPref(String key, Integer defaultValue) {
        return mPref.getInt(key, defaultValue);
    }

    public Long getLongFromSharedPref(String key, Long defaultValue) {
        return mPref.getLong(key, defaultValue);
    }


}
