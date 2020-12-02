package com.example.nanuda.expense;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nanuda.EditExpenseActivity;
import com.example.nanuda.R;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    String expenseName[], paidBy[], amount[], date[];
    Context context;

    public ExpenseAdapter(Context ct, String expenseName[], String paidBy[], String amount[], String date[]){
        context = ct;
        this.expenseName = expenseName;
        this.paidBy = paidBy;
        this.amount = amount;
        this.date = date;
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
        holder.expenseText.setText(expenseName[position]);
        holder.paidByText.setText(paidBy[position]);
        holder.amountText.setText(amount[position]);
        holder.dateText.setText(date[position]);

        holder.expenseListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditExpenseActivity.class);
                intent.putExtra("expenseName",expenseName[position] );
                intent.putExtra("paidBy",paidBy[position] );
                intent.putExtra("amount",amount[position] );
                intent.putExtra("date",date[position] );
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return expenseName.length;
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
