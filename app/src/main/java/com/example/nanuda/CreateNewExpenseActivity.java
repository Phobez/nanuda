package com.example.nanuda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateNewExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_expense);

        getSupportActionBar().setTitle("Add Expense");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void createNewExpense(View view) {
        EditText newExpenseNameInput = (EditText) view.findViewById(R.id.newExpenseName);
        EditText newAmountInput = (EditText) view.findViewById(R.id.newAmount);
        EditText newDateInput = (EditText) view.findViewById(R.id.newDate);
        Spinner newWhoPaidSpinner = (Spinner) findViewById(R.id.spinnerWhoPaidNew);



        String newExpenseName = newExpenseNameInput.getText().toString();
        Long newAmount = Long.parseLong(newAmountInput.getText().toString());
        Date newDate = null;
        try {
            newDate = new SimpleDateFormat("dd/MM/yyyy").parse(newDateInput.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newWhoPaid = newWhoPaidSpinner.getSelectedItem().toString();


    }
}