package com.example.nanuda.expense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.nanuda.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditExpenseActivity extends AppCompatActivity {

    EditText editExpenseName, editAmount, editDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);

        getSupportActionBar().setTitle("Edit Expense");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editExpenseName = findViewById(R.id.editExpenseName);
        editAmount = findViewById(R.id.editAmount);
        editDate = findViewById(R.id.editDate);
    }

    public void editExpense(View view) {
        EditText editExpenseNameInput = (EditText) view.findViewById(R.id.editExpenseName);
        EditText editAmountInput = (EditText) view.findViewById(R.id.editAmount);
        EditText editDateInput = (EditText) view.findViewById(R.id.editDate);
        Spinner editWhoPaidSpinner = (Spinner) findViewById(R.id.spinnerWhoPaidEdit);



        String editExpenseName = editExpenseNameInput.getText().toString();
        Long editAmount = Long.parseLong(editAmountInput.getText().toString());
        Date newDate = null;
        try {
            newDate = new SimpleDateFormat("dd/MM/yyyy").parse(editDateInput.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newWhoPaid = editWhoPaidSpinner.getSelectedItem().toString();

    }
}