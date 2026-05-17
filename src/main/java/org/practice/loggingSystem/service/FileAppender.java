package org.practice.loggingSystem.service;

import org.practice.loggingSystem.model.LogMessage;

import java.util.ArrayList;
import java.util.List;

public class FileAppender implements Appender {
    private final List<String> file = new ArrayList<>();

    @Override
    public void append(LogMessage message) {
        file.add(message.format());
    }

    public List<String> getLogs() {
        return file;
    }
}
