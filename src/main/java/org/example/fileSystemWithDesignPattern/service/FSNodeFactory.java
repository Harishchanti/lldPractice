package org.example.fileSystemWithDesignPattern.service;

import org.example.fileSystemWithDesignPattern.model.DirectoryNode;
import org.example.fileSystemWithDesignPattern.model.FSNode;
import org.example.fileSystemWithDesignPattern.model.FileNode;
import org.example.fileSystemWithDesignPattern.model.FileType;

/*
Factory Pattern
 */
public class FSNodeFactory {
    public static FSNode createNode(String name, FileType type) {
        if (type == FileType.FILE) return new FileNode(name);
        return new DirectoryNode(name);
    }
}
