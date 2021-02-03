package com.example.give10.classes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.give10.R;

public class TransactionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView tv_transaction_type, tv_transaction_date, tv_transaction_amount, tv_description;

    public TransactionViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);
        tv_transaction_type = itemView.findViewById(R.id.tv_transaction_type);
        tv_transaction_date = itemView.findViewById(R.id.tv_transaction_date);
        tv_transaction_amount = itemView.findViewById(R.id.tv_transaction_amount);
        tv_description = itemView.findViewById(R.id.tv_description);

    }

    @Override
    public void onClick(View v) {
        // can probably ignore clicks as this is just a log (item)?
    }
}
