package org.practice.snakAndLadder;

import java.util.*;

public class Board {
    int size;
    Queue<Player> playerQueue;
    Map<Integer, Integer> snaks = new HashMap<>();
    Map<Integer, Integer> ladders = new HashMap<>();

    Board() {
        playerQueue = new LinkedList<>();
        this.size = 100;
    }

    public void addPlayers(Player player){
        playerQueue.add(player);
    }

    public void addSankesInBoard(int start, int end) {
        snaks.put(start, end);
    }

    public void addLaddersInBoard(int start, int end) {
        ladders.put(start, end);
    }

    Queue<Player> getPlayerList() {
        return playerQueue;
    }

    public int getFinalPosition(int position) {

        if (snaks.containsKey(position)) {
            return snaks.get(position);
        }
        if (ladders.containsKey(position)) {
            return ladders.get(position);
        }
        return position;
    }
}
