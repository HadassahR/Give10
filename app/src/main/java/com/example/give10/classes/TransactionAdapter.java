package com.example.give10.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.give10.R;
import com.example.give10.interfaces.AdapterOnItemClickListener;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionViewHolder> {

    static AdapterOnItemClickListener sAdapterOnItemClickListener;

    // What else do I need to add here?


    public void setsAdapterOnItemClickListener(AdapterOnItemClickListener sAdapterOnItemClickListener) {
        TransactionAdapter.sAdapterOnItemClickListener = sAdapterOnItemClickListener;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.transaction_entry, parent, false);
        return new TransactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        // ???
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

