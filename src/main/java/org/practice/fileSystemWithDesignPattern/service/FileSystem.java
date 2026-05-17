package org.practice.fileSystemWithDesignPattern.service;

import org.practice.fileSystemWithDesignPattern.model.DirectoryNode;
import org.practice.fileSystemWithDesignPattern.model.FSNode;
import org.practice.fileSystemWithDesignPattern.model.FileNode;
import org.practice.fileSystemWithDesignPattern.model.FileType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Singleton FileSystem
*/
public class FileSystem {
    private static final FileSystem INSTANCE = new FileSystem();

    private final DirectoryNode root = new DirectoryNode("/");
    private final StorageStrategy storage = new InMemoryStorage();

    private FileSystem() {
    }

    public static FileSystem getInstance() {
        return INSTANCE;
    }

    private String[] tokenize(String path) {
        return path.split("/");
    }

    private FSNode traverse(String path, boolean create) {
        String[] parts = tokenize(path);
        DirectoryNode curr = root;

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (part.isEmpty())
                continue;

            FSNode next = curr.get(part);

            if (next == null && create) {
                next = FSNodeFactory.createNode(part, FileType.DIRECTORY);
                curr.add(next);
            }

            if (next == null)
                throw new RuntimeException("Path not found");

            if (i == parts.length - 1)
                return next;

            if (next.getType() != FileType.DIRECTORY)
                throw new RuntimeException("Invalid path");

            curr = (DirectoryNode) next;
        }
        return curr;
    }

    public void mkdir(String path) {
        traverse(path, true);
    }

    public void createFile(String path) {
        int idx = path.lastIndexOf("/");
        DirectoryNode parent =
                (DirectoryNode) traverse(path.substring(0, idx), true);
        String name = path.substring(idx + 1);
        parent.add(FSNodeFactory.createNode(name, FileType.FILE));
    }

    public void write(String path, String data) {
        FileNode file = (FileNode) traverse(path, false);
        storage.write(file, data);
    }

    public String read(String path) {
        FileNode file = (FileNode) traverse(path, false);
        return storage.read(file);
    }

    public List<String> ls(String path) {
        FSNode node = traverse(path, false);
        if (node.getType() == FileType.FILE)
            return Collections.singletonList(node.getName());

        DirectoryNode dir = (DirectoryNode) node;
        List<String> res = new ArrayList<>();
        for (FSNode child : dir.list())
            res.add(child.getName());
        return res;
    }

    public void delete(String path) {
        int idx = path.lastIndexOf("/");
        DirectoryNode parent =
                (DirectoryNode) traverse(path.substring(0, idx), false);
        parent.remove(path.substring(idx + 1));
    }

}
