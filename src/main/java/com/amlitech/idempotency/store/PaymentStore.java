package com.amlitech.idempotency.store;

import com.amlitech.idempotency.model.PaymentResponse;
import com.amlitech.idempotency.model.StoredPayment;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class PaymentStore {
    private final ConcurrentHashMap<String, StoredPayment> store = new ConcurrentHashMap<>();

    public StoredPayment get(String key){
        return store.get(key);
    }
    public void save(String key, StoredPayment payment){
        store.put(key, payment);
    }
    public boolean exists(String key){
        return store.containsKey(key);
    }

}

