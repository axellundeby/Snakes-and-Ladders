package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;


public interface ViewableModel{
    
    /**
    @return a grid Dimension object
    */
    GridDimension getDimension();
    

    /**

    Returns an iterable collection of all the grid cells on the board,
    where each cell contains a character value.
    @return an iterable collection of grid cells containing character values
    */
    Iterable<GridCell<Character>> getTilesOnBoard();    

   
    /**
     * this method returns a score 
     * @return a score 
     */
    int score();

    DiceState getDiceState();
}
