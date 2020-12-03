package com.example.nanuda.expense;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nanuda.R;
import com.example.nanuda.objects.Expense;
import com.example.nanuda.objects.Group;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    ArrayList<Expense> expenses;
    Group group;
    //String expenseName[], paidBy[], amount[], date[];
    Context context;

    public ExpenseAdapter(Context ct, ArrayList<Expense> expenses, Group group ){
        context = ct;
        this.expenses = expenses;
    }
    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =    inflater.inflate(R.layout.expenses_list_item,parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        holder.expenseText.setText(expenses.get(position).getTitle());
        holder.paidByText.setText(expenses.get(position).getPayer());
        // TODO: change to use getAmountAsString()
        holder.amountText.setText((expenses.get(position).getAmount()).toString());
        holder.dateText.setText(expenses.get(position).getDate().toString());

        holder.expenseListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditExpenseActivity.class);
                intent.putExtra("com.example.nanuda.GROUP", group);
                intent.putExtra("com.example.nanuda.EXPENSE", expenses.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder{

        TextView expenseText, paidByText, amountText, dateText;
        ConstraintLayout expenseListLayout;

        public ExpenseViewHolder(@NonNull View itemView) {

            super(itemView);
            expenseText = itemView.findViewById(R.id.expense_name);
            paidByText = itemView.findViewById(R.id.paid_by);
            amountText = itemView.findViewById(R.id.amount);
            dateText = itemView.findViewById(R.id.date);
            expenseListLayout = itemView.findViewById(R.id.expenseListLayout);

        }
    }
}
