package com.example.nanuda.group;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nanuda.Nanuda;
import com.example.nanuda.R;
import com.example.nanuda.expense.ExpensesListActivity;
import com.example.nanuda.objects.Group;

import java.util.List;

public class GroupsListAdapter extends RecyclerView.Adapter<GroupsListAdapter.ViewHolder> {

    private final List<Group> groupsList;
    private final Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout linearLayout;
        private final TextView nameTextView;
        private final TextView descTextView;

        public ViewHolder(View view) {
            super(view);

            linearLayout = (LinearLayout) view.findViewById(R.id.groupsListItemLinearLayout);
            nameTextView = (TextView) view.findViewById(R.id.groupsListItemName);
            descTextView = (TextView) view.findViewById(R.id.groupsListItemDesc);
        }

        public LinearLayout getLinearLayout() { return linearLayout; }

        public TextView getNameTextView() { return nameTextView; }

        public TextView getDescTextView() { return descTextView; }
    }

    public GroupsListAdapter(Context context, List<Group> groupsList) {
        this.context = context;
        this.groupsList = groupsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.groups_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getNameTextView().setText(groupsList.get(position).getName());
        if (groupsList.get(position).getDesc() != null) {
            viewHolder.getDescTextView().setText(groupsList.get(position).getDesc());
        } else {
            viewHolder.getDescTextView().setText("");
        }

        viewHolder.getLinearLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExpensesListActivity.class);
                intent.putExtra(Nanuda.EXTRA_GROUP, groupsList.get(position));
                ((GroupsListActivity)context).startActivityForResult(intent, GroupsListActivity.EXPENSE_REQUEST_CODE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupsList.size();
    }
}
