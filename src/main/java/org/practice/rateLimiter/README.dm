How to explain in interview (this is key)

Start like this:

“I’ll design rate limiter using Strategy pattern so that different algorithms like Token Bucket, Fixed Window can be plugged in.”

Then:

“Factory will help create strategies, and I can wrap them using Decorators for logging/metrics.”

Then:

“Each user/key will have its own state stored in a concurrent map.”



==============================================================================

Bring these up proactively:

Distributed rate limiting
Redis + Lua scripts
Consistent hashing
Precision issues
Fixed window → burst problem
Token bucket → smoother
Scaling
Shard by userId
Use LRU/TTL cache for inactive users

==============================================================================

1. Big Picture

A Rate Limiter controls how many requests a user (or key) can make in a given time.

Example:

“User can make 5 requests per second”
If exceeded → reject request ❌
🏗️ 2. High-Level Design

Your design has 3 layers:

1. Client (calls API)
limiter.allow(userId)
2. RateLimiterService (Singleton)
Entry point
Delegates to strategy
3. Strategy (Core logic)
Token Bucket ✅
Fixed Window ✅
(Can add Sliding Window later)
🎯 3. Why Strategy Pattern? (MOST IMPORTANT)

Instead of hardcoding logic:

if (type == TOKEN_BUCKET) { ... }

You use:

interface RateLimiterStrategy {
    boolean allowRequest(String key);
}

👉 This lets you plug different algorithms without changing core system

⚙️ 4. Token Bucket (Smooth Rate Limiting)
Concept:
Each user has a bucket of tokens
Every request consumes 1 token
Tokens refill over time
Example:
Capacity = 5
Refill = 2/sec
Time 0 → 5 tokens
Request → 4 tokens
...
Time passes → tokens refill
Code flow:
Bucket bucket = buckets.computeIfAbsent(key, ...)
Each user gets its own bucket
refill(bucket);
Calculate how many tokens to add
if (bucket.tokens > 0)
Allow request
🔥 Why it's good:
Handles bursts
Smooth traffic
⏱️ 5. Fixed Window (Simple but flawed)
Concept:
Count requests in a time window

Example:

Limit = 5 per 10 sec
0–10 sec → 5 requests allowed
10–20 sec → reset
Code logic:
if (now - window.startTime > windowSize)
    reset window
if (count < limit)
    allow
⚠️ Problem:

Burst issue:

5 req at 9.9 sec
5 req at 10.1 sec
→ 10 requests in ~0.2 sec 😬
🏭 6. Factory Pattern

Instead of:

new TokenBucketStrategy(...)

You use:

RateLimiterFactory.createTokenBucket(...)

👉 Why?

Centralized creation
Easy to extend
🎁 7. Decorator Pattern (Logging)
strategy = new LoggingRateLimiter(strategy);

This wraps original logic:

boolean allowed = delegate.allowRequest(key);
System.out.println(...);

👉 Benefit:

Add logging / metrics without modifying core logic
👑 8. Singleton (Service Layer)
RateLimiterService.getInstance()

Why?

One global limiter
Central control
Easy to manage config
🔄 9. Flow of a Request
Client → RateLimiterService
        → Strategy (TokenBucket)
            → Bucket lookup (per user)
            → Refill tokens
            → Allow / Reject
⚡ 10. Concurrency Handling

You handled this well:

ConcurrentHashMap → thread-safe storage
synchronized(bucket) → per-user locking

👉 Important:

Avoid global lock (bad performance)
Lock only per user (good scalability)
🚀 11. How to Impress Interviewer

After explaining, say this:

Improvements:
Sliding Window Log (more accurate)
Redis-based distributed limiter
Add TTL eviction for inactive users
Add LRU cache for memory control
🧩 12. Real-World Mapping
System	Algorithm Used
API Gateway	Token Bucket
Nginx	Leaky Bucket
Redis Limiter	Sliding Window