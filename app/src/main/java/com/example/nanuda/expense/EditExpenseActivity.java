package com.example.nanuda.expense;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.nanuda.R;
import com.example.nanuda.objects.Expense;
import com.example.nanuda.objects.Group;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            Intent intent = new Intent(EditExpenseActivity.this, ExpensesListActivity.class);
            Group group = getIntent().getParcelableExtra("com.example.nanuda.GROUP");
            ArrayList<Expense> expenses = getIntent().getParcelableArrayListExtra("com.example.nanuda.EXPENSES");
            intent.putExtra("com.example.nanuda.GROUP", group);
            intent.putParcelableArrayListExtra("com.example.nanuda.EXPENSES", expenses);
            setResult(Activity.RESULT_OK, intent);
            finishActivity(1);
        }
        return super.onOptionsItemSelected(item);
    }


}