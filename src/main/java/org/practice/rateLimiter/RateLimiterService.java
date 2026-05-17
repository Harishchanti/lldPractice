package org.practice.rateLimiter;

public class RateLimiterService {
    private static final RateLimiterService INSTANCE = new RateLimiterService();

    private RateLimiterStrategy strategy;

    private RateLimiterService() {}

    public static RateLimiterService getInstance() {
        return INSTANCE;
    }

    public void setStrategy(RateLimiterStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean allow(String key) {
        if (strategy == null) throw new IllegalStateException("Strategy not set");
        return strategy.allowRequest(key);
    }
}
