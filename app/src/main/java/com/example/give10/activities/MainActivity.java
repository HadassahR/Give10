package com.example.give10.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.give10.R;
import com.example.give10.lib.DialogUtils;
import com.example.give10.models.Give10;
import com.example.give10.models.Transaction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Fields for main activity
    private TextView mOwed;
    private TextView mTotalIncome;
    private TextView mTotalGiven;

    private Give10 mGive10;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("GIVE10", mGive10.getGSONFromThis());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String json = savedInstanceState.getString("GIVE10");
        mGive10 = Give10.getObjectFromGSONString(json);

        updateTextViews();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupFAB();
        setupViews();

        mGive10 = new Give10();
    }

    private void setupViews() {
        mOwed = findViewById(R.id.tv_balance);
        mTotalIncome = findViewById(R.id.tv_total_income_num);
        mTotalGiven = findViewById(R.id.tv_total_given_num);
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
        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra("GIVE10", mGive10.getGSONFromThis());
        startActivity(intent);
    }

    private void showAbout() {
        DialogUtils.showInfoDialog(this, "About Give 10", getString(R.string.about_app));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 || requestCode==2) {
            if (resultCode == RESULT_OK) {
                addIncomingTransactions(data);
                updateTextViews();
            }
        } else
            super.onActivityResult(requestCode, resultCode, data);

    }

    private void addIncomingTransactions(@Nullable Intent data) {
        String json = data.getStringExtra("LIST");
        List<Transaction> incomingList = new Gson().fromJson(json,
                new TypeToken<ArrayList<Transaction>>() {
                }.getType());
        mGive10.addTransactions(incomingList);
    }

    private void updateTextViews() {
        mOwed.setText(String.format(Locale.getDefault(), "$%.2f", mGive10.getAmountOwed()));
        mTotalIncome.setText(String.format(Locale.getDefault(), "$%.2f", mGive10.getIncome()));
        mTotalGiven.setText(String.format(Locale.getDefault(), "$%.2f", mGive10.getCharity()));
    }

    public void showAddActivity(View view) {
        Intent intent = new Intent(this, AddIncome.class);
        startActivityForResult(intent, 1);
    }

    public void showGiveActivity(View view) {
        Intent intent = new Intent(this, GiveCharity.class);
        startActivityForResult(intent, 2);
    }

}