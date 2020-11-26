package com.example.nanuda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;
import java.util.ArrayList;

public class GroupCreatorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
int groupId;
Button save;
ArrayList<String>addArray = new ArrayList<String>();
EditText txt;
ListView show;
int participantNmb = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_group_creator );
        txt = (EditText) findViewById( R.id.ParticipantInput );
        show= (ListView) findViewById( R.id.participantsListView );
        save = (Button) findViewById( R.id.addParticipantButton );
        save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInput = txt.getText().toString();

                if(addArray.contains( getInput )){
                    Toast.makeText( getBaseContext(), "Participant already added ", Toast.LENGTH_LONG ).show();
                }
                else if (getInput == null || getInput.trim().equals( "" )){
                    Toast.makeText( getBaseContext(), "Invalid Name ", Toast.LENGTH_LONG ).show();
                }
                else if (participantNmb==20){
                    Toast.makeText( getBaseContext(), "Maximum participants number reached ", Toast.LENGTH_LONG ).show();
                }
                else{
                    addArray.add(getInput);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(GroupCreatorActivity.this, android.R.layout.simple_list_item_1,addArray  );
                    show.setAdapter( adapter );
                    ((EditText)findViewById( R.id.ParticipantInput )).setText( " " );

                }
            }
        } );
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