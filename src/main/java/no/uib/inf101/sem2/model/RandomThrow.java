package no.uib.inf101.sem2.model;

import java.util.Random;

public class RandomThrow implements nextThrow {
    private final Random random;
    DiceState diceState = DiceState.ROLE;

    public RandomThrow() {
        this.random = new Random();
    }

    @Override
    public int rollDice() {
        return random.nextInt(6) + 1;
    }

    public void nextThrow(int eyes){
        if (eyes == 1){
            diceState = DiceState.ONE;
        }
        else if (eyes == 2){
            diceState = DiceState.TWO;
        }
        else if (eyes == 3){
            diceState = DiceState.THREE;
        }
        else if (eyes == 4){
            diceState = DiceState.FOUR;
        }
        else if (eyes == 5){
            diceState = DiceState.FIVE;
        }
        else if (eyes == 6){
            diceState = DiceState.SIX;
        }
    }
}
    

