package com.amlitech.idempotency.controller;

import com.amlitech.idempotency.model.PaymentRequests;
import com.amlitech.idempotency.model.PaymentResponse;
import com.amlitech.idempotency.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @GetMapping("/")
    public String home(){
        return "Idempotency Gateway running";
    }

    @PostMapping("/process-payment")
    public PaymentResponse processPayment(@RequestHeader("Idempotency-Key") String key, @RequestBody PaymentRequests requests){
        return paymentService.processPayment(key, requests);
    }

}
