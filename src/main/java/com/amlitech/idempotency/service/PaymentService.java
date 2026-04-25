package com.amlitech.idempotency.service;

import com.amlitech.idempotency.model.PaymentRequests;
import com.amlitech.idempotency.model.PaymentResponse;
import com.amlitech.idempotency.store.PaymentStore;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentStore paymentStore;

    public PaymentService(PaymentStore paymentStore) {
        this.paymentStore = paymentStore;
    }

    public PaymentResponse processPayment(String key, PaymentRequests requests){
        if(paymentStore.exists(key)){
            return paymentStore.get(key);
        }
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        PaymentResponse response = new PaymentResponse("Charged " + requests.getAmount() + " " + requests.getCurrency());
        paymentStore.save(key, response);

        return response;
    }
}
