# Idempotency Gateway (Pay-Once Protocol)

A Spring Boot REST API that prevents duplicate payment processing using an **Idempotency-Key**.

This project simulates a fintech payment backend where repeated payment requests with the same key are processed only once.

---

# Problem Statement

Payment systems may receive duplicate requests because of:

* Network timeout
* Client retry logic
* Slow server response

Without protection, customers may be charged more than once.

This project solves that problem using an idempotency layer.

---

# Tech Stack

* Java 17
* Spring Boot
* Maven
* ConcurrentHashMap (in-memory store)

---

# Project Structure

```text
src/main/java/com/amalitech/idempotency
├── controller
├── service
├── model
├── store
└── exception
```

---

# Base URL

```text
http://localhost:8080
```

---

# Architecture Flow

```text
Client
   |
   | POST /process-payment
   | Header: Idempotency-Key
   v
PaymentController
   |
   v
PaymentService
   |
   |-- New Key --> Process payment (2 sec delay)
   |              Save response
   |
   |-- Existing Key + Same Body --> Return saved response
   |                               X-Cache-Hit: true
   |
   |-- Existing Key + Different Body --> 409 Conflict
```

---

# Setup Instructions

```bash
git clone <repo-url>
cd Idempotency-Gateway-Pay-Once-Protocol-
./mvnw spring-boot:run
```

---

# API Documentation

## POST /process-payment

### Headers

* Content-Type: application/json
* Idempotency-Key: abc123

### Body

```json
{
  "amount": 100,
  "currency": "GHS"
}
```

---

# Responses

## First Request

```json
{
  "message": "Charged 100.0 GHS"
}
```

## Duplicate Request

Returns saved response instantly.

Header:

```text
X-Cache-Hit: true
```

## Same Key Different Body

Status:

```text
409 Conflict
```

---

# Design Decisions

* Used Service Layer to separate controller and business logic.
* Used ConcurrentHashMap for fast in-memory idempotency storage.
* Used headers for key handling as required.

---

# Developer Choice Feature

Added `X-Cache-Hit: true` header to indicate replayed responses.

---

# Author

Nsumba Herve
