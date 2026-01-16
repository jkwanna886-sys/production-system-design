# Practical System Design
System design notes and examples based on real-world backend systems,
including both production-grade designs and focused study implementations.

---

### 1. [Redesigned Loan Approval System](./loan-approval-redesign/README.md)

**Background**
- Accepts loan applications, performs review and risk detection, and synchronizes results to downstream systems.
- Original system: legacy, over-split, high complexity.
- Business context: internal approval workflow with predictable users.

**Problems**
- System unavailability
- Frequent bugs, mostly from complexity introduced by over-splitting

---

### 2. [Large-Scale Withdraw System](./withdraw-system/README.md)

**Background**
- Handles withdraw operations for a large-scale financial platform.
- Focused on correctness, scalability, and recoverability.

---

### 3. [Troubleshooting Incident: Slow Static Resource Download](./troubleshooting-breakthrough/README.md)

**Summary**  
- During pre-production testing, static resources were downloading slowly.

**Cause & Fix**  
- Internal network inspection caused the slowdown. Encrypting the resources bypassed inspection and restored normal performance.

**Outcome**
- Issue resolved before production release
- Ensured smooth resource delivery
- Learned to identify network-related performance issues early