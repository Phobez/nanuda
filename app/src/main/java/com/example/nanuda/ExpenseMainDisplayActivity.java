package com.example.nanuda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.nanuda.expense.ExpenseAdapter;

public class ExpenseMainDisplayActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String expenseName[], paidBy[], amount[], date[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_main_display);

        recyclerView = findViewById(R.id.expenses_recycler);

        expenseName= getResources().getStringArray(R.array.expense_name);
        paidBy = getResources().getStringArray(R.array.paid_by);
        amount = getResources().getStringArray(R.array.amount);
        date = getResources().getStringArray(R.array.date_array);

        ExpenseAdapter expenseAdapter = new ExpenseAdapter( this,expenseName, paidBy, amount, date );
        recyclerView.setAdapter(expenseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}