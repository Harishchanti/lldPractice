package org.example.rateLimiter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimiterStrategy strategy = RateLimiterFactory
                .createTokenBucket(5, 2);

        strategy = new LoggingRateLimiter(strategy);

        RateLimiterService limiter = RateLimiterService.getInstance();
        limiter.setStrategy(strategy);

        String user = "user1";

        for (int i = 0; i < 10; i++) {
            System.out.println(limiter.allow(user));
            Thread.sleep(300);
        }
    }

}
