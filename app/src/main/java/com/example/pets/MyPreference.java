package com.example.pets;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreference {
    private Context context;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private final String ACCOUNTS_PREF = "ACCOUNTS_PREF";
    public static final String IS_LOGIN = "IS_LOGIN";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String KEY = "KEY";
    public MyPreference(Context context) {
        this.context = context;
        this.sharedPreferences = this.context.getSharedPreferences(ACCOUNTS_PREF, Context.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static SharedPreferences.Editor getEditorPreference() {
        return editor;
    }


}
