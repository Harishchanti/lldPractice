package org.practice.cache.service;

import org.practice.cache.exception.CacheTypeNoFoundException;

import java.util.HashMap;
import java.util.Map;

public class CacheFactory {
    Map<CacheType, Cache> map = new HashMap<>();

    public CacheFactory() {
        map.put(CacheType.TTL, new TTLCache(2000));
        map.put(CacheType.LRU, new LRUCache(3));
        map.put(CacheType.LFU, new LFUCache(3));
    }

    public Cache getCache(CacheType type) throws CacheTypeNoFoundException {
        if (!map.containsKey(type)) {
            throw new CacheTypeNoFoundException(
                    " Cache Type:" + type + " not found");
        }
        return map.get(type);
    }
}
