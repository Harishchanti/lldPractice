package org.example.fileSystem.service;

import java.util.List;

public interface FileSystem {
    void mkdir(String path);

    void createFile(String path);

    void write(String path, String content);

    String read(String path);

    List<String> ls(String path);

    void delete(String path);

    void move(String source, String dest);
}
