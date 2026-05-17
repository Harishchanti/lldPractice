package org.practice.loggingSystem.service;

import org.practice.loggingSystem.model.LogLevel;

public class ErrorLogger extends Logger {
    public ErrorLogger() { super(LogLevel.ERROR); }
}
