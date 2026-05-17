package org.example.cache;

import org.example.cache.exception.CacheTypeNoFoundException;
import org.example.cache.service.Cache;
import org.example.cache.service.CacheFactory;
import org.example.cache.service.CacheType;

public class TestCacheImpl {
    public static void main(String[] args)
            throws InterruptedException, CacheTypeNoFoundException {
        CacheFactory cacheFactory = new CacheFactory();
        Cache<Integer, String> lru =
                cacheFactory.getCache(CacheType.LRU);
        lru.put(1, "A");
        lru.put(2, "B");
        lru.put(3, "C");
        lru.get(1);
        lru.put(4, "D"); // Evicts key 2

        Cache<String, String> ttl =
                cacheFactory.getCache(CacheType.TTL);
        ttl.put("key", "value");

        System.out.println(lru.get(2)); // null
        System.out.println(ttl.get("key")); // value
        Thread.sleep(3000);
        System.out.println(ttl.get("key")); // null
    }
}
