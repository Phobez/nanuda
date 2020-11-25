package com.example.nanuda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class create_new_expense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_expense);
    }
    // idk about this lol

    //get the spinner from the xml.
    Spinner dropdown = findViewById(R.id.spinner1);
    //create a list of items for the spinner.
    String[] whopaid = new String[]{"abia", "nadya", "hugo"};
    //create an adapter to describe how the items are displayed, adapters are used in several places in android.
    //There are multiple variations of this, but this is the basic variant.
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, whopaid);
}