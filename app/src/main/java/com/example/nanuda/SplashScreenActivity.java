package com.example.nanuda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.nanuda.balances.BalancesActivity;

/**
 * Activity that displays the splash screen during the app's cold start.
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /*
        // TEST BALANCES DATA
        // TODO: remove
        Intent tempIntent = new Intent(SplashScreenActivity.this, BalancesActivity.class);

        List<String> participants = new ArrayList<String>();
        participants.add("A");
        participants.add("B");
        participants.add("C");
        Group group = new Group("Test", Group.Currency.KRW, participants);

        tempIntent.putExtra("com.example.nanuda.GROUP", group);

        ArrayList<Expense> expenses = new ArrayList<Expense>();
        List<String> payees = new ArrayList<String>();
        List<Long> owedAmounts = new ArrayList<Long>();

        payees.add("B");
        payees.add("C");

        owedAmounts.add((long) 300000);
        owedAmounts.add((long) 200000);

        Expense expense = new Expense("Test Expense", (long) 500000, new Date(), "A", payees, owedAmounts, group);

        expenses.add(expense);

        payees = new ArrayList<String>();
        owedAmounts = new ArrayList<Long>();

        payees.add("C");

        owedAmounts.add((long) 200000);

        expense = new Expense("Test Expense", (long) 200000, new Date(), "B", payees, owedAmounts, group);

        expenses.add(expense);

        tempIntent.putParcelableArrayListExtra("com.example.nanuda.EXPENSES", expenses);

        int requestCode = 100;

        startActivityForResult(tempIntent, requestCode);
        */

        startActivity(new Intent(SplashScreenActivity.this, GroupsListActivity.class));
        finish();
    }

    // TEST BALANCES METHOD
    // TODO: remove
    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i("TEST", "RequestCode:" + requestCode);
        Log.i("TEST", "ResultCode:" + resultCode );
        if (requestCode == 100) {
            switch (resultCode) {
                case RESULT_OK:
                    Log.i("TEST", "Message: " + ((Group) data.getParcelableExtra("com.example.nanuda.GROUP")).getName());
                    ArrayList<Expense> expenses =  data.getParcelableArrayListExtra("com.example.nanuda.EXPENSES");
                    Log.i("TEST", "Message: " + expenses.get(0).getTitle());
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    */
}