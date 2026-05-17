package org.practice.kafkaSimulator;

import java.util.concurrent.atomic.AtomicInteger;

class Producer implements Runnable {
    private final Topic topic;
    private static final AtomicInteger counter = new AtomicInteger(0);
    private final String name;

    public Producer(Topic topic, String name) {
        this.topic = topic;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            int partitionIndex = counter.getAndIncrement() % topic.getPartitionCount();
            Partition partition = topic.getPartition(partitionIndex);

            String message = name + "-msg-" + System.nanoTime();
            partition.publish(message);

            System.out.println(name + " produced to P" + partitionIndex + ": " + message);

            sleep();
        }
    }

    private void sleep() {
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
    }
}