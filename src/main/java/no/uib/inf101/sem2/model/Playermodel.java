package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

public class Playermodel implements ViewableModel{
    Board board;
    GameState gameState = GameState.ACTIVE_GAME;

    public Playermodel(Board board){
        this.board = board;
        
    }
    @Override
    public GridDimension getDimension(){
        return this.board;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard(){
        return this.board;
    }


    @Override
    public GameState getGamestate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGamestate'");
    }
    @Override
    public int score() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'score'");
    }

}
