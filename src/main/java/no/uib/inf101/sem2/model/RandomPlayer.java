package no.uib.inf101.sem2.model;

import java.util.Random;

public class RandomPlayer implements PlayerFactory {
    private final Random random;

    public RandomPlayer() {
        this.random = new Random();
    }

    @Override
    public Player getNext() {
        final char[] tetrominoSymbols = {'P', 'Q'};
        char randomSymbol = tetrominoSymbols[random.nextInt(tetrominoSymbols.length)];
        return Player.newPlayer(randomSymbol);
    }
    
}
