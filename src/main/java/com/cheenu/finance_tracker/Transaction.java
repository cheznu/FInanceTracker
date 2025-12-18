package com.cheenu.finance_tracker;

public class Transaction{

    //instance variables
    private double transactionAmount;
    private String transactionName;

    //constructors
    public Transaction(double transactionAmount, String transactionName){
        this.transactionAmount = transactionAmount;
        this.transactionName = transactionName;
    }

    //getters and setters
    public double getTransactionAmount() {
        return transactionAmount;
    }
    public String getTransactionName() {
        return transactionName;
    }



}