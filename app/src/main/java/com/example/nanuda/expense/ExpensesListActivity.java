package com.example.nanuda.expense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nanuda.R;
import com.example.nanuda.SplashScreenActivity;
import com.example.nanuda.objects.Expense;
import com.example.nanuda.objects.Group;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ExpensesListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String expenseName[], paidBy[], amount[], date[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_lists);

        Intent intent = getIntent();

        // TODO: replace extra name with static final string
        Group group = intent.getParcelableExtra("com.example.nanuda.GROUP");
        ArrayList<Expense> expenses = new ArrayList<Expense>();

        getSupportActionBar().setTitle(group.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.expensesTabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        // TODO: replace SplashScreenActivity.class with BalancesActivity.class
                        Intent intent = new Intent(ExpensesListActivity.this, SplashScreenActivity.class);
                        // TODO: replace extra names with static final strings
                        intent.putExtra("com.example.nanuda.GROUP", group);
                        intent.putParcelableArrayListExtra("com.example.nanuda.EXPENSES", expenses);

                        startActivity(intent);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

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