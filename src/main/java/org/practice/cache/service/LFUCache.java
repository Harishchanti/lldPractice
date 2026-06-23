package org.practice.cache.service;

import org.practice.cache.exception.ItemNotFoundException;

import java.util.*;

public class LFUCache<K, V> implements Cache<K, V> {

    class Pair<F, S> {
        F first;
        S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    private Map<K, Node<K, V>> cacheMap;

    // Maps frequency to doubly linked list
    //(head, tail) of Nodes with that frequency
    private Map<Integer, Pair<Node<K, V>, Node<K, V>>> freqMap;

    // Tracks the minimum frequency
    private int minFreq;

    // Capacity of the LFU cache
    private final int capacity;

    // Constructor to initialize LFUCache with a given capacity
    LFUCache(int capacity) {
        this.capacity = capacity;

        // Initial minimum frequency is 0
        minFreq = 0;
        cacheMap = new HashMap<>();
        freqMap = new HashMap<>();
    }

    @Override
    public void put(K key, V value) {
        // Do nothing if the cache has zero capacity
        if (capacity == 0) {
            return;
        }

        // Update value if key already exists in the cache
        if (cacheMap.containsKey(key)) {
            Node node = cacheMap.get(key);
            node.value = value;
            updateFreq(node);
        }

        // Add a new key-value pair to the cache
        else {

            // Remove the least frequently used node if cache is full
            if (cacheMap.size() == capacity) {
                Node node = freqMap.get(minFreq).second.prev;
                cacheMap.remove(node.key);
                remove(node);

                // Remove the frequency list if it's empty
                if (freqMap.get(minFreq).first.next == freqMap.get(
                        minFreq).second) {
                    freqMap.remove(minFreq);
                }
            }

            // Create a new node for the key-value pair
            Node node = new Node(key, value);
            cacheMap.put(key, node);

            // Reset minimum frequency to 1
            minFreq = 1;
            add(node, 1);
        }
    }

    // Add a node right after the head
    void add(Node node, int freq) {

        // Initialize the frequency list if it doesn't exist
        if (!freqMap.containsKey(freq)) {

            // Dummy head node
            Node head = new Node(-1, -1);

            // Dummy tail node
            Node tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
            freqMap.put(freq, new Pair<>(head, tail));
        }

        // Insert the node right after the head
        Node head = freqMap.get(freq).first;
        Node temp = head.next;
        node.next = temp;
        node.prev = head;
        head.next = node;
        temp.prev = node;
    }

    // Remove a node from the list
    void remove(Node node) {

        // Update pointers to exclude the node
        Node delprev = node.prev;
        Node delnext = node.next;
        delprev.next = delnext;
        delnext.prev = delprev;
    }

    // Update the frequency of a node
    void updateFreq(Node node) {

        // Get the current frequency
        int oldFreq = node.cnt;

        // Increment the frequency
        node.cnt++;

        // Remove the node from the current frequency list
        remove(node);
        if (freqMap.get(oldFreq).first.next == freqMap.get(oldFreq).second) {
            freqMap.remove(oldFreq);

            // Update minimum frequency if needed
            if (minFreq == oldFreq) {
                minFreq++;
            }
        }

        // Add the node to the updated frequency list
        add(node, node.cnt);
    }

    @Override
    public V get(K key) {
        // Return -1 if key is not found in the cache
        if (!cacheMap.containsKey(key)) {
            return null;
        }

        // Retrieve the Node and update its frequency
        Node node = cacheMap.get(key);
        V res = (V) node.value;
        updateFreq(node);
        return res;
    }

    @Override
    public void remove(K key) throws ItemNotFoundException {

    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }

}

class Node<K, V> {
    K key;
    V value;
    int cnt;
    Node next;
    Node prev;

    Node(K key, V val) {
        this.key = key;
        this.value = val;

        // Initial frequency is 1
        cnt = 1;
    }
}
