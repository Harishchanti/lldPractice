package org.example.loggingSystem.service;

import org.example.loggingSystem.model.LogLevel;

public class ErrorLogger extends Logger {
    public ErrorLogger() { super(LogLevel.ERROR); }
}
