package org.practice.loggingSystem.service;

import org.practice.loggingSystem.model.LogMessage;

public class ConsoleAppender implements Appender {
    @Override
    public void append(LogMessage message) {
        System.out.println(message.format());
    }
}