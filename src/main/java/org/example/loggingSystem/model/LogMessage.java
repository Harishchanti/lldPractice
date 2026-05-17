package org.example.loggingSystem.model;

public class LogMessage {
    private final long timestamp;
    private final LogLevel level;
    private final String message;

    public LogMessage(LogLevel level, String message) {
        this.timestamp = System.currentTimeMillis();
        this.level = level;
        this.message = message;
    }

    public LogLevel getLevel() { return level; }
    public String format() {
        return "[" + timestamp + "] [" + level + "] " + message;
    }
}
