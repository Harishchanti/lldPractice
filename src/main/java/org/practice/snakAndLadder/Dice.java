package org.practice.snakAndLadder;

import java.util.Random;

public class Dice {
    int min;
    int max;
    Random random;

    Dice() {
        min = 1;
        max = 6;
        random = new Random(max);

    }

    int rollDice() {
        int number = random.nextInt(max);
        return number == 0 ? min : number;
    }

}
