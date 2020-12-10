package com.example.nanuda.group;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nanuda.R;
import com.example.nanuda.objects.Group;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GroupsListActivity extends AppCompatActivity {
    public static final int EXPENSE_REQUEST_CODE = 100;

    private static String GROUPS_FILE_PATH = "groups.txt";
    private static ArrayList<String> groupIds = new ArrayList<String>();

    static ArrayList<String> groupNames = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button makeJoinButton;
    EditText link;
    Button joinButtonPopup;

    private SharedPreferences prefs = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate( R.menu.join_group_menu, menu );

        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected( item );
        if (item.getItemId() == R.id.make_group) {
            Intent intent = new Intent( getApplicationContext(), EditGroupActivity.class );
            startActivity( intent );
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups_list);

        getSupportActionBar().setTitle("Groups");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // TODO: refactor logic to work with group name AND description
        setUpGroupIds();

        makeJoinButton = (Button) findViewById(R.id.MakeJoin);
        makeJoinButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder( GroupsListActivity.this ).setMessage( "What do want to do ?" ).setPositiveButton( "Create group", null ).setNegativeButton( "Join group", null ).show();
                Button createButton = dialog.getButton( AlertDialog.BUTTON_POSITIVE );
                createButton.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(GroupsListActivity.this, MakeGroupActivity.class);
                        startActivity( intent );
                    }
                } );
                Button joinButton = dialog.getButton( AlertDialog.BUTTON_NEGATIVE );
                joinButton.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        joinGroupDialog();
                    }
                } );
            }
        } );

        ParseQuery<Group> query = ParseQuery.getQuery("Group");
        query.whereContainedIn(Group.KEY_OBJECT_ID, groupIds);
        query.findInBackground(new FindCallback<Group>() {
            public void done(List<Group> groupsList, ParseException e) {
                if (e == null) {
                    int groupsListSize = groupsList.size();

                    for (int i = 0; i < groupsListSize; i++) {
                        groupNames.add(groupsList.get(i).getName());

                        /*
                        ListView listView = (ListView) findViewById( R.id.listView );
                        arrayAdapter = new ArrayAdapter(GroupsListActivity.this, android.R.layout.simple_list_item_1, groupNames);
                        listView.setAdapter(arrayAdapter);
                        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                                Intent intent = new Intent( getApplicationContext(), MakeGroupActivity.class );
                                intent.putExtra( "groupId", i );
                                startActivity( intent );
                            }
                        } );

                        listView.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {

                                int itemToDelete = i;

                                new AlertDialog.Builder( GroupsListActivity.this )
                                        .setIcon( android.R.drawable.ic_dialog_alert )
                                        .setTitle( "Are you sure?" )
                                        .setMessage( "Do you want to delete this group?" )
                                        .setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                groupNames.remove( itemToDelete );
                                                arrayAdapter.notifyDataSetChanged();
                                            }
                                        } )
                                        .setNegativeButton( "No", null )
                                        .show();
                                return true;
                            }
                        } );

                         */
                    }

                    setUpRecyclerView(groupsList);
                } else {
                    Log.d("Expenses List", "Error: " + e.getMessage());
                }
            }
        });
    }

    /**
     * Sets up the groups list recycler view.
     */
    private void setUpRecyclerView(List<Group> groups) {
        // set up recycler view
        RecyclerView recyclerView = findViewById(R.id.groupsListRecyclerView);

        GroupsListAdapter groupsListAdapter = new GroupsListAdapter(this, groups);
        recyclerView.setAdapter(groupsListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void joinGroupDialog(){
        dialogBuilder = new AlertDialog.Builder( this );
        final View joinPopupView = getLayoutInflater().inflate( R.layout.join_popup, null );
        link = (EditText)joinPopupView.findViewById( R.id.linkInputPopup );
        joinButtonPopup = (Button) joinPopupView.findViewById( R.id.joinGroupButtonPopup );
        dialogBuilder.setView( joinPopupView );
        dialog = dialogBuilder.create();
        dialog.show();

        joinButtonPopup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss( );
            }
        } );
    }

    /**
     * Sets up the Groups ID array list.
     */
    private void setUpGroupIds() {
        prefs = getSharedPreferences(getApplicationContext().getPackageName(), MODE_PRIVATE);

        File groupIdsFile = new File(getFilesDir() + "/" + GROUPS_FILE_PATH);
        if (groupIdsFile.exists()) {
            groupIds = readGroupIds(this);
        } else {
            if (prefs.getBoolean("firstRun", true)) {
                prefs.edit().putBoolean("firstRun", false).apply();
                List<String> sampleParticipants = new ArrayList<String>();
                sampleParticipants.add("James");
                sampleParticipants.add("Mary");
                sampleParticipants.add("Robert");
                Group sampleGroup = new Group("Sample Group", "This is a sample group.", Group.Currency.KRW, sampleParticipants);
                sampleGroup.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            groupIds.add(sampleGroup.getObjectId());
                            writeGroupIds(groupIds, GroupsListActivity.this);
                        } else {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                writeGroupIds(groupIds, this);
            }
        }
    }

    /**
     * Write group IDs to the groups.txt file.
     * @param groupIds Group IDs as a list of strings.
     * @param context
     */
    private void writeGroupIds(ArrayList<String> groupIds, Context context) {
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(GROUPS_FILE_PATH, MODE_PRIVATE);

            StringBuilder data = new StringBuilder();

            for (String s : groupIds) {
                data.append(s);
                data.append("\n");
            }

            fos.write(data.toString().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Reads group IDs from the groups.txt file.
     * @param context
     * @return Group IDs as a list of strings.
     */
    private ArrayList<String> readGroupIds(Context context) {
        ArrayList<String> groupIds = new ArrayList<String>();

        try {
            InputStream inputStream = context.openFileInput(GROUPS_FILE_PATH);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ((receiveString = bufferedReader.readLine()) != null) {
                    groupIds.add(receiveString);
                }

                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e(context.toString(), "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e(context.toString(), "Cannot read file: " + e.toString());
        }

        return groupIds;
    }
}