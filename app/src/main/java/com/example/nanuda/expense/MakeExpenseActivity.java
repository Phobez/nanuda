package com.example.nanuda.expense;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nanuda.Nanuda;
import com.example.nanuda.R;
import com.example.nanuda.objects.Group;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MakeExpenseActivity extends AppCompatActivity {

    private Group group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_expense);

        getSupportActionBar().setTitle("Add Expense");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpActivity(getIntent());
    }

    private void setUpActivity(Intent intent) {
        group = intent.getParcelableExtra(Nanuda.EXTRA_GROUP);

        // sets up the currency label
        TextView currencyTextView = (TextView) findViewById(R.id.addExpenseCurrencyLabel);
        currencyTextView.setText(group.getCurrency().toString());

        // sets up the payer spinner
        Spinner payerSpinner = (Spinner) findViewById(R.id.addExpensePayerSpinner);

        List<String> participants = group.getParticipants();
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, participants);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payerSpinner.setAdapter(spinnerArrayAdapter);

        // sets up the paid for checkboxes
        RecyclerView recyclerView = findViewById(R.id.payeeListRecyclerView);

        PayeesListAdapter payeesListAdapter = new PayeesListAdapter(participants);
        recyclerView.setAdapter(payeesListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void createNewExpense(View view) {
        EditText newExpenseNameInput = (EditText) view.findViewById(R.id.newExpenseName);
        EditText newAmountInput = (EditText) view.findViewById(R.id.newAmount);
        EditText newDateInput = (EditText) view.findViewById(R.id.newDate);
        Spinner newWhoPaidSpinner = (Spinner) findViewById(R.id.addExpensePayerSpinner);

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