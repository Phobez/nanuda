package com.example.nanuda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class GroupCreatorActivity extends AppCompatActivity {
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
        Spinner mySpinner = (Spinner) findViewById( R.id.spinner1 );
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(GroupCreatorActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray( R.array.names )  );
        myAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        mySpinner.setAdapter( myAdapter );
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
}