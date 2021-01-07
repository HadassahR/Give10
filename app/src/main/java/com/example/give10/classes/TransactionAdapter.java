package com.example.give10.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.give10.R;
import com.example.give10.interfaces.AdapterOnItemClickListener;
import com.example.give10.models.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionViewHolder> {

    /*   // static AdapterOnItemClickListener sAdapterOnItemClickListener;
        public void setsAdapterOnItemClickListener(AdapterOnItemClickListener sAdapterOnItemClickListener) {
            TransactionAdapter.sAdapterOnItemClickListener = sAdapterOnItemClickListener;
        }
    */
    private final ArrayList<Transaction> mTransactions;

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.transaction_entry, parent, false);
        return new TransactionViewHolder(itemView);
    }

    public TransactionAdapter(List<Transaction> transactions) {
        this.mTransactions = new ArrayList<>(transactions);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction current = mTransactions.get(position);
        String date = current.getDateReceived() == null ? "N/A" : current.getDateReceived();
        String src = current.getSource() == null ? "N/A" : current.getSource();

        holder.tv_transaction_type.setText(current.getType().toString());
        holder.tv_transaction_amount.setText(String.format
                (Locale.getDefault(), "$%.2f", current.getAmount()));
        holder.tv_transaction_date.setText(date);
        holder.tv_description.setText(src);
    }

    @Override
    public int getItemCount() {
        return mTransactions.size();
    }
}

