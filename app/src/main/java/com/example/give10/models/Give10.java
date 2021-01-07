package com.example.give10.models;

import com.example.give10.types.TransactionType;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Class keeps both a income/charity running total separate from/in addition to a transactions log
 */
public class Give10 {

    private double mIncome, mCharity;
    private double mPercentage = .10;
    private List<Transaction> mTransactionList;

    public Give10() {
        this.mIncome = 0;
        this.mCharity = 0;
        mTransactionList = new ArrayList<>();
    }

    public double getIncome() {
        return mIncome;
    }

    public void setIncome(double income) {
        setIncome(new Transaction(TransactionType.INCOME, income));
    }

    public void setIncome (Transaction transaction)
    {
        if (transaction.getType().equals(TransactionType.INCOME))
        {
            this.mIncome += transaction.getAmount();
            mTransactionList.add(transaction);
        }
        else
        {
            throw new IllegalArgumentException("Income transactions must be of type Income.");
        }
    }

    public double getCharity() {
        return mCharity;
    }

    public void setCharity(double charity) {
        setCharity(new Transaction(TransactionType.CHARITY, charity));
    }

    public void setCharity (Transaction transaction)
    {
        if (transaction.getType().equals(TransactionType.CHARITY))
        {
            this.mCharity+=transaction.getAmount();
            mTransactionList.add(transaction);
        }
    }

    public double getAmountOwed() {
        double incomeMinusChar = this.mIncome - this.mCharity;
        return incomeMinusChar * mPercentage;
    }

    public void addTransactions(List<Transaction> transactionList)
    {
        for (Transaction current : transactionList)
        {
            if (current.getType().equals(TransactionType.CHARITY))
                mCharity+=current.getAmount();
            else if (current.getType().equals(TransactionType.INCOME))
                mIncome+=current.getAmount();
        }

        this.mTransactionList.addAll(transactionList);

    }

    public ArrayList<Transaction> getTransactionList() {
        return new ArrayList<>(mTransactionList);
    }

    public String getGSONFromThis() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Give10 getObjectFromGSONString(String gsonString) {
        Gson gson = new Gson();
        return gson.fromJson(gsonString, Give10.class);
    }
}
