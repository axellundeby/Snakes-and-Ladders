package no.uib.inf101.sem2.model;

import java.util.List;

public interface PlayerFactory {
    

    /**
    Retrieves a list of players from the current game.
    @return a list of  players.
    */
    List<Player> getPlayerList();
}
