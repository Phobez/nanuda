package com.example.nanuda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.nanuda.expense.ExpensesListActivity;
import com.example.nanuda.objects.Expense;
import com.example.nanuda.objects.Group;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Activity that displays the splash screen during the app's cold start.
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // DUMMY INTENT
        // TODO: remove
        Intent tempIntent = new Intent(SplashScreenActivity.this, ExpensesListActivity.class);

        ArrayList<String> participants = new ArrayList<String>();
        participants.add("A");
        participants.add("B");
        participants.add("C");

        Group group = new Group("Test Group", Group.Currency.KRW, participants);

        tempIntent.putExtra("com.example.nanuda.GROUP", group);

        ArrayList<Expense> expenses = new ArrayList<Expense>();

        ArrayList<String> payees = new ArrayList<String>();

        payees.add("B");
        payees.add("C");

        ArrayList<Long> owedAmounts = new ArrayList<Long>();

        owedAmounts.add((long) 400000);
        owedAmounts.add((long) 100000);

        expenses.add(new Expense("Test Expense 1", (long) 500000, new Date(), "A", payees, owedAmounts, group));

        payees = new ArrayList<String>();
        payees.add("C");

        owedAmounts = new ArrayList<Long>();
        owedAmounts.add((long) 200000);

        expenses.add(new Expense("Test Expense 2", (long) 200000, new Date(), "B", payees, owedAmounts, group));

        tempIntent.putParcelableArrayListExtra("com.example.nanuda.EXPENSES", expenses);

        startActivity(tempIntent);
        finish();
    }
}