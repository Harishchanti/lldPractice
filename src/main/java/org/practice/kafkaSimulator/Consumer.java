package org.practice.kafkaSimulator;

class Consumer implements Runnable {
    private final Partition partition;
    private final String name;
    private final int partitionId;

    public Consumer(Partition partition, String name, int partitionId) {
        this.partition = partition;
        this.name = name;
        this.partitionId = partitionId;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message msg = partition.consume();

                System.out.println(
                        name + " consumed from P" + partitionId +
                                " | offset=" + msg.getOffset() +
                                " | value=" + msg.getValue()
                );

                process(msg);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void process(Message msg) throws InterruptedException {
        Thread.sleep(1000); // simulate work
    }
}
