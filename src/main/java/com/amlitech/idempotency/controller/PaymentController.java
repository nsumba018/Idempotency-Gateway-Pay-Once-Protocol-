package com.amlitech.idempotency.controller;

import com.amlitech.idempotency.model.PaymentRequests;
import com.amlitech.idempotency.model.PaymentResponse;
import com.amlitech.idempotency.model.PaymentResult;
import com.amlitech.idempotency.service.PaymentService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PaymentResponse> processPayment(
            @RequestHeader("Idempotency-Key") String key,
            @RequestBody PaymentRequests request) {

        PaymentResult result = paymentService.processPayment(key, request);

        if (result.isCacheHit()) {
            return ResponseEntity.ok()
                    .header("X-Cache-Hit", "true")
                    .body(result.getResponse());
        }

        return ResponseEntity.ok(result.getResponse());
    }

}
