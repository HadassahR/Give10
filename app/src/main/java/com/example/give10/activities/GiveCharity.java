package com.example.give10.activities;

import android.os.Bundle;

import com.example.give10.R;
import com.example.give10.classes.DateUtils;
import com.example.give10.lib.DialogUtils;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class GiveCharity extends AppCompatActivity {

    // Fields for add income
    private EditText giveAmount;
    private EditText giveDate;
    private EditText giveSource;
//    private Button giveComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_charity);
        setupToolbar();
        setupFAB();

        // Initialize fields for add income
        giveAmount = findViewById(R.id.t_give_amount);
        giveDate = findViewById(R.id.give_date);
        giveSource = findViewById(R.id.give_source);
//        giveComplete = findViewById(R.id.give_fab);

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
                    charityTransaction();
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

    private void charityTransaction() {
        // TODO Implement method charityTransaction
        String amount = giveAmount.getText().toString();
        DialogUtils.showInfoDialog(this, "GIVE CHARITY", "Successfully gave $" +
                amount + " to charity");
        giveAmount.setText(null);
        giveDate.setText(null);
        giveSource.setText(null);
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