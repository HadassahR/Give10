package com.example.give10.classes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.give10.R;

public class TransactionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ConstraintLayout cl_transaction_constraint;
    public TextView tv_transaction_type, tv_transaction_date, tv_transaction_amount, tv_description;

    public TransactionViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);
        cl_transaction_constraint = itemView.findViewById(R.id.transaction_constraint);
        tv_description = itemView.findViewById(R.id.description);
        tv_transaction_date = itemView.findViewById(R.id.transaction_date);
        tv_transaction_amount = itemView.findViewById(R.id.transaction_amount);
        tv_description = itemView.findViewById(R.id.description);

    }

    @Override
    public void onClick(View v) {
        //TODO Sent click to MainActivity via adapter
    }
}
