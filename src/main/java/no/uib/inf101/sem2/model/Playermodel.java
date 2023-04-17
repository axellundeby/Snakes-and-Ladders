package no.uib.inf101.sem2.model;
import java.util.Arrays;
import java.util.List;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

public class Playermodel implements ViewableModel, PlayerFactory{
    private Board board;
    private PlayerFactory factory;
    private DiceState diceState = DiceState.ROLE;
    private GameState gameState = GameState.GameActive;
    private GameInfo gameInfo = GameInfo.DEFAULT;
    
    private int PlayerListIndex = 0;
    private List<Player> PlayerList;
    
     
//currentPlayer = playerList.get(playerListIndex % playerList.size());
    

// set num players(int)
// hente liste fra factory med spillere - gjort?

    public Playermodel(Board board, PlayerFactory factory){
        this.board = board;
        this.factory = factory;
        this.PlayerList = factory.getPlayerList(); 
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
       return PlayerList.get(PlayerListIndex);
    }

    @Override
    public DiceState getDiceState() {
        return diceState;
    }
   /**
    * This method updates the state of the dice based on the number of eyes that show up after rolling.
    * @param eyes  An integer value between 1 and 6, which represents the number of eyes that show up on the rolled dice.
    */

    public void updateDiceNumber(int eyes) {//kan være private?
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
   

    private void movePlayer(int MoveToRow, int MoveTocol) {
        Player ShapeCopy = PlayerList.get(PlayerListIndex).shiftedBy(MoveToRow, MoveTocol);
        PlayerList.set(PlayerListIndex,ShapeCopy);
        
    }
    
    private void movePlayerTo(int MoveToRow, int MoveToCol) {
        PlayerList.get(PlayerListIndex).setPos(new CellPosition(MoveToRow, MoveToCol));
    }
    
    //PlayerList.get(PlayerListIndex).setPos(ShapeCopy.getPos());
    /**
     * This method moves the player right if the row is even and left if it is odd, if the player is on the edge of the board it moves the player up.
     */
    public void PlayerJump() {
        int PlayerRow = PlayerList.get(PlayerListIndex).getPos().row();
        int PlayerCol = PlayerList.get(PlayerListIndex).getPos().col();
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


    /**
     * This method checks if the player is on a snake and moves the player accordingly.
     */

    public void SteppedOnSnake() {
        int row = PlayerList.get(PlayerListIndex).getPos().row();
        int col = PlayerList.get(PlayerListIndex).getPos().col();

        if (row == 0 && col == 1) {
            movePlayerTo(5, 0);
        } else if (row == 1 && col == 8) {
            movePlayerTo(4, 7);
        } else if (row == 2 && col == 4) {
            movePlayerTo(4, 2);
        } else if (row == 3 && col == 5) {
            movePlayerTo(5, 4);
        } else if (row == 4 && col == 6) {
             movePlayerTo(6, 9);
        } else if (row == 5 && col == 2) {
            movePlayerTo(9, 2);
        } else if (row == 6 && col == 0) {
            movePlayerTo(9, 2);
        } else if (row == 7 && col == 6) {
            movePlayerTo(9, 4);
        }
        gameInfo = GameInfo.SNAKE;
     }

    /**
     * This method checks if the player is on a ladder and moves the player accordingly.
     */
     public void SteppedOnLadder() {
        int row = PlayerList.get(PlayerListIndex).getPos().row();
        int col = PlayerList.get(PlayerListIndex).getPos().col();
        if (row == 9 && col == 3) {
            movePlayerTo(7, 4);
        } else if (row == 8 && col == 7) {
            movePlayerTo(5, 5);
        } else if (row == 6 && col == 7) {
            movePlayerTo(5, 8);
        } else if (row == 5 && col == 1) {
            movePlayerTo(3, 2);
        } else if (row == 5 && col == 9) {
            movePlayerTo(3, 8);
        } else if (row == 3 && col == 1) {
            movePlayerTo(1, 0);
        } else if (row == 2 && col == 6) {
            movePlayerTo(0, 8);
        }
        gameInfo = GameInfo.LADDER;
    }
    /**
     * this method checks if the player is out of the grid if so, the player wins.
     */

    public void Winner() {
        int row = PlayerList.get(PlayerListIndex).getPos().row();
        int col = PlayerList.get(PlayerListIndex).getPos().col();
        if(row<0 || col<0){
            gameInfo = GameInfo.WINNER;
            gameState=GameState.disbaleDice;
        }
    }

    /**
     * this method makes another player appear if there are no more players left.
     */
    //denne metoden er det også krøll i tror jeg, trenger jeg den om jeg har amout of players?
    public void PlayerAppear(){
        if(!factory.hasMorePlayers()){
            Player CurrentPlayerTemp = factory.getNext();
            PlayerList.set(PlayerListIndex,CurrentPlayerTemp);
        }
    }
    //viljar sa noe om rar indeksering hopping, hente ut amout of players
    /**
     * This method chenges the players turn, by changing the index of the player in the playerlist.
     */
    public void PlayerTurn(){
        if (PlayerListIndex >= 3) {//er dette lov, helst amountOfPlayers
            PlayerListIndex = 0;

        } else {
            PlayerListIndex++;
            PlayerList.get(PlayerListIndex);//tipper denne er feil
            //PlayerList.set(PlayerListIndex,PlayerList.get(PlayerListIndex));
        }
    }

    @Override
    public GameState getGamestate() {
        return gameState;
    }

    //funker ikke 
    //om en spiller går på en annen spiller, flyttes den til en tilfeldig posisjon under rad 7
    //sjekket at jeg ikke gikk på meg selv
    //hvis det ligger en spiller som ikke er seg selv, flytt den spilleren til blabla, bruk en for loop for å hente alle spillere
    //hopp over plassen til en annen spiller, behold stump
    //må da hente ut spillerID fra listen over
    public void stumpPlayer() {
        int randomrow = (int) (Math.random() * 3) + 7;
        int randomCol = (int) (Math.random() * 10);
        CellPosition pos = PlayerList.get(PlayerListIndex).getPos();
        if(board.get(pos) == '-'){
            movePlayer(randomrow, randomCol);
            gameInfo = GameInfo.STUMP;
        }
    }

    private void playerOnNextTile(){
        int PlayerRow = PlayerList.get(PlayerListIndex).getPos().row();
        int PlayerCol = PlayerList.get(PlayerListIndex).getPos().col();

        if(PlayerRow % 2 == 0){
            if(PlayerCol == 0){
                movePlayer(1, -1);
            }
            movePlayer(0, -2);
        }
        else if(PlayerRow % 2 != 0){
            if(PlayerCol == 9){
                movePlayer(1, 1);
            }
            movePlayer(0, 2);
        }

    }

    public boolean playerOnNextTileChecker(){
        CellPosition pos = PlayerList.get(PlayerListIndex).getPos();
        if(board.get(pos) != '-' || board.get(pos) != PlayerList.get(PlayerListIndex).getPlayerID()){//funker dette
            playerOnNextTile();
        }
        return false;
        
    }

    public void cellOcipied(){
        CellPosition pos = PlayerList.get(PlayerListIndex).getPos();
        if(board.get(pos) != '-' && PlayerList.get(PlayerListIndex).getPlayerID() != PlayerList.get(PlayerListIndex).getPlayerID()){//eller seg selv
            //hopper over den cellen, men teller det som et hopp på terningen
        }
    } 

    @Override
    public GameInfo getGameInfo() {
        return gameInfo;
    }

    @Override
    public Player getNext() {
        return getNext();
    }
    @Override
    public boolean hasMorePlayers() {
        return hasMorePlayers();
    }

    @Override
    public List<Player> getPlayerList() {
        return PlayerList; 
    }
    
}
