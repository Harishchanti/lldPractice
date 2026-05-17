package org.practice.loggingSystem.service;

import org.practice.loggingSystem.model.LogLevel;

public class DebugLogger extends Logger {
    public DebugLogger() { super(LogLevel.DEBUG); }
}