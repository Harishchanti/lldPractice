package org.practice.rateLimiter;
/*
// ===================== Factory Pattern =====================
 */
public class RateLimiterFactory {
    public static RateLimiterStrategy createTokenBucket(int capacity, int refillRate) {
        return new TokenBucketStrategy(capacity, refillRate);
    }

    public static RateLimiterStrategy createFixedWindow(int limit, long windowMs) {
        return new FixedWindowStrategy(limit, windowMs);
    }
}
