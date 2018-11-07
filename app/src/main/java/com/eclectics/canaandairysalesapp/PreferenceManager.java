package com.eclectics.canaandairysalesapp;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor spEditor;
    private Context context;
    private static final String FIRST_LAUNCH = "firstLaunch";
    private static final String PREFERENCE = "Brookside_Dairy";

    public PreferenceManager(Context context) {
        this.context = context;
        int MODE = 0;
        sharedPreferences = context.getSharedPreferences(PREFERENCE, MODE);
        spEditor = sharedPreferences.edit();
        spEditor.apply();
    }

    /*public void setFirstTimeLaunch(boolean isFirstTime) {
        spEditor.putBoolean(FIRST_LAUNCH, isFirstTime);
        spEditor.commit();
    }*/

    public boolean FirstLaunch() {
        return sharedPreferences.getBoolean(FIRST_LAUNCH, true);
    }

}
