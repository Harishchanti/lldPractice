package org.practice.loggingSystem.service;

import org.practice.loggingSystem.model.LogMessage;

// ===================== Strategy Pattern (Appender) =====================
public interface Appender {
    void append(LogMessage message);
}
