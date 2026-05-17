package org.example.fileSystemWithDesignPattern.service;

public class CreateFileCommand implements Command {
    private final FileSystem fs;
    private final String path;

    public CreateFileCommand(FileSystem fs, String path) {
        this.fs = fs;
        this.path = path;
    }

    public void execute() {
        fs.createFile(path);
    }
}