package com.example.nanuda.group;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.nanuda.R;
import com.example.nanuda.expense.MakeExpenseActivity;
import com.example.nanuda.objects.Group;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

public class AddGroupDialog extends Dialog implements View.OnClickListener {
    private final Context context;

    private Button makeGroupButton;
    private Button joinGroupButton;

    public AddGroupDialog(@NonNull Context context) {
        super(context);

        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_group_dialog);

        makeGroupButton = (Button) findViewById(R.id.makeGroupButton);
        joinGroupButton = (Button) findViewById(R.id.joinGroupButton);

        makeGroupButton.setOnClickListener(this);
        joinGroupButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.makeGroupButton:
                Intent intent = new Intent(context, MakeExpenseActivity.class);
                ((GroupsListActivity) context).startActivityForResult(intent, GroupsListActivity.MAKE_GROUP_REQUEST_CODE);
                break;
            case R.id.joinGroupButton:
                showJoinGroupDialog();
                break;
            default:
                break;
        }
        dismiss();
    }

    public void showJoinGroupDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        final View joinGroupDialogView = getLayoutInflater().inflate(R.layout.join_group_dialog,null);
        EditText link = (EditText) joinGroupDialogView.findViewById(R.id.groupIdEditText);
        Button joinGroupDialogButton = (Button) joinGroupDialogView.findViewById(R.id.joinGroupDialogButton);

        dialogBuilder.setView(joinGroupDialogView);
        Dialog dialog = dialogBuilder.create();
        dialog.show();

        joinGroupDialogButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<Group> query = ParseQuery.getQuery("Group");
                query.getInBackground(link.getText().toString(), new GetCallback<Group>() {
                    public void done(Group object, ParseException e) {
                        if (e == null) {
                            ((GroupsListActivity)context).updateGroupsList(object);
                        } else {
                            // something went wrong
                            e.printStackTrace();
                        }
                        dialog.dismiss( );
                    }
                });
            }
        } );
    }
}
