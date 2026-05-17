package org.practice.fileSystemWithDesignPattern.service;

import org.practice.fileSystemWithDesignPattern.model.DirectoryNode;
import org.practice.fileSystemWithDesignPattern.model.FSNode;
import org.practice.fileSystemWithDesignPattern.model.FileNode;
import org.practice.fileSystemWithDesignPattern.model.FileType;

/*
Factory Pattern
 */
public class FSNodeFactory {
    public static FSNode createNode(String name, FileType type) {
        if (type == FileType.FILE) return new FileNode(name);
        return new DirectoryNode(name);
    }
}
