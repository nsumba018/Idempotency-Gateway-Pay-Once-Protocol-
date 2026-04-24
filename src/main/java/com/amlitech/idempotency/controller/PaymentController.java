package com.amlitech.idempotency.controller;

import com.amlitech.idempotency.model.PaymentRequests;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    @PostMapping("/process-payment")
    public String processPayment(@RequestBody PaymentRequests requests){
        return "Charged " + requests.getAmount() + " " + requests.getCurrency();
    }
    @GetMapping("/")
    public String home(){
        return "Idempotency Gateway running";
    }
}
