package com.example.give10.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.give10.R;
import com.example.give10.classes.TransactionAdapter;
import com.example.give10.models.Give10;
import com.example.give10.models.Transaction;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {

    private Give10 mGive10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        setupToolbar();
        getIncomingData();
        setupRV();
    }

    private void getIncomingData() {
        Intent intent = getIntent();
        String json = intent.getStringExtra("GIVE10");
        if (json == null) {
            Toast.makeText(getApplicationContext(),
                    "No transactions to show", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            mGive10 = Give10.getObjectFromGSONString(json);
        }
    }

    private void setupRV() {
        RecyclerView rv = findViewById(R.id.transactions);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new TransactionAdapter(mGive10.getTransactionList()));
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

// TODO fix layout
// TODO add a reset button - better idea to add that in MainActivity where there is access to obj.