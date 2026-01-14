## Production Troubleshooting Incident: Slow Static Resource Download

### Background

#### Existing Design Overview
- Static business data is generated on the backend in JSON format.
- When data changes, the backend regenerates the static resources.
- All JSON files are compressed into a single ZIP package.
- The ZIP file size is reduced from ~38MB to ~2MB.
- The app detects version changes and downloads the latest ZIP package.

#### Existing Technical Choices
- ZIP format was selected due to its wide cross-platform compatibility.
- Java `ZipOutputStream` was used to avoid OS-level dependencies.
- The system favors compatibility and operational simplicity over aggressive compression.

---

### Problem Statement

The app team reported that downloading the static resource ZIP file took **15–20 seconds** and frequently timed out.  
This behavior was unacceptable for production usage.

---

### Troubleshooting Strategy

A bottom-up troubleshooting approach was used:

> Client → Network → Protocol → Application

This approach reduces investigation cost and avoids premature assumptions.

---

### Investigation Process

#### 1. Client-Side Isolation
- The same download API was tested using Postman.
- Download time remained **~14 seconds**.

**Conclusion:**  
The issue was not caused by the app client.

---

#### 2. Network Verification
- A similarly sized file was downloaded via SSH (Xshell).
- Download completed in **~2–3 seconds** consistently.

**Conclusion:**  
Network bandwidth and stability were not the bottleneck.

---

#### 3. Protocol-Level Checks
- Reviewed HTTP headers such as `Content-Type` and transfer encoding.
- Compared configurations with other stable download APIs.
- No abnormal configuration was found.

**Conclusion:**  
HTTP protocol configuration was not the root cause.

---

#### 4. Cross-API Comparison
- Identified another API serving a ~1MB static resource.
- Download time was consistently <1 second.
- HTTP headers were nearly identical.

**Conclusion:**  
The issue was not caused by HTTP behavior.

---

#### 5. Application-Level Validation
- Tested downloading other file types (e.g., Excel files ~4MB).
- Download time was ~1–2 seconds.
- Recompressed the same JSON content using Deflate64 via 7-Zip.
- Download time dropped to ~60ms.

**Key Observation:**  
File size remained similar, but download performance improved significantly.

**Conclusion:**  
The bottleneck was content-dependent rather than size-dependent.

---

### Design Decision and Trade-offs

- Java’s standard ZIP libraries do not support Deflate64.
- Invoking OS-level compression tools was evaluated.
- This option was rejected due to:
    - Increased operational complexity
    - Reduced maintainability
    - Higher failure risk

**Decision Principle:**  
Avoid introducing system complexity that exceeds its operational value.

---

### Reframing the Problem

Instead of focusing only on compression levels or file size, the investigation showed that
the slowdown was tied to **how the network treated the content**, not how the server generated it.

Because the ZIP payload contained **large, unencrypted JSON**, the network path likely performed
additional inspection and scanning. This introduced processing latency that grew with payload
complexity, even when file size remained small.

Once the content was encrypted, the download latency dropped sharply.  
This strongly suggested:

> The underlying issue was network inspection of readable data, and **encryption removed that cost**.

---

### Key Takeaways

- Troubleshooting should progress from low-cost layers to high-cost layers.
- Evidence-based isolation prevents incorrect assumptions.
- Technical solutions must be evaluated against operational complexity.
- Business goals should guide technical decisions.

---
ø