package org.example.kafkaSimulator;

import java.util.*;

public class Topic {
    private final List<Partition> partitions;

    public Topic(int partitionCount) {
        partitions = new ArrayList<>();
        for (int i = 0; i < partitionCount; i++) {
            partitions.add(new Partition());
        }
    }

    public Partition getPartition(int index) {
        return partitions.get(index);
    }

    public int getPartitionCount() {
        return partitions.size();
    }
}
