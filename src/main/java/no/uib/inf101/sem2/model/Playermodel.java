package no.uib.inf101.sem2.model;
import java.util.List;
import java.util.Random;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

public class Playermodel implements ViewableModel, PlayerFactory{
    private Board board;
    private PlayerFactory factory;
    private final Random random = new Random();
    private DiceState diceState = DiceState.ROLE;
    private GameState gameState = GameState.GameInActive;
    private GameInfo gameInfo = GameInfo.DEFAULT;
    private int PlayerListIndex = 0;
    private List<Player> PlayerList;
    private int amountOfplayers = 2;
    private char overWrittenValue = '-';

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
    public DiceState getDiceState() {
        return diceState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }


    public void setAmountOfPlayers(int amountOfplayers) {
        this.amountOfplayers = amountOfplayers;
    }

    @Override
    public GameState getGamestate() {
        return gameState;
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

    private void movePlayerTo(int MoveToRow, int MoveToCol) {
        Player currentPlayer = PlayerList.get(PlayerListIndex);
        CellPosition currentPos = currentPlayer.getPos();
        board.set(currentPos, '-');
        
        Player updatedPlayer = currentPlayer.setPos(MoveToRow, MoveToCol);
        CellPosition newPos = updatedPlayer.getPos();
        char cellValue = board.get(newPos);
        
        if (board.positionIsOnGrid(newPos)) {
            if (cellValue == '-') {
                PlayerList.set(PlayerListIndex, updatedPlayer);
                board.set(newPos, updatedPlayer.getPlayerID());
            } else {
                // Stump the player at the target cell
                int stumpedPlayerIndex = getPlayer(cellValue);
                if (stumpedPlayerIndex != -1) {
                    stumpPlayer(stumpedPlayerIndex);
                    PlayerList.set(PlayerListIndex, updatedPlayer);
                    board.set(newPos, updatedPlayer.getPlayerID());
                }
            }
        }
    }
 
    private void movePlayer(int Row, int Col, boolean finished) {
        Player temp = PlayerList.get(PlayerListIndex);
        CellPosition pos = PlayerList.get(PlayerListIndex).getPos();    
        board.set(pos, overWrittenValue);
        Player ShapeCopy = temp.shiftedBy(Row, Col);
        CellPosition newPos = new CellPosition(ShapeCopy.getPos().row(), ShapeCopy.getPos().col());
        overWrittenValue = board.get(newPos);//setter overwrittenValue til valuen til spilleren som står på plassen
        
        if (board.positionIsOnGrid(newPos)){
            PlayerList.set(PlayerListIndex,ShapeCopy);
            if(getPlayer(overWrittenValue) != -1 && finished){//om flyttingen er ferdig og det er en annen spiller på plassen
                stumpPlayer(getPlayer(overWrittenValue));//the player under is stumped
            }   
            board.set(ShapeCopy.getPos(), ShapeCopy.getPlayerID());
        }
    }
    
    /**
     * returns the index of the player in the playerlist, if the player is not in the list it returns -1
     * @param value, a char value that represents a player
     * @return
     */
   
    private int getPlayer(char value){
        for(int i = 0; i < amountOfplayers; i++){
            if(PlayerList.get(i).getPlayerID() == value){
                return i;
            }
        }
        return -1;
    }

    /**
     * This method moves a player to a random pos on the board.
     * @param playerIndex, the index of the player in the playerlist
     */

    private void stumpPlayer(int playerIndex) {
        int randomCol = random.nextInt(10);
        int randomRow = random.nextInt(3)+7;
        while(!board.get(new CellPosition(randomRow, randomCol)).equals('-')){
            randomCol = random.nextInt(10);
            randomRow = random.nextInt(3)+7;
        }
        int tempIndex = PlayerListIndex;
        PlayerListIndex = playerIndex;
        movePlayerTo(randomRow, tempIndex);
        PlayerListIndex = tempIndex;
        gameInfo = GameInfo.STUMP;
        overWrittenValue = '-';
    }

    
    /**
     *  This method moves the player right if the row is even and left if it is odd, if the player is on the edge of the board it moves the player up.
     * @param finished checks the jumping is finished or not
     */
    public void PlayerJump(boolean finished) {
        int PlayerRow = PlayerList.get(PlayerListIndex).getPos().row();
        int PlayerCol = PlayerList.get(PlayerListIndex).getPos().col();

        if(PlayerRow % 2 != 0){
            if (PlayerCol == 9){
                PlayerOnEdge(finished);
            }
            else{
                movePlayerRight(finished);
            }
        }
        else{
            if (PlayerCol == 0){
                PlayerOnEdge(finished);
            }
            else{
                movePlayerLeft(finished);
            }
        }
    }
    private void movePlayerRight(boolean finished) {
        movePlayer(0, +1, finished);
    }
    
    private void movePlayerLeft(boolean finished) {
        movePlayer(0, -1, finished);
    }
    
    private void PlayerOnEdge(boolean finished){       
        movePlayer(-1,0, finished);
    }


    /**
     * This method checks if the player is on a snake and moves the player accordingly.
     */

    public void SteppedOnSnake() {
        int row = PlayerList.get(PlayerListIndex).getPos().row();
        int col = PlayerList.get(PlayerListIndex).getPos().col();

        if (row == 0 && col == 1) {
            movePlayerTo(5, 0);
            gameInfo = GameInfo.SNAKE;
        } else if (row == 1 && col == 8) {
            movePlayerTo(4, 7);
            gameInfo = GameInfo.SNAKE;
        } else if (row == 2 && col == 4) {
            movePlayerTo(4, 2);
            gameInfo = GameInfo.SNAKE;
        } else if (row == 3 && col == 5) {
            movePlayerTo(5, 4);
            gameInfo = GameInfo.SNAKE;
        } else if (row == 4 && col == 6) {
             movePlayerTo(6, 9);
             gameInfo = GameInfo.SNAKE;
        } else if (row == 5 && col == 2) {
            movePlayerTo(8, 2);
            gameInfo = GameInfo.SNAKE;
        } else if (row == 6 && col == 0) {
            movePlayerTo(9, 2);
            gameInfo = GameInfo.SNAKE;
        } else if (row == 7 && col == 6) {
            movePlayerTo(9, 4);
            gameInfo = GameInfo.SNAKE;
        }
     }


    /**
     * This method checks if the player is on a ladder and moves the player accordingly.
     */
     public void SteppedOnLadder() {
        int row = PlayerList.get(PlayerListIndex).getPos().row();
        int col = PlayerList.get(PlayerListIndex).getPos().col();
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

    public void updateGameinfo(){
        gameInfo = GameInfo.DEFAULT;
    }

    /**
     * this method checks if the player is out of the grid if so, the player wins.
     */

    public void Winner() {
        int row = PlayerList.get(PlayerListIndex).getPos().row();
        int col = PlayerList.get(PlayerListIndex).getPos().col();
        if(row == 0 && col == 0){
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
 
    /**
     * This method chenges the players turn, by changing the index of the player in the playerlist.
     */
    public void PlayerTurn(){
        if (PlayerListIndex >= amountOfplayers - 1) {
            PlayerListIndex = 0;

        } else {
            PlayerListIndex++;
        }
    }
}
