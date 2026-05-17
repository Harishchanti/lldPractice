package org.example.loggingSystem.service;

import org.example.loggingSystem.model.LogMessage;

// ===================== Strategy Pattern (Appender) =====================
public interface Appender {
    void append(LogMessage message);
}
