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

public class GiveCharity extends AppCompatActivity {

    // Fields for add income
    private EditText giveAmount;
    private EditText giveDate;
    private EditText giveSource;
//    private Button addComplete; // // // Put into view // // //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        setupToolbar();
        setupFAB();

        // Initialize fields for add income
        giveAmount = findViewById(R.id.t_give_amount);
        giveDate = findViewById(R.id.give_date);
        giveSource = findViewById(R.id.give_source);

        if (giveDate != null) {
            giveDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        selectDate(v);
                    }
                }
            });
        }
    }

    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
                    new MaterialPickerOnPositiveButtonClickListener<Long>() {
                        @Override
                        public void onPositiveButtonClick(Long selection) {
                            giveDate.setText(DateUtils.getFormattedDate(selection));
                        }
                    }
            );
            picker.show(getSupportFragmentManager(), picker.toString());

        } catch (Exception exc){
            Snackbar.make(view, "There was an error", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}