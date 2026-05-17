package org.example.loggingSystem.service;

import org.example.loggingSystem.model.LogLevel;

public class DebugLogger extends Logger {
    public DebugLogger() { super(LogLevel.DEBUG); }
}