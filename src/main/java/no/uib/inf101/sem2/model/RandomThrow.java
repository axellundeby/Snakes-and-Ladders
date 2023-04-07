package no.uib.inf101.sem2.model;

import java.util.Random;

public class RandomThrow implements nextThrow {
    private final Random random;
    DiceState diceState = DiceState.ROLE;
    Player player;

    public RandomThrow() {
        this.random = new Random();
    }

    @Override
    public int rollDice() {
        int eyes = random.nextInt(6) + 1;
        return eyes;
    }
}
    

