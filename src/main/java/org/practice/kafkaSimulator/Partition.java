package org.practice.kafkaSimulator;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class Partition {
    private final BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private final AtomicLong offset = new AtomicLong(0);

    public void publish(String value) {
        long currentOffset = offset.getAndIncrement();
        Message msg = new Message(value, currentOffset);
        queue.offer(msg);
    }

    public Message consume() throws InterruptedException {
        return queue.take(); // blocking
    }
}
