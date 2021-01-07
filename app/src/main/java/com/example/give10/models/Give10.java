package com.example.give10.models;

import com.google.gson.Gson;

public class Give10 {

    private double mIncome, mCharity;
    private double percentage = .10;

    public Give10() {
        this.mIncome = 0;
        this.mCharity = 0;
    }

    public double getIncome() {
        return mIncome;
    }

    public void setIncome(double income) {
        this.mIncome += income;
    }

    public double getCharity() {
        return mCharity;
    }

    public void setCharity(double charity) {
        this.mCharity -= charity;
    }

    public double getAmountOwed() {
        double incomeMinusChar = this.mIncome - this.mCharity;
        return incomeMinusChar * percentage;
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
