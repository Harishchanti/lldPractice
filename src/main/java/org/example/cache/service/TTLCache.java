package org.example.cache.service;

import org.example.cache.exception.ItemNotFoundException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TTLCache<K, V> implements Cache<K, V> {
    long defaultExpiryttl;
    Map<K, EntryNode<V>> map;

    TTLCache(long expiryTime) {
        defaultExpiryttl = expiryTime;
        map = new ConcurrentHashMap<>();
        startCleaner();
    }

    private void startCleaner() {
        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            for (K key : map.keySet()) {
                EntryNode<V> entry = map.get(key);
                if (entry != null && entry.isExpired()) {
                    map.remove(key);
                }
            }
        }, defaultExpiryttl, defaultExpiryttl, TimeUnit.MILLISECONDS);
    }

    @Override
    public void put(K key, V value) {
        map.put(key, new EntryNode<>(value, defaultExpiryttl));
    }

    public void put(K key, V value, long ttl) {
        map.put(key, new EntryNode<>(value, ttl));
    }

    @Override
    public V get(K key) {
        EntryNode<V> entryNode = map.get(key);
        if (entryNode != null && entryNode.isExpired()) {
            map.remove(key);
            return null;
        }

        // Update the expiry time on access if needed, depending on the desired behavior (e.g., reset expiry on access)
        return entryNode != null && !entryNode.isExpired() ? entryNode.value :
                null;
    }

    @Override
    public void remove(K key) throws ItemNotFoundException {
        if (map.containsKey(key)) {
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
        return CacheType.TTL;
    }
}

class EntryNode<V> {
    V value;
    long expiryTime;

    public void updateExpiryTime(long expiryTime) {
        this.expiryTime = System.currentTimeMillis() + expiryTime;
    }

    EntryNode(V value, long expiryTime) {
        this.value = value;
        this.expiryTime = System.currentTimeMillis() + expiryTime;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}
