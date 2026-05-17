package org.example.fileSystemWithDesignPattern.model;

/*

Core Abstraction (Composite design pattern) structural  design pattern
 */
public interface FSNode {
    String getName();
    FileType getType();
    long getSize();
}
