# Idempotency Gateway (Pay-Once Protocol)

A Spring Boot REST API that prevents duplicate payment processing using an **Idempotency-Key**.

This project simulates a fintech payment backend where repeated payment requests with the same key are processed only once.

---

# Problem Statement

Payment systems can receive duplicate requests because of:

- Network timeout
- Client retry logic
- Slow server response

Without protection, customers may be charged more than once.

This project solves that problem using an idempotency layer.

---

# How It Works

- First request with a new key is processed normally.
- The response is saved in memory.
- Repeated request with the same key and same body returns the saved response instantly.
- Same key with a different request body returns `409 Conflict`.

---
# Architecture Diagram
    src/main/java/com/amalitech/idempotency
        │── controller
        │── service
        │── model
        │── store
        │── exception

BASE URL: http://localhost:8080
s
# Architecture Diagram

```text
Client
   |
   | POST /process-payment
   | Header: Idempotency-Key: abc123
   | Body: {"amount":100,"currency":"GHS"}
   v
PaymentController
   |
   v
PaymentService
   |
   |---- Key exists?
         |
         |-- NO --> Process payment (2 sec delay)
         |         Save request + response
         |         Return success
         |
         |-- YES --> Compare request body
                    |
                    |-- Same body --> Return saved response
                    |                Header: X-Cache-Hit: true
                    |
                    |-- Different body --> 409 Conflict
                

