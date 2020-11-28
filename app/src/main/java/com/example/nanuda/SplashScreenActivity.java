package com.example.nanuda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

/**
 * Activity that displays the splash screen during the app's cold start.
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        finish();
    }
}