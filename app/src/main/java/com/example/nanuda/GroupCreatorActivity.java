package com.example.nanuda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toolbar;

public class GroupCreatorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
int groupId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_group_creator );
        EditText groupName = (EditText) findViewById( R.id.groupName );
        Intent intent = getIntent();
        groupId= intent.getIntExtra("groupId", -1);
        if(groupId != -1 ){
            groupName.setText(MainActivity.groups.get(groupId));
        }
        else{
            MainActivity.groups.add("");
            groupId=MainActivity.groups.size() -1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }
        Spinner mySpinner = findViewById( R.id.spinner1 );
        ArrayAdapter<CharSequence> myAdapter = ArrayAdapter.createFromResource( this,R.array.names, android.R.layout.simple_spinner_item );
        myAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        mySpinner.setAdapter( myAdapter );
        mySpinner.setOnItemSelectedListener( this );
        groupName.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                MainActivity.groups.set(groupId, String.valueOf( s ));
                MainActivity.arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition( position ).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}