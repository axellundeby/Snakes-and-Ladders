package no.uib.inf101.sem2.model;

public interface PlayerFactory {
    Player getNext();
    boolean hasMorePlayers();
}
