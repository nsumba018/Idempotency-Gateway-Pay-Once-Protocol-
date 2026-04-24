package com.amlitech.idempotency.model;

public class PaymentRequests {
    private double amount;
    private String currency;

    public PaymentRequests(){

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
