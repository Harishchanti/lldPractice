package org.example.cache.service;

import org.example.cache.exception.ItemNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> implements Cache<K, V> {
    int capacity;
    Map<K, CacheNode<K, V>> map;
    CacheNode<K, V> head, end;

    LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    @Override
    public void put(K key, V value) {
        CacheNode<K, V> cacheNode = map.get(key);

        if (cacheNode != null) {
            cacheNode.value = value;
            removeNode(cacheNode);
            setHead(cacheNode);
        } else {
            CacheNode<K, V> newNode = new CacheNode<>(key, value);
            if (getSize() > capacity) {
                removeNode(end);
                map.remove(end.key);
            }
            setHead(newNode);
        }
        map.put(key, cacheNode);
    }

    private void removeNode(CacheNode<K, V> cacheNode) {
        if (cacheNode.next != null) {
            cacheNode.next.prev = cacheNode.prev;
        } else {
            end = cacheNode.prev;
        }

        if (cacheNode.prev != null) {
            cacheNode.prev.next = cacheNode.next;
        } else {
            head = cacheNode.next;
        }
    }

    private void setHead(CacheNode<K, V> cacheNode) {
        cacheNode.next = head;
        cacheNode.prev = null;

        if (head != null) {
            head.prev = cacheNode;
        }
        head = cacheNode;

        if (end == null) {
            end = cacheNode;
        }
    }

    @Override
    public V get(K key) {
        CacheNode<K, V> cacheNode = map.get(key);
        if (cacheNode != null) {
            removeNode(cacheNode);
            setHead(cacheNode);
            return cacheNode.value;
        }
        return null;
    }

    @Override
    public void remove(K key) throws ItemNotFoundException {
        if (map.containsKey(key)) {
            removeNode(map.get(key));
            map.remove(key);
        } else {
            throw new ItemNotFoundException("Map key:" + key + " not persent");
        }
    }

    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    @Override
    public int getSize() {
        return map.size();
    }

    public CacheType getCacheType() {
        return CacheType.LRU;
    }
}

class CacheNode<K, V> {
    K key;
    V value;
    CacheNode prev;
    CacheNode next;

    CacheNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
