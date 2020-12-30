package com.example.give10;

import android.os.Bundle;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

public class AddIncome extends AppCompatActivity {

    // Fields for add income
    private EditText addAmount;
    private EditText addDate;
    private EditText addSource;
//    private Button addComplete; // // // Put into view // // //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        setupToolbar();
        setupFAB();

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

    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
}