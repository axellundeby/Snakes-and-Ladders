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
    private GameInfo gameInfo = GameInfo.DEFAULT;
    

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

        if (row == 0 && col == 1) {//slange1
            movePlayerTo(5, 0);
            gameInfo = GameInfo.SNAKE;
        } else if (row == 1 && col == 8) {
            movePlayerTo(4, 7);//slange 2
            gameInfo = GameInfo.SNAKE;
        } else if (row == 2 && col == 4) {
            movePlayerTo(4, 2);//slange 3
            gameInfo = GameInfo.SNAKE;
        } else if (row == 3 && col == 5) {
            movePlayerTo(5, 4);//
            gameInfo = GameInfo.SNAKE;
        } else if (row == 4 && col == 6) {
             movePlayerTo(6, 9);//
             gameInfo = GameInfo.SNAKE;
        } else if (row == 5 && col == 2) {
            movePlayerTo(9, 2);//
            gameInfo = GameInfo.SNAKE;
        } else if (row == 6 && col == 0) {
            movePlayerTo(9, 2);//
            gameInfo = GameInfo.SNAKE;
        } else if (row == 7 && col == 6) {
            movePlayerTo(9, 4);//
            gameInfo = GameInfo.SNAKE;
        }
     }

     public void SteppedOnLadder() {
        int row = CurrentPlayer.getPos().row();
        int col = CurrentPlayer.getPos().col();
        if (row == 9 && col == 3) {
            movePlayerTo(7, 4);
            gameInfo = GameInfo.LADDER;
        } else if (row == 8 && col == 7) {
            movePlayerTo(5, 5);
            gameInfo = GameInfo.LADDER;
        } else if (row == 6 && col == 7) {
            movePlayerTo(5, 8);
            gameInfo = GameInfo.LADDER;
        } else if (row == 5 && col == 1) {
            movePlayerTo(3, 2);
            gameInfo = GameInfo.LADDER;
        } else if (row == 5 && col == 9) {
            movePlayerTo(3, 8);
            gameInfo = GameInfo.LADDER;
        } else if (row == 3 && col == 1) {
            movePlayerTo(1, 0);
            gameInfo = GameInfo.LADDER;
        } else if (row == 2 && col == 6) {
            movePlayerTo(0, 8);
            gameInfo = GameInfo.LADDER;
        }
    }

    public void Winner() {
        int row = CurrentPlayer.getPos().row();
        int col = CurrentPlayer.getPos().col();
        if(row<=0 && col<=0){
            gameInfo = GameInfo.WINNER;
            gameState = GameState.GameInActive;
        }
    }
    
    
    public void PlayerAppear(){
        Player CurrentPlayerTemp = factory.getNext().spawnPlayer(board);
        CurrentPlayer = CurrentPlayerTemp;
    }


    
    //må fikse tur system mellom 2 spillere
    public void turn(){

    }

    @Override
    public GameState getGamestate() {
        return gameState;
    }
    //om en spiller går på en annen spiller, flyttes den til start
    public void stumpPlayer() {
        CellPosition pos = CurrentPlayer.getPos();
        if(board.get(pos) == '-'){
            movePlayer(9, 0);
        }
    }
    @Override
    public GameInfo getGameInfo() {
        return gameInfo;
    }
}
