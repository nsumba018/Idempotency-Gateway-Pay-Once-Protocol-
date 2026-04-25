package com.amlitech.idempotency.store;

import com.amlitech.idempotency.model.PaymentResponse;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class PaymentStore {
    private final ConcurrentHashMap<String, PaymentResponse> store = new ConcurrentHashMap<>();

    public PaymentResponse get(String key){
        return store.get(key);
    }
    public void save(String key, PaymentResponse response){
        store.put(key, response);
    }
    public boolean exists(String key){
        return store.containsKey(key);
    }

}

