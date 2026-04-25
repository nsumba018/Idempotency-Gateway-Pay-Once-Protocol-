package com.amlitech.idempotency.service;

import com.amlitech.idempotency.model.PaymentRequests;
import com.amlitech.idempotency.model.PaymentResponse;
import com.amlitech.idempotency.model.PaymentResult;
import com.amlitech.idempotency.model.StoredPayment;
import com.amlitech.idempotency.store.PaymentStore;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PaymentService {

    private final PaymentStore paymentStore;

    public PaymentService(PaymentStore paymentStore) {
        this.paymentStore = paymentStore;
    }

    public PaymentResult processPayment(String key, PaymentRequests requests){
        if(paymentStore.exists(key)){
            StoredPayment saved = paymentStore.get(key);
            if (saved.getRequest().getAmount() == requests.getAmount() && saved.getRequest().getCurrency().equals(requests.getCurrency())){
                return new PaymentResult(saved.getResponse(), true);
            }else{
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT, "Idempotency key already used for a different request body."
                );
            }
        }
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        PaymentResponse response = new PaymentResponse("Charged " + requests.getAmount() + " " + requests.getCurrency());
        StoredPayment payment = new StoredPayment(response, requests);
        paymentStore.save(key, payment);

        return new PaymentResult(response, false);
    }
}
