package com.baharan.service;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class App extends Application {

    private static App myApplication = new App();

    public static App getMyApplication() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

    }

    public void saveSharedPreferences(String MY_PREFS_NAME, String MY_PREFS_TAG, String data, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.clear();
        editor.putString(MY_PREFS_TAG, data);
        editor.apply();
        editor.commit();
    }

    public String getSharedPreferences(String MY_PREFS_NAME, String MY_PREFS_TAG, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        return prefs.getString(MY_PREFS_TAG, "");
    }

}
