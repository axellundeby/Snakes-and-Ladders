package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

public class Playermodel implements ViewableModel{
    Board board;
    Player player;
    PlayerFactory factory;
    RandomThrow diceState = new RandomThrow();

    public Playermodel(Board board, PlayerFactory factory, Player player){
        this.board = board;
        this.factory = factory;
        this.player = factory.getNext();
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
    public Iterable<GridCell<Character>> getPiece() {
       return player;
    }

    @Override
    public DiceState getDiceState() {
        return diceState.getDiceState();
    }
    @Override
    public RandomThrow rollDice() {
        diceState.rollDice();
        return diceState;
    }

//her er det noe rør siden jeg ikke får hentet ut øyne  
    public void movePlayer(int eyes, CellPosition pos) {
        int PlayerRow = pos.row();
        if(PlayerRow % 2 == 0){
            movePlayerRight(eyes);
        }
        else{
            movePlayerLeft(eyes);
        }
        PlayerOnEdge(player, pos);
        Snake(pos);
        Ladder(pos);
    }

    private void PlayerOnEdge(Player player, CellPosition pos){    
            int PlayerCol = pos.col();
            if(PlayerCol == 0 || PlayerCol == 9){
            player.shiftedBy(+1,0);
        }
    }
    private void movePlayerRight(int eyes) {
        for(int i = 0; i < eyes; i++){
            player.shiftedBy(0, +1);
        }
    }

    private void movePlayerLeft(int eyes) {
        for(int i = 0; i < eyes; i++){
            player.shiftedBy(0, -1);
        }
    }

    private void Snake(CellPosition pos){
        if(pos.row() == 9 && pos.col() == 1){
            player.shiftedBy(4, 0);
        }
        else if (pos.row() == 8 && pos.col() == 8){
            player.shiftedBy(5, 7);
        }
        else if (pos.row() == 7 && pos.col() == 4){
            player.shiftedBy(5, 2);
        }
        else if (pos.row() == 6 && pos.col() == 5){
            player.shiftedBy(4, 4);
        }
        else if (pos.row() == 5 && pos.col() == 6){
            player.shiftedBy(3, 9);
        }
        else if (pos.row() == 4 && pos.col() == 2){
            player.shiftedBy(1, 2);
        }
        else if (pos.row() == 3 && pos.col() == 0){
            player.shiftedBy(0, 2);
        }
        else if (pos.row() == 2 && pos.col() == 6){
            player.shiftedBy(0, 4);
        }
    }

    private void Ladder(CellPosition pos){
        if(pos.row() == 0 && pos.col() == 3){
            player.shiftedBy(2, 4);
        }
        else if(pos.row() == 1 && pos.col() == 7){
            player.shiftedBy(4, 5);
        }
        else if(pos.row() == 3 && pos.col() == 7){
            player.shiftedBy(4, 8);
        }
        else if(pos.row() == 4 && pos.col() == 9){
            player.shiftedBy(6, 8);
        }
        else if(pos.row() == 4 && pos.col() == 1){
            player.shiftedBy(6, 5);
        }
        else if(pos.row() == 6 && pos.col() == 1){
            player.shiftedBy(8, 0);
        }
        else if(pos.row() == 7 && pos.col() == 6){
            player.shiftedBy(9, 8);
        }
    }
}
