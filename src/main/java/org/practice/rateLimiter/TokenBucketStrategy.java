package org.practice.rateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
// ===================== Token Bucket strategy =====================
 */
public class TokenBucketStrategy implements RateLimiterStrategy{

    private static class Bucket {
        int tokens;
        long lastRefillTime;

        Bucket(int capacity) {
            this.tokens = capacity;
            this.lastRefillTime = System.currentTimeMillis();
        }
    }

    private final int capacity;
    private final int refillRatePerSec;
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public TokenBucketStrategy(int capacity, int refillRatePerSec) {
        this.capacity = capacity;
        this.refillRatePerSec = refillRatePerSec;
    }

    @Override
    public boolean allowRequest(String key) {
        Bucket bucket = buckets.computeIfAbsent(key, k -> new Bucket(capacity));
        synchronized (bucket) {
            refill(bucket);
            if (bucket.tokens > 0) {
                bucket.tokens--;
                return true;
            }
            return false;
        }
    }

    private void refill(Bucket bucket) {
        long now = System.currentTimeMillis();
        long elapsed = now - bucket.lastRefillTime;
        int tokensToAdd = (int) (elapsed / 1000 * refillRatePerSec);

        if (tokensToAdd > 0) {
            bucket.tokens = Math.min(capacity, bucket.tokens + tokensToAdd);
            bucket.lastRefillTime = now;
        }
    }
}
