package no.uib.inf101.sem2.model;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

public class Playermodel implements ViewableModel{
    Board board;
    Player player;
    PlayerFactory factory;
    private DiceState diceState = DiceState.ROLE;
    

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
        return diceState;
    }
   
    public void updateDiceNumber(int eyes) {
        if (eyes == 1){
            diceState = DiceState.ONE;
        }
        else if (eyes == 2){
            diceState = DiceState.TWO;
        }
        else if (eyes == 3){
            diceState = DiceState.THREE;
        }
        else if (eyes == 4){
            diceState = DiceState.FOUR;
        }
        else if (eyes == 5){
            diceState = DiceState.FIVE;
        }
        else if (eyes == 6){
            diceState = DiceState.SIX;
        }
    }

    public void movePlayer(int eyes) {
        int PlayerRow = player.getPos().row();
        int PlayerCol = player.getPos().col();
        for (int i = 0; i <= eyes; i++) {
            if(PlayerRow % 2 == 0){
                if (PlayerCol == 9){
                    PlayerOnEdge(eyes);
                    }
                else{
                    movePlayerRight(eyes);
                }
                }
            else{
                if (PlayerCol == 0){
                    PlayerOnEdge(eyes);
                    }
                else{
                    movePlayerLeft(eyes);
                }
            }
        }
        Snake();
        Ladder();
        lastTile();
    }

    private void movePlayerRight(int eyes) {
            player.shiftedBy(0, +1);
    }
    
    private void movePlayerLeft(int eyes) {
            player.shiftedBy(0, -1);
    }
    
    private void PlayerOnEdge(int eyes){       
            player.shiftedBy(+1,0);
            eyes--;
    }
    
    private void Snake() {
        int row = player.getPos().row();
        int col = player.getPos().col();
        
        if (row == 9 && col == 1) {
            player.shiftedBy(4, 0);
        } else if (row == 8 && col == 8) {
            player.shiftedBy(5, 7);
        } else if (row == 7 && col == 4) {
            player.shiftedBy(5, 2);
        } else if (row == 6 && col == 5) {
            player.shiftedBy(4, 4);
        } else if (row == 5 && col == 6) {
            player.shiftedBy(3, 9);
        } else if (row == 4 && col == 2) {
            player.shiftedBy(1, 2);
        } else if (row == 3 && col == 0) {
            player.shiftedBy(0, 2);
        } else if (row == 2 && col == 6) {
            player.shiftedBy(0, 4);
        }
     }

     public void Ladder() {
        int row = player.getPos().row();
        int col = player.getPos().col();
    
        if (row == 0 && col == 3) {
            player.shiftedBy(2, 4);
        } else if (row == 1 && col == 7) {
            player.shiftedBy(4, 5);
        } else if (row == 3 && col == 7) {
            player.shiftedBy(4, 8);
        } else if (row == 4 && col == 9) {
            player.shiftedBy(6, 8);
        } else if (row == 4 && col == 1) {
            player.shiftedBy(6, 5);
        } else if (row == 6 && col == 1) {
            player.shiftedBy(8, 0);
        } else if (row == 7 && col == 6) {
            player.shiftedBy(9, 8);
        }
    }

    public void lastTile() {
        if (player.getPos().row() == 9 && player.getPos().col() == 9){
            nextPlayer();
            //brikken må også fjernes, altså må plassen bli en "-"
            //count terningtrykk, lagre i liste, 
            //winner(gangerRullet), winner burde bli kalt på i RadnomPlayer klassen
        }
    }
    //ny spiller når player1 har kommet i mål
    public void nextPlayer(){
            this.player = factory.getNext();
            this.player = player.spawnPlayer(board);
    }
    public void winner(){
        //sjekker minste tallet, spiller med minst tall vinner
    }
}
