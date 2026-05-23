package org.practice.redis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class SubScriber {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        JedisPubSub pubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("Received from " + channel + ": " + message);
            }
        };

        System.out.println("Subscribed to channel 'news'");
        jedis.subscribe(pubSub, "news");  // blocks and listens
    }
}
