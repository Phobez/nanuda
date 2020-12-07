package com.example.nanuda.expense;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nanuda.R;

import java.util.List;

public class PayeesListAdapter extends RecyclerView.Adapter<PayeesListAdapter.ViewHolder> {

    private List<String> participants;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBox;
        private final EditText editText;

        public ViewHolder(View view) {
            super(view);

            checkBox = (CheckBox) view.findViewById(R.id.payeeCheckbox);
            editText = (EditText) view.findViewById(R.id.paidForAmountEditText);
        }

        public CheckBox getCheckBox() { return checkBox; }

        public EditText getEditText() { return editText; }
    }

    public PayeesListAdapter(List<String> participants) {
        this.participants = participants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payee_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getCheckBox().setText(participants.get(position));
        holder.getEditText().setText("0.00");
    }

    @Override
    public int getItemCount() {
        return participants.size();
    }
}
