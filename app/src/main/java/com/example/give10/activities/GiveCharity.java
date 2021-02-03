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

public class GiveCharity extends AppCompatActivity {

    // Fields for give charity
    private EditText giveAmount;
    private EditText giveDate;
    private EditText giveSource;
    private List<Transaction> mTransactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_charity);
        setupToolbar();
        setupFAB();

        mTransactionList = new ArrayList<>();

        // Initialize fields for add income
        giveAmount = findViewById(R.id.t_give_amount);
        giveDate = findViewById(R.id.give_date);
        giveSource = findViewById(R.id.give_source);

        giveDate.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                selectDate(v);
            }
        });
    }

    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.give_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (giveAmount != null) {
                    createNewTransaction();
                    alertIncomeAmountGiven();
                    clearFields();
//                    charityTransaction();
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
            builder.setTitleText("Select date charity given");
            final MaterialDatePicker<Long> picker = builder.build();

            picker.addOnPositiveButtonClickListener(
                    selection -> giveDate.setText(DateUtils.getFormattedDate(selection))
            );
            picker.show(getSupportFragmentManager(), picker.toString());

        } catch (Exception exc) {
            Snackbar.make(view, "There was an error", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    private void createNewTransaction(){
        String amount = giveAmount.getText().toString();
        double dblAmount = Double.parseDouble(amount);
        String date = giveDate.getText().toString();
        String src = giveSource.getText().toString();

        mTransactionList.add(new Transaction(TransactionType.CHARITY, dblAmount, date, src));
    }

    private void clearFields() {
        giveAmount.setText(null);
        giveDate.setText(null);
        giveSource.setText(null);
    }

    public void alertIncomeAmountGiven() {
        String amount = giveAmount.getText().toString();
        DialogUtils.showInfoDialog(this, "GIVE CHARITY", "Successfully gave $" +
                amount + " to charity.");
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