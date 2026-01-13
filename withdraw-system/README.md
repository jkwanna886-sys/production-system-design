# Large-Scale Withdraw System Design
This document describes a withdraw system designed for high-volume financial platforms, where correctness and scalability are critical.

## Business Requirements
The system targets scenarios with:
- Millions of daily withdraw requests
- High write throughput on balances and transaction records
- Strict financial correctness (no double withdrawal, no missing records)
- Strong requirements for auditing, reconciliation, and failure recovery

At this scale, combining balance updates and ledger persistence in a single service becomes a bottleneck and increases operational risk.

## Design Goals
- Scale balance operations and ledger writes independently
- Reduce contention under high concurrency
- Support asynchronous workflows without sacrificing correctness
- Provide strong auditability and reconciliation capabilities
- Isolate failures and limit blast radius

## Core Design Decision: Separate Balance and Ledger Services

At large scale, the system separates Balance Service and Ledger Service into independent components.

### Balance Service
- Low latency
- Strong consistency
- Frequent updates

### Ledger Service
- Append-only writes
- Optimized for durability and auditing
- High write throughput

This separation improves scalability, fault isolation, and operational clarity.

## Withdraw APIs

All APIs follow a unified response format:
```http
{
  "code": int,
  "msg": string,
  "data": object
}
```

- Create withdraw order (async)
```http
POST /withdraw 
idempotent-key:<uuid-v7>
{
    "toAccountId": "123xx",
    "amount": 123.12
}

Response:
{
    "code": 0,
    "msg":"processing",
    "data": {
        "withdrawOrderId": "xyz.."
    }
}
```

- Submit Withdraw to Payment System (Async)
```http
POST /withdraw/submit 
{
    "fromAccountId": "222xx",
    "toAccountId": "123xx",
    "amount":123.12,
    "userId":"111xx",
    "transactionId": "xxxxyyyy"
}
```

- Query Withdraw Status
```http
GET /withdraw/{withdrawOrderId} 
```
Although the workflow is asynchronous, most requests complete quickly, so a status API is useful for frontend confirmation.

Status values:
```text
init → processing → [manual] → success | failed
```

- Third-Party Callback
```http
POST /withdraw/notify
```
The callback is handled by an internal payment-gateway service to isolate third-party instability.

## High-Level Architecture
![Withdraw Architecture](images/withdraw-large.png)

### 1. Idempotency (Cache + Database)
- Frontend provides an idempotent key (UUID-v7 recommended)
- Cache protects the database under high concurrency
- Database remains the source of truth

Fallback to DB is required to guarantee correctness in financial scenarios.

### 2. Withdraw Request Persistence
- Generate a global transactionId
- Insert a withdraw_request record with status init
- Enables reconciliation and safe retries


### 3. Balance Reservation (Synchronous)
- Validate transactionId for idempotency
- Check available_balance
- Reserve balance within a single transaction

Example:
```text
Before:
  balance = 100
  available = 100
  reserved = 0

After reserve:
  balance = 100
  available = 0
  reserved = 100
```

### 4. Ledger Recording (Synchronous)
- Append debit and credit records
- Ensure idempotency using transactionId
- Ledger remains append-only for auditability

### 5. Payment Processing (Asynchronous)
- Withdraw service calls third-party systems via payment-gateway
- Timeouts and retries are carefully controlled
- Status moves to processing only after a valid third-party response

### 6. Event-Driven Result Handling
- Payment callback publishes events:
  - withdraw.success
  - withdraw.failed
- Kafka buffers traffic and decouples downstream processing

### 7. Final State Handling

#### Withdraw Service
- Updates final status (success / failed)

#### Balance Service
- Success: finalize balance deduction
- Failure: release reserved balance

#### Ledger Service
- Failure only: append reverse ledger entries

### Reconciliation & Failure Recovery
- Internal Failures
  - Detect init records older than X minutes
  - Retry using the same business flow (idempotent by design)
- External Failures
  - Detect processing records older than threshold
  - Query third-party status
  - Escalate to manual review if needed

All workflows eventually converge to:
```text
success or failed
```

## Design Principles
- Correctness over throughput
- Simple state machine
- Idempotency at every boundary
- Synchronous calls for critical correctness
- Asynchronous processing for scalability
- Append-only ledger for auditability


### Notes on Monolithic Design

For smaller systems, a monolithic design combining balance and withdraw logic can be a pragmatic starting point:
- Lower complexity
- Faster iteration
- Easier debugging

This document focuses on a large-scale, production-ready architecture.

