package no.uib.inf101.sem2.model;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

public class Playermodel implements ViewableModel{
    Board board;
    Player CurrentPlayer;
    PlayerFactory factory;
    private DiceState diceState = DiceState.ROLE;
    private GameState gameState = GameState.GameActive;
    

    public Playermodel(Board board, PlayerFactory factory, Player player){
        this.board = board;
        this.factory = factory;
        this.CurrentPlayer = factory.getNext();
        this.CurrentPlayer = player.spawnPlayer(board);
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
       return CurrentPlayer;
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

    private void movePlayer(int deltaRow, int deltaCol) {
        Player ShapeCopy = CurrentPlayer.shiftedBy(deltaRow, deltaCol);
        this.CurrentPlayer = ShapeCopy;
    
    }
    private void movePlayerTo(int row, int col) {
        CurrentPlayer.setPos(new CellPosition(row, col));
    }

    public void PlayerJump() {
        int PlayerRow = CurrentPlayer.getPos().row();
        int PlayerCol = CurrentPlayer.getPos().col();
        if(PlayerRow % 2 != 0){
            if (PlayerCol == 9){
                PlayerOnEdge();
            }
            else{
                movePlayerRight();
            }
        }
        else{
            if (PlayerCol == 0){
                PlayerOnEdge();
            }
            else if(PlayerRow < 0 && PlayerCol < 0){
                Winner();
            }
            else{
                movePlayerLeft();
            }
        }
    }

    private void movePlayerRight() {
        movePlayer(0, +1);
    }
    
    private void movePlayerLeft() {
        movePlayer(0, -1);
    }
    
    private void PlayerOnEdge(){       
        movePlayer(-1,0);
    }
    //må sjekke om en spiller er på et felt, når landet
    public void SteppedOnSnake() {
        int row = CurrentPlayer.getPos().row();
        int col = CurrentPlayer.getPos().col();
        
        if (row == 9 && col == 1) {
            movePlayerTo(4, 0);//
        } else if (row == 8 && col == 8) {
            movePlayerTo(5, 7);//
        } else if (row == 7 && col == 4) {
            movePlayerTo(5, 2);
        } else if (row == 6 && col == 5) {
            movePlayerTo(4, 4);//
        } else if (row == 5 && col == 6) {
             movePlayerTo(3, 9);//
        } else if (row == 4 && col == 2) {
            movePlayerTo(1, 2);//
        } else if (row == 3 && col == 0) {
            movePlayerTo(0, 2);//
        } else if (row == 2 && col == 6) {
            movePlayerTo(0, 4);//
        }
     }

     public void SteppedOnLadder() {
        int row = CurrentPlayer.getPos().row();
        int col = CurrentPlayer.getPos().col();
    
        if (row == 0 && col == 3) {
            movePlayerTo(2, 4);
        } else if (row == 1 && col == 7) {
            movePlayerTo(4, 5);
        } else if (row == 3 && col == 7) {
            movePlayerTo(4, 8);
        } else if (row == 4 && col == 1) {
            movePlayerTo(6, 2);
        } else if (row == 4 && col == 9) {
            movePlayerTo(6, 8);
        } else if (row == 6 && col == 1) {
            movePlayerTo(8, 0);
        } else if (row == 7 && col == 6) {
            movePlayerTo(9, 8);
        }
    }

    public void Winner() {
        
       gameState = GameState.GameOver;
    }
 
    public void nextPlayer(){
            this.CurrentPlayer = factory.getNext();
            this.CurrentPlayer = CurrentPlayer.spawnPlayer(board);
    }
    @Override
    public GameState getGamestate() {
        return gameState;
    }
}
