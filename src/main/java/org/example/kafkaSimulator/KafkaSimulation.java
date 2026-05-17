package org.example.kafkaSimulator;
/*
 Desing Kafka like
 - Multiple Producers producing to Topic
 - Multiple Consumer consumer from Topic
 - In P1 : M1, M2 , M3 ...
      P2 : M1, M2 ...
      P3 : M1 ..
- Kafka consumer process the messages from Partition sequencialy,


Partitioon count : 3
3 thread producing the messages to partition.
3 thread consuming partitions maintain a sequence while consuemr the message.


 */
public class KafkaSimulation {
    public static void main(String[] args) {

        Topic topic = new Topic(3);

        // Producers
        for (int i = 0; i < 3; i++) {
            new Thread(new Producer(topic, "Producer-" + i)).start();
        }

        // Consumers (1 per partition)
        for (int i = 0; i < 3; i++) {
            Partition partition = topic.getPartition(i);
            new Thread(new Consumer(partition, "Consumer-" + i, i)).start();
        }
    }
}
