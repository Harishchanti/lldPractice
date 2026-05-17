package org.practice.fileSystemWithDesignPattern.service;

import org.practice.fileSystemWithDesignPattern.model.FileNode;

public interface StorageStrategy {
    void write(FileNode file, String data);
    String read(FileNode file);
}
