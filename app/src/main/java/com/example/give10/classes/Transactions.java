package com.example.give10.classes;

import androidx.recyclerview.widget.RecyclerView;

public class Transactions {

    private String transaction;
    private String amount;
    private String date;
    private String description;

    public Transactions(String transaction, String amount, String date, String description) {
        this.transaction = transaction;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
