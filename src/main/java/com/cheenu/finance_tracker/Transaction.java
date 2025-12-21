package com.cheenu.finance_tracker;

public class Transaction{

    //instance variables
    private final double transactionAmount;
    private final String transactionName;

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