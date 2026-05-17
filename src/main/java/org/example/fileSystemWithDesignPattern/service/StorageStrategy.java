package org.example.fileSystemWithDesignPattern.service;

import org.example.fileSystemWithDesignPattern.model.FileNode;

public interface StorageStrategy {
    void write(FileNode file, String data);
    String read(FileNode file);
}
