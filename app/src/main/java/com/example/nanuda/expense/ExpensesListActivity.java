package com.example.nanuda.expense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nanuda.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ExpensesListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String expenseName[], paidBy[], amount[], date[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_lists);

        getSupportActionBar().setTitle("the group name");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.expenses_recycler);

        expenseName= getResources().getStringArray(R.array.expense_name);
        paidBy = getResources().getStringArray(R.array.paid_by);
        amount = getResources().getStringArray(R.array.amount);
        date = getResources().getStringArray(R.array.date_array);

        ExpenseAdapter expenseAdapter = new ExpenseAdapter( this,expenseName, paidBy, amount, date );
        recyclerView.setAdapter(expenseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton btn = (FloatingActionButton)findViewById(R.id.addExpenseButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpensesListActivity.this, MakeExpenseActivity.class));
            }
        });
    }
}