package com.example.give10.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.give10.R;
import com.example.give10.classes.DateUtils;
import com.example.give10.lib.DialogUtils;
import com.example.give10.models.Transaction;
import com.example.give10.types.TransactionType;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AddIncome extends AppCompatActivity {

    // Fields for add income
    private EditText mAddAmount;
    private EditText mAddDate;
    private EditText addSource;
    private List<Transaction> mTransactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        setupToolbar();
        setupFAB();

        // current transactions to be sent back if at least one added to it; otherwise ignored
        mTransactionList = new ArrayList<>();

        // Initialize fields for add income
        mAddAmount = findViewById(R.id.t_add_amount);
        mAddDate = findViewById(R.id.date_income);
        addSource = findViewById(R.id.source);
        mAddDate.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                selectDate(v);
            }
        });

    }

    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.add_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAddAmount != null) {
                    createNewTransaction();
                    alertIncomeAmountAdded();
                    clearFields();
                }
            }
        });
    }


    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void selectDate(View view) {
        try {
            MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
            builder.setTitleText("Select date income received");
            final MaterialDatePicker<Long> picker = builder.build();

            picker.addOnPositiveButtonClickListener(
                    selection -> mAddDate.setText(DateUtils.getFormattedDate(selection))
            );
            picker.show(getSupportFragmentManager(), picker.toString());

        } catch (Exception exc) {
            Snackbar.make(view, "There was an error", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    private void alertIncomeAmountAdded() {
        String amount = mAddAmount.getText().toString();
        DialogUtils.showInfoDialog(this, "ADD INCOME", "Successfully added $" +
                amount + " to your income.");
    }

    private void createNewTransaction() {
        String amount = mAddAmount.getText().toString();
        double dblAmount = Double.parseDouble(amount);
        String date = mAddDate.getText().toString();
        String src = addSource.getText().toString();

        mTransactionList.add(
                new Transaction(TransactionType.INCOME, dblAmount, date, src));
    }

    private void clearFields() {
        mAddAmount.setText(null);
        mAddDate.setText(null);
        addSource.setText(null);
    }

    @Override
    public void onBackPressed() {
        sendBackCurrentTransactionsIfAny();
        super.onBackPressed();
    }

    private void sendBackCurrentTransactionsIfAny() {
        int result = mTransactionList.size()>0 ? RESULT_OK : RESULT_CANCELED;
        if (result == RESULT_OK) {
            Intent intent = new Intent();
            String json = new Gson().toJson(mTransactionList,
                    new TypeToken<ArrayList<Transaction>>() {}.getType());
            intent.putExtra("LIST", json);
            setResult(result, intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

}