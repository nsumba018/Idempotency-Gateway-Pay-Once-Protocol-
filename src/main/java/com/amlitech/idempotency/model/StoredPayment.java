package com.amlitech.idempotency.model;

public class StoredPayment {
    private PaymentRequests request;
    private PaymentResponse response;

    public StoredPayment() {
    }

    public StoredPayment(PaymentResponse response, PaymentRequests request) {
        this.response = response;
        this.request = request;
    }

    public PaymentRequests getRequest() {
        return request;
    }

    public void setRequest(PaymentRequests request) {
        this.request = request;
    }

    public PaymentResponse getResponse() {
        return response;
    }

    public void setResponse(PaymentResponse response) {
        this.response = response;
    }
}
