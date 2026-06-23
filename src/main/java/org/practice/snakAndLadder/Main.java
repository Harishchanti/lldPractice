package org.practice.snakAndLadder;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        Queue<Player> players = board.getPlayerList();
        board.addPlayers(new Player("A"));
        board.addPlayers(new Player("B"));
        board.addPlayers(new Player("C"));

        board.addLaddersInBoard(4, 20);
        board.addLaddersInBoard(12, 34);
        board.addLaddersInBoard(23, 45);
        board.addLaddersInBoard(44, 80);

        board.addSankesInBoard(80, 30);
        board.addSankesInBoard(40, 23);
        board.addSankesInBoard(98, 11);
        board.addSankesInBoard(24, 7);

        Dice dice = new Dice();

        while (!players.isEmpty()) {

            Player player = players.poll();
            int position = dice.rollDice();

            position = board.getFinalPosition(position);
            player.currentPosition =
                    ((player.currentPosition + position) > board.size) ?
                            position : (player.currentPosition + position);

            if (player.currentPosition == board.size) {
                System.out.println(
                        player.name + " is winner " + player.currentPosition);
                break;
            }
            System.out.println(
                    " Position of " + player.name + " " + player.currentPosition);

            players.add(player);
        }
        System.out.println("Final positions of all the players");

        while (!players.isEmpty()) {
            Player player = players.poll();
            System.out.println(player.name + " :  " + player.currentPosition);
        }
    }

}
