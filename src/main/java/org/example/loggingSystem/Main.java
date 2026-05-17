package org.example.loggingSystem;

import org.example.loggingSystem.model.LogLevel;
import org.example.loggingSystem.service.LogManager;

public class Main {
    public static void main(String[] args) {
        LogManager logManager = LogManager.getInstance();

        logManager.log(LogLevel.INFO, "Application started");
        logManager.log(LogLevel.DEBUG, "Debugging details");
        logManager.log(LogLevel.ERROR, "Something failed");
    }
}
