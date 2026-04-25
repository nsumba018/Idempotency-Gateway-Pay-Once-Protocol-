package com.amlitech.idempotency.model;

public class PaymentResult {
    private PaymentResponse response;
    private boolean cacheHit;

    public PaymentResult() {
    }

    public PaymentResult(PaymentResponse response, boolean cacheHit) {
        this.response = response;
        this.cacheHit = cacheHit;
    }

    public PaymentResponse getResponse() {
        return response;
    }

    public void setResponse(PaymentResponse response) {
        this.response = response;
    }

    public boolean isCacheHit() {
        return cacheHit;
    }

    public void setCacheHit(boolean cacheHit) {
        this.cacheHit = cacheHit;
    }
}
