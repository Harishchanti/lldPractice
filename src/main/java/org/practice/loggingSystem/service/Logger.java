package org.practice.loggingSystem.service;

import org.practice.loggingSystem.model.LogLevel;
import org.practice.loggingSystem.model.LogMessage;

import java.util.ArrayList;
import java.util.List;

// ===================== Chain of Responsibility =====================
public abstract class Logger {
    protected LogLevel level;
    protected List<Appender> appenders = new ArrayList<>();
    protected Logger next;

    public Logger(LogLevel level) {
        this.level = level;
    }

    public void setNext(Logger next) {
        this.next = next;
    }

    public void addAppender(Appender appender) {
        appenders.add(appender);
    }

    public void log(LogMessage message) {
        if (message.getLevel().ordinal() >= level.ordinal()) {
            for (Appender appender : appenders) {
                appender.append(message);
            }
        }
        if (next != null) next.log(message);
    }
}