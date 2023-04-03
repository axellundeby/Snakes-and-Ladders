package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

public class Playermodel implements ViewableModel{
    Board board;
    Player player;
    DiceState diceState = DiceState.ROLE;

    public Playermodel(Board board, Player player){
        this.board = board;
        this.player = player;
        this.player = player.spawnPlayer(board);
        
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
    public DiceState getDiceState() {
        return diceState;
    }
    @Override
    public int score() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'score'");
    }
}
