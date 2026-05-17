package org.practice.fileSystemWithDesignPattern.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/*

File & Directory (Composite Implementation) structural  design pattern
 */
public class DirectoryNode implements FSNode {
    private final String name;
    private final Map<String, FSNode> children = new HashMap<>();

    public DirectoryNode(String name) { this.name = name; }

    public void add(FSNode node) { children.put(node.getName(), node); }
    public FSNode get(String name) { return children.get(name); }
    public void remove(String name) { children.remove(name); }
    public Collection<FSNode> list() { return children.values(); }

    @Override public String getName() { return name; }
    @Override public FileType getType() { return FileType.DIRECTORY; }
    @Override public long getSize() {
        return children.values().stream().mapToLong(FSNode::getSize).sum();
    }
}
