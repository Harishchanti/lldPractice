package org.practice.loggingSystem.service;

import org.practice.loggingSystem.model.LogLevel;
import org.practice.loggingSystem.model.LogMessage;

// ===================== Singleton =====================
public class LogManager {
    private static final LogManager INSTANCE = new LogManager();
    private Logger logger;

    private LogManager() {
        logger = LoggerFactory.createLoggerChain();
    }

    public static LogManager getInstance() {
        return INSTANCE;
    }

    public void log(LogLevel level, String message) {
        logger.log(new LogMessage(level, message));
    }
}
