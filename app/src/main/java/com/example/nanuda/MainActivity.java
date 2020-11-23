package com.example.nanuda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        static ArrayList<String> groups = new ArrayList<>();
        static ArrayAdapter arrayAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate( R.menu.join_group_menu, menu );

        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
         super.onOptionsItemSelected( item );
        if(item.getItemId()== R.id.make_group){
            Intent intent = new Intent(getApplicationContext(), GroupCreatorActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById( R.id.listView );
        groups.add("\n\nSample Group");
        arrayAdapter = new ArrayAdapter( this,android.R.layout.simple_list_item_1, groups );
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent (getApplicationContext(), GroupCreatorActivity.class);
                intent.putExtra("groupId",i);
                startActivity(intent);
            }
        } );

        listView.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {

                int itemToDelete = i;

                new AlertDialog.Builder( MainActivity.this )
                        .setIcon( android.R.drawable.ic_dialog_alert )
                        .setTitle( "Are you sure ? " )
                        .setMessage( "Do you want to delete this group ? " )
                        .setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                groups.remove(itemToDelete);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        } )
                        .setNegativeButton( "No", null )
                        .show();
                return true;
            }
        } );

    }
}