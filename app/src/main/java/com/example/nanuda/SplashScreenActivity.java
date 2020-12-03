package com.example.nanuda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.nanuda.expense.ExpensesListActivity;
import com.example.nanuda.objects.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity that displays the splash screen during the app's cold start.
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // DUMMY INTENT
        // TODO: remove
        Intent tempIntent = new Intent(SplashScreenActivity.this, ExpensesListActivity.class);

        Group group = new Group("Test Group", Group.Currency.KRW, new ArrayList<String>());

        tempIntent.putExtra("com.example.nanuda.GROUP", group);

        startActivity(tempIntent);
        finish();
    }
}