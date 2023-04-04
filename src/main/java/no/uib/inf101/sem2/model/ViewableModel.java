package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;


public interface ViewableModel{
    
    GridDimension getDimension();
    
    Iterable<GridCell<Character>> getTilesOnBoard();    

    DiceState getDiceState();

    Iterable<GridCell<Character>> getPiece();

    RandomThrow rollDice();

}
