package org.practice.redis;

import redis.clients.jedis.Jedis;

public class RedisPubSub {

    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("localhost", 6379);

        for (int i = 1; i <= 5; i++) {
            String msg = "Breaking News " + i;
            jedis.publish("news", msg);
            System.out.println("Published: " + msg);
            Thread.sleep(1000);
        }

        jedis.close();
    }
}
