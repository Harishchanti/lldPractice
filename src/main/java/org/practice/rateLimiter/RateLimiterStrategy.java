package org.practice.rateLimiter;
/*

// ===================== Strategy Pattern =====================
 */
public interface RateLimiterStrategy {
    boolean allowRequest(String key);
}
