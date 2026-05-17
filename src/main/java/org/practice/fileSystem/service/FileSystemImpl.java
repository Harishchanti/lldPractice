package org.practice.fileSystem.service;

import org.practice.fileSystem.model.FileType;
import org.practice.fileSystem.model.Inode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSystemImpl implements FileSystem {

    private final Inode root;

    public FileSystemImpl() {
        root = new Inode("/", FileType.DIRECTORY);
    }

    private String[] tokenize(String path) {
        return path.split("/");
    }

    private Inode traverse(String path, boolean createDirs) {
        String[] parts = tokenize(path);
        Inode curr = root;

        for (String part : parts) {
            if (part.isEmpty())
                continue;

            curr.getChildren().putIfAbsent(part,
                    createDirs ? new Inode(part, FileType.DIRECTORY) : null);

            curr = curr.getChildren().get(part);
            if (curr == null)
                throw new RuntimeException("Path not found");
        }
        return curr;
    }

    @Override
    public void mkdir(String path) {
        traverse(path, true);
    }

    @Override
    public void createFile(String path) {
        int idx = path.lastIndexOf("/");
        String dir = path.substring(0, idx);
        String fileName = path.substring(idx + 1);

        Inode parent = traverse(dir, true);
        parent.getChildren().put(fileName, new Inode(fileName, FileType.FILE));
    }

    @Override
    public void write(String path, String content) {
        Inode file = traverse(path, false);
        if (file.getType() != FileType.FILE)
            throw new RuntimeException("Not a file");

        file.setContent(content);
        file.setSize(content.length());
        file.setModifiedAt(System.currentTimeMillis());
    }

    @Override
    public String read(String path) {
        Inode file = traverse(path, false);
        return file.getContent();
    }

    @Override
    public List<String> ls(String path) {
        Inode node = traverse(path, false);
        if (node.getType() == FileType.FILE) {
            return Collections.singletonList(node.getName());
        }
        return new ArrayList<>(node.getChildren().keySet());
    }

    @Override
    public void delete(String path) {
        int idx = path.lastIndexOf("/");
        String dir = path.substring(0, idx);
        String name = path.substring(idx + 1);

        Inode parent = traverse(dir, false);
        parent.getChildren().remove(name);
    }

    @Override
    public void move(String source, String dest) {
        int sIdx = source.lastIndexOf("/");
        Inode srcParent = traverse(source.substring(0, sIdx), false);
        String name = source.substring(sIdx + 1);

    }
}