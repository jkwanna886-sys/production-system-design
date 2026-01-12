## 1.Background
- Handles loan application, review, risk detection, and downstream synchronization.
- Internal approval workflow with predictable traffic.
- ~10,000 total users, ~2,000 DAU
- High change frequency (policies and rules)
- Primary concern: correctness and maintainability

## 2.Problems
- Over-split services without clear domain boundaries
- Latency spikes under moderate load
- Difficult request tracing and maintenance
- High bug rate due to complex inter-service communication

# 3.Original Service Layout
- system - maintain system level component, eg department, menu, user,user role,
- conf -system level configuration, eg. country code, region code, industry info etc.

- bus - business configuration, eg. product list, rate info etc.

- cus - customer info.

- doc - document management.
- edoc - loan archive

- gra - grade apply(pure loan, without guarantee)
- loan - loan apply(non-pure loan, with guarantee)
- flow - loan review

- risk - risk detection

- ois - entry to talk to third part
- ois-adaptor - when we call a third-part system, first enter ois, then ois-adapter.

- job-admin - xxl-job service.
- statistics - statistic query support

# 4.services to re-design
## 1).system+conf→system
### why collapse conf(system level configuration)
- Both are system-level, low-change, supporting data
- no independent scaling value.

## 2).gra+loan+flow+bus→loan
### why collapse gra/flow
- All are one business flow
- Splitting creates orchestration pain
- no independent scaling value.
### why collapse bus(business configuration eg.products, rates)
- low update frequency
- read-heavy
- splitting adds overhead without benefit
### Result
- loan apply (pure / guaranteed)
- loan review
- approval workflow

## 3).ois+ois-adaptor→integration
### why collapse
- unclear ownership and trace path (ois vs adaptor).
- duplicate change on third-party updates.
- no independent scaling value.


# 5.Services Remain Split
## 1).cus→customer
### why need split
- separation of concern: supports but does not block core loan flow
- domain separation: customer data is a distinct domain, independent from system or business config
- independent scaling: handles large dataset efficiently

## 1).doc→document
### why need split
- High bandwidth
- High QPS
- User-facing
- Latency sensitive

## 2).edoc→archive
### why need split
- Low QPS
- Backend / async
- Long-running

## 3).risk
### why split
- rule / model fan-out per loan
- synchronous, latency-sensitive
- external data dependencies
- high QPS

## 4).job-admin
### why split
- separation of concern: scheduling vs business logic
- independent scaling and failure isolation

## 5).statistics
### why split
- read-heavy, may stress system
- failure isolation: must not affect core loan flow

# 6.services re-design result(very clean now)
- system - maintain system level component +system level configuration, eg. country code etc.

- bus - business configuration, eg. product list, rate info etc.

- customer - customer info.

- document - online document upload.
- archive - customer loan archive

- loan - loan apply (pure / guaranteed), review, and approval workflow.

- risk - risk detection

- integration - integrate with third-part system.

- job-admin - xxl-job service.
- statistics - statistic query support

## 7. Why Microservices Are Often Misused

### 1) Microservice ≠ Modern
- Often adopted as a trend rather than a necessity
- Many teams do not clearly understand the cost and trade-offs
- Introduces operational, communication, and debugging overhead

### 2) When a Service Should Be Split
- ❌ Splitting purely by business concepts (e.g. business configuration) increases coupling and communication cost
- ✅ Split by operational characteristics:
    - High QPS
    - Read/write intensity
    - Resource isolation needs  
      (e.g. Document Service)

## 8. Lessons Learned
- Engineering favors clarity over abstraction
- Simplicity is the primary goal of system design
- Most production bugs originate from unnecessary complexity