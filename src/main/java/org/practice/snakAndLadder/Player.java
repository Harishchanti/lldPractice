package org.practice.snakAndLadder;

public class Player {
    String name;
    int currentPosition;

    Player(String name) {
        this.name = name;
        currentPosition = 0;
    }

    void setPosition(int pos) {
        currentPosition = pos;
    }
}
