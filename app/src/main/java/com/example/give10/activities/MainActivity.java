package com.example.give10.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.give10.R;
import com.example.give10.lib.DialogUtils;
import com.example.give10.models.Give10;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Fields for main activity
    private static TextView owed;
    private TextView totalI;
    private TextView totalG;
    private Button btnAdd;
    private Button btnGive;
    private Give10 mGive10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupFAB();

        mGive10 = new Give10();
        // Initialize fields for main
        owed = findViewById(R.id.tv_balance);
        totalI = findViewById(R.id.tv_total_income_num);
        totalG = findViewById(R.id.tv_total_given_num);
        btnAdd = findViewById(R.id.btn_add);
        btnGive = findViewById(R.id.btn_give);

    }

    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAbout();
            }
        });
    }


    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                showSettings();
                return true;
            case R.id.summary:
                showSummary();
                return true;
            case R.id.about:
                showAbout();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSettings() {
        //TODO implement settings
    }

    private void showSummary() {
        //TODO implement showSummary
        startActivity(new Intent(this, Summary.class));
    }

    private void showAbout() {
        DialogUtils.showInfoDialog(this, "About Give 10", getString(R.string.about_app));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 || requestCode == 2) {
            Log.d("MAASER", "Owed: " + mGive10.getAmountOwed());
            owed.setText("$" + mGive10.getAmountOwed());
        }
        else
            super.onActivityResult(requestCode, resultCode, data);

    }

    public void showAddActivity(View view) {
        Intent intent = new Intent(this, AddIncome.class);
        intent.putExtra("GIVE10", mGive10.getGSONFromThis());
        startActivityForResult(intent, 1);
    }

    public void showGiveActivity(View view) {
        Intent intent = new Intent(this, GiveCharity.class);
        intent.putExtra("GIVE10", mGive10.getGSONFromThis());
        startActivityForResult(intent, 2);
    }


}