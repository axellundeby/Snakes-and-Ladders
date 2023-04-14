package no.uib.inf101.sem2.model;

import java.util.List;

public interface PlayerFactory {
    Player getNext();
    boolean hasMorePlayers();

    List<Player> getPlayerList();
}
