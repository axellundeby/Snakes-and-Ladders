package no.uib.inf101.sem2.model;

import java.util.Arrays;
import java.util.Random;

public class RandomPlayer implements PlayerFactory {
    private final Random random;
    private char[] playerSymbols;

    public RandomPlayer() {
        this.random = new Random();
        this.playerSymbols = new char[]{'P', 'Q'}; 
    }
    //lengden skal av iterasjoen skal være lengden til ant spillere, om vi d er 2 player, itererer vi 1 gang
    @Override
    public Player getNext() {
        if (playerSymbols.length == 0) {
            return null;
            //winner må kalle på her
        }
        int randomIndex = random.nextInt(playerSymbols.length);
        char randomSymbol = playerSymbols[randomIndex];
        
        for (int i = randomIndex; i < playerSymbols.length - 1; i++) {
            playerSymbols[i] = playerSymbols[i + 1];
        }
        playerSymbols = Arrays.copyOf(playerSymbols, playerSymbols.length - 1); 
        
        return Player.newPlayer(randomSymbol);
    }
}
