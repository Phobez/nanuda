package com.example.nanuda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

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