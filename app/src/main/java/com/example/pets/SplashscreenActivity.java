package com.example.pets;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashscreenActivity extends AppCompatActivity {
    private String TAG = SplashscreenActivity.class.getSimpleName();

    public MyPreference myPreference;

    private int TIME_OUT = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        myPreference = new MyPreference(SplashscreenActivity.this );

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (MyPreference.getSharedPreferences().getBoolean(MyPreference.IS_LOGIN, false)){
                    Intent intent = new Intent(SplashscreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashscreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        }, TIME_OUT);
    }
}
