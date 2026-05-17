package org.example.fileSystem.model;

import java.util.HashMap;
import java.util.Map;

public class Inode {
    String name;
    FileType type;
    long size;
    long createdAt;
    long modifiedAt;
    Permission permission;

    // For file
    String content;

    public void setName(String name) {
        this.name = name;
    }

    public void setType(FileType type) {
        this.type = type;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(long modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setChildren(
            Map<String, Inode> children) {
        this.children = children;
    }

    // For directory
    Map<String, Inode> children;

    public Map<String, Inode> getChildren() {
        return children;
    }

    public FileType getType() {
        return type;
    }

    public long getSize() {
        return size;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getModifiedAt() {
        return modifiedAt;
    }

    public Permission getPermission() {
        return permission;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }


    public Inode(String name, FileType type) {
        this.name = name;
        this.type = type;
        this.createdAt = System.currentTimeMillis();
        this.modifiedAt = createdAt;
        this.permission = new Permission();

        if (type == FileType.DIRECTORY) {
            children = new HashMap<>();
        }
    }

}
