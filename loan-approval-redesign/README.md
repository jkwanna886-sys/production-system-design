# Redesigned Loan Approval System

---

## 1. Background
- Loan application, review, risk detection, and system syncing
- Internal approval workflow with predictable load
- ~10,000 users total, ~2,000 DAU
- Frequent rule/policy changes
- Priorities: correctness, maintainability, and stability

---

## 2. Problems Observed
- Services split too aggressively, unclear domain ownership
- Latency spikes under moderate load
- Request tracing and debugging difficult
- High defect rate from inter-service dependency chains

---

## 3. Original Service Landscape
- system — user/role/menu/department
- conf — system-level configuration (region, industry, country code)
- bus — business config (products, rates)
- cus — customer data
- gra — loan request (pure loan)
- loan — loan request (with guarantee)
- flow — loan review
- doc — document upload
- edoc — archival
- risk — risk evaluation
- ois — third-party entrypoint
- ois-adaptor — third-party adapter mapping
- job-admin — job scheduler
- statistics — reporting / analytics

---

## 4. Services Consolidated

### ✔ 1) system + conf → **system**
**Reason**
- Same domain
- Low change rate
- No scaling advantage when split

---

### ✔ 2) gra + loan + flow + bus → **loan**
**Reasons**
- One business flow end-to-end
- Splitting created orchestration burden
- bus updates infrequent, mostly read-heavy

**Result**
- Loan submission (pure / guaranteed)
- Review & approval workflow

---

### ✔ 3) ois + ois-adaptor → **integration**
**Reasons**
- Duplicate ownership confusion
- Hard traceability
- No scaling benefit for separation

---

## 5. Services That Stay Split

### 1) cus → **customer**
- Distinct domain
- Supports loan workflow without blocking it
- Scaling and future growth independent

### 2) doc → **document**
- High QPS and bandwidth demand
- User-facing and latency-sensitive

### 3) edoc → **archive**
- Low traffic, async, long-running
- Optimized for retention, not speed

### 4) risk
- Fan-out to rules/models
- Latency-sensitive
- External data dependencies
- High QPS

### 5) job-admin
- Scheduler separation
- Failure isolation from business services

### 6) statistics
- Read-heavy, may spike load
- Failure must not impact core flow

---

## 6. Final Simplified Architecture
- **system** — system components + configuration
- **bus** — business configuration
- **customer** — customer data
- **document** — online document handling
- **archive** — historical loan archive
- **loan** — application, review, approval
- **risk** — loan risk evaluation
- **integration** — third-party calls
- **job-admin** — job scheduling (XXL-job)
- **statistics** — analytical queries

---

## 7. Microservices: Lessons and Misuse

### ✅ Common Trap: “Microservice = Modern”
- Teams split services without justification
- Distributed systems cost more:
    - Debugging
    - Operations
    - Latency and failures

### ✅ When Splitting Makes Sense
❌ Pure business segmentation  
✔ Operational characteristics such as:
- High QPS or bandwidth
- Read/write imbalance
- Need for resource isolation  
  (e.g., Document)

---

## 8. Takeaways
- Favor clarity over fragmentation
- Split only when scaling or failure isolation requires it
- Simplicity > clever architecture
- Most bugs come from **unnecessary complexity**

---