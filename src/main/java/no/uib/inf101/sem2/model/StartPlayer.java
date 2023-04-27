package no.uib.inf101.sem2.model;

import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.sem2.grid.CellPosition;

public class StartPlayer implements PlayerFactory {
    private int playerIndex=0;
    private List<Character> playerSymbols = new ArrayList<Character>();

    public StartPlayer() {
        playerSymbols.add('P');
        playerSymbols.add('B');
        playerSymbols.add('Q');
        playerSymbols.add('K');
    }

    /**
        Retrieves the next player in the player order.
        If there are no more players in the order, returns null.
        @return The next player or null if there are no more players.
    */

    public Player getNext() {
        if (playerIndex >= playerSymbols.size()) {
            return null;
        }
        else{
            char playerSymbol = playerSymbols.get(playerIndex);
            Player player = new Player(playerSymbol, new CellPosition(9, 0));
            playerIndex++;
            return player;
    }   
    }

    @Override
    public List<Player> getPlayerList() {
        List<Player> players = new ArrayList<Player>();
        for (int i = 0; i < 4; i++) {
            players.add(getNext());
        }
        return players;
    }
}
