package org.practice.cache;

import org.practice.cache.exception.CacheTypeNoFoundException;
import org.practice.cache.service.Cache;
import org.practice.cache.service.CacheFactory;
import org.practice.cache.service.CacheType;

public class TestCacheImpl {
    public static void main(String[] args)
            throws InterruptedException, CacheTypeNoFoundException {
        CacheFactory cacheFactory = new CacheFactory();
        Cache<Integer, String> lru = cacheFactory.getCache(CacheType.LRU);
        lru.put(1, "A");
        lru.put(2, "B");
        lru.put(3, "C");
        lru.get(1);
        lru.put(4, "D"); // Evicts key 2

        Cache<String, String> ttl = cacheFactory.getCache(CacheType.TTL);
        ttl.put("key", "value");

        System.out.println(lru.get(2)); // null
        System.out.println(ttl.get("key")); // value
        Thread.sleep(3000);
        System.out.println(ttl.get("key")); // null

        Cache<Integer, String> lfu = cacheFactory.getCache(CacheType.LFU);

        lfu.put(1, "hhhh");
        lfu.put(2, "aaa");
        System.out.println(lfu.get(1));

        lfu.put(3, "ssss");
        System.out.println(lfu.get(1));
        lfu.put(4, "rrrr");
        System.out.println(lfu.get(2));

    }
}
