package org.practice.fileSystemWithDesignPattern.model;

/*

Core Abstraction (Composite design pattern) structural  design pattern
 */
public class FileNode implements FSNode {
    private final String name;
    private String content = "";

    public FileNode(String name) { this.name = name; }

    public void write(String data) { this.content = data; }
    public String read() { return content; }

    @Override public String getName() { return name; }
    @Override public FileType getType() { return FileType.FILE; }
    @Override public long getSize() { return content.length(); }
}