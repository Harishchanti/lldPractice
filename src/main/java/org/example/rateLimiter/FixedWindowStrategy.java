package org.example.rateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/*
// ===================== Fixed Window strategy =====================
 */
public class FixedWindowStrategy implements RateLimiterStrategy {

    private static class Window {
        int count;
        long startTime;

        Window() {
            this.startTime = System.currentTimeMillis();
        }
    }

    private final int limit;
    private final long windowSizeMs;
    private final Map<String, Window> windows = new ConcurrentHashMap<>();

    public FixedWindowStrategy(int limit, long windowSizeMs) {
        this.limit = limit;
        this.windowSizeMs = windowSizeMs;
    }

    @Override
    public boolean allowRequest(String key) {
        Window window = windows.computeIfAbsent(key, k -> new Window());
        synchronized (window) {
            long now = System.currentTimeMillis();

            if (now - window.startTime > windowSizeMs) {
                window.startTime = now;
                window.count = 0;
            }

            if (window.count < limit) {
                window.count++;
                return true;
            }
            return false;
        }
    }
}
