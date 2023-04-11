package no.uib.inf101.sem2.model;

import java.util.ArrayList;
import java.util.List;

public class StartPlayer implements PlayerFactory {
    private List<Character> playerSymbols;

    public StartPlayer() {
        this.playerSymbols = new ArrayList<Character>();
        playerSymbols.add('P');
        playerSymbols.add('B');
        playerSymbols.add('Q');
    }

    @Override
    public Player getNext() {
        if (playerSymbols.isEmpty()) {
            return null;
        }

        char playerSymbol = playerSymbols.get(0);
        playerSymbols.remove(0);

        return Player.newPlayer(playerSymbol);
    }
    //er dette ok?
    public boolean hasMorePlayers() {
        return !playerSymbols.isEmpty();
    }
}
