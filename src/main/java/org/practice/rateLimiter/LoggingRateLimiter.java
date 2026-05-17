package org.practice.rateLimiter;

/*
// ===================== Decorator Pattern (Logging) =====================
 */
public class LoggingRateLimiter implements RateLimiterStrategy {
    private final RateLimiterStrategy delegate;

    public LoggingRateLimiter(RateLimiterStrategy delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean allowRequest(String key) {
        boolean allowed = delegate.allowRequest(key);
        System.out.println("Request for " + key + " allowed: " + allowed);
        return allowed;
    }
}

