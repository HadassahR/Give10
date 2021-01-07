package com.example.give10.models;

import androidx.annotation.NonNull;

import com.example.give10.types.TransactionType;

public class Transaction {
    private TransactionType type;
    private double amount;
    private String dateReceived;
    private String source;

    public Transaction(TransactionType type, double amount, String dateReceived, String source) {
        if (type == null)
            throw new IllegalArgumentException("Transaction Type cannot be null");

        this.type = type;
        this.amount = amount;
        this.dateReceived = dateReceived;
        this.source = source;
    }

    public Transaction(TransactionType type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public String getSource() {
        return source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = type.hashCode();
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override @NonNull
    public String toString() {
        return "Transaction{" +
                "type=" + type +
                ", amount=" + amount +
                ", dateReceived='" + dateReceived + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
