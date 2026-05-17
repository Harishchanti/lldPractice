package org.example.distributedHashing;

import java.util.*;

public class ConsistentHashing<T> {
    private final TreeMap<Integer, T> ring = new TreeMap<>();
    private final int virtualNodes;

    public ConsistentHashing(int virtualNodes) {
        this.virtualNodes = virtualNodes;
    }

    // Hash function
    private int hash(String key) {
        return key.hashCode() & 0x7fffffff; // make positive
    }

    // Add server with virtual nodes
    public void addNode(T node) {
        for (int i = 0; i < virtualNodes; i++) {
            int hash = hash(node.toString() + "-VN" + i);
            ring.put(hash, node);
        }
    }

    // Remove server
    public void removeNode(T node) {
        for (int i = 0; i < virtualNodes; i++) {
            int hash = hash(node.toString() + "-VN" + i);
            ring.remove(hash);
        }
    }

    // Get node for a key
    public T getNode(String key) {
        if (ring.isEmpty())
            return null;

        int hash = hash(key);

        // Find nearest clockwise node
        Map.Entry<Integer, T> entry = ring.ceilingEntry(hash);

        if (entry == null) {
            entry = ring.firstEntry(); // wrap around
        }

        return entry.getValue();
    }

    // Debug: print ring
    public void printRing() {
        for (Map.Entry<Integer, T> entry : ring.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    // Test
    public static void main(String[] args) {
        ConsistentHashing<String> ch = new ConsistentHashing<>(3);

        ch.addNode("ServerA");
        ch.addNode("ServerB");
        ch.addNode("ServerC");

        System.out.println("User1 -> " + ch.getNode("User1"));
        System.out.println("User2 -> " + ch.getNode("User2"));
        System.out.println("User3 -> " + ch.getNode("User3"));

        System.out.println("\nAdding ServerD...\n");
        ch.addNode("ServerD");

        System.out.println("User1 -> " + ch.getNode("User1"));
        System.out.println("User2 -> " + ch.getNode("User2"));
        System.out.println("User3 -> " + ch.getNode("User3"));

        ch.printRing();
    }
}
