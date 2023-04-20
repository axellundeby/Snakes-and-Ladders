package no.uib.inf101.sem2.model;

import java.util.List;

public interface PlayerFactory {
    
    /**
    Retrieves the next player from the list of players in the game.
    @return the next player in the list, or null if there are no more players.
    */
    Player getNext();


    /**
    Checks if there are any more players remaining to play the game.
    @return true if there are more players, false otherwise.
    */
    boolean hasMorePlayers();

    /**
    Retrieves a list of players from the current game.
    @return a list of  players.
    */
    List<Player> getPlayerList();
}
