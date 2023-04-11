package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;


public interface ViewableModel{

    /**
     * @return The dimension of the game board
     */
    
    GridDimension getDimension();

    /**
    Returns an Iterable of GridCell objects representing all the tiles on the game board.
    @return An Iterable of GridCell objects representing all the tiles on the game board.
    */
    
    Iterable<GridCell<Character>> getTilesOnBoard();   
    
    /**
    Returns an Iterable of GridCell objects that represent the cells occupied by a piece on the game board.
    @return An Iterable of GridCell objects representing the cells occupied by a piece.
    */

    Iterable<GridCell<Character>> getPiece();
    
    /**
     * Returns the current state of the dice
     */
    DiceState getDiceState();

    /**returns the current gamestate */
    GameState getGamestate();

    /**returns the current gameinfo */
    GameInfo getGameInfo();
}
