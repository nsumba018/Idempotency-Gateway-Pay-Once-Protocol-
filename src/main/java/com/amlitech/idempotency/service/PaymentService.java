package com.amlitech.idempotency.service;

import com.amlitech.idempotency.model.PaymentRequests;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public String processPayment(PaymentRequests requests){
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "Charged " + requests.getAmount() + " " + requests.getCurrency();
    }
}
