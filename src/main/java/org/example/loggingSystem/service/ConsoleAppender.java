package org.example.loggingSystem.service;

import org.example.loggingSystem.model.LogMessage;

public class ConsoleAppender implements Appender {
    @Override
    public void append(LogMessage message) {
        System.out.println(message.format());
    }
}