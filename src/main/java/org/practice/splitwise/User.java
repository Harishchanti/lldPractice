package org.practice.splitwise;

import java.util.concurrent.locks.ReentrantLock;

public class User {
    String id;
    String name;

    ReentrantLock reentrantLock = new ReentrantLock();

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
