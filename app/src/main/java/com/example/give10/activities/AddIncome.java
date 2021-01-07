package com.example.give10.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.give10.R;
import com.example.give10.classes.DateUtils;
import com.example.give10.lib.DialogUtils;
import com.example.give10.models.Give10;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddIncome extends AppCompatActivity {

    // Fields for add income
    private EditText addAmount;
    private EditText addDate;
    private EditText addSource;
//    private Button addComplete;
    private Give10 mGive10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        setupToolbar();
        setupFAB();
        getIncomingGive10();

        // Initialize fields for add income
        addAmount = findViewById(R.id.t_add_amount);
        addDate = findViewById(R.id.date_income);
        addSource = findViewById(R.id.source);
        addDate.setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    selectDate(v);
                }
            });

    }

    private void getIncomingGive10() {
        Intent intent = getIntent();
        String gson = intent.getExtras().getString("GIVE10");
        mGive10 = Give10.getObjectFromGSONString(gson);
    }

    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.add_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addAmount != null) {
                    incomeTransaction();
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
                    selection -> addDate.setText(DateUtils.getFormattedDate(selection))
            );
            picker.show(getSupportFragmentManager(), picker.toString());

        } catch (Exception exc){
            Snackbar.make(view, "There was an error", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    private void incomeTransaction() {
        // TODO Implement method incomeTransaction

        String amount = addAmount.getText().toString();
        DialogUtils.showInfoDialog(this, "ADD INCOME", "Successfully added $" +
                amount + " to your income");
        mGive10.setIncome(Double.parseDouble(amount));
        addAmount.setText(null);
        addDate.setText(null);
        addSource.setText(null);
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