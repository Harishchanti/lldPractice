package org.example.kafkaSimulator;

public class Message {
    private final String value;
    private final long offset;

    public Message(String value, long offset) {
        this.value = value;
        this.offset = offset;
    }

    public String getValue() { return value; }
    public long getOffset() { return offset; }
}
