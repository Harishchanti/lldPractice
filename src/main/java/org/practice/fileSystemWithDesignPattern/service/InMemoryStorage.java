package org.practice.fileSystemWithDesignPattern.service;

import org.practice.fileSystemWithDesignPattern.model.FileNode;

public class InMemoryStorage implements StorageStrategy{

    @Override
    public String read(FileNode file) {
        return file.read();
    }

    @Override
    public void write(FileNode file, String data) {
        file.write(data);
    }
}
