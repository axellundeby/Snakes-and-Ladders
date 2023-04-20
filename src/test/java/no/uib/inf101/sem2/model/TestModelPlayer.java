package no.uib.inf101.sem2.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;

public class TestModelPlayer {
 
    @Test
    public void snakeTest(){
        Board board = new Board(10,10); 
        PlayerFactory player = new StartPlayer();
        Playermodel model = new Playermodel(board, player);

        Player moved = model.getPlayerList().get(0).setPos(0, 1);
        model.getPlayerList().set(0, moved);
        model.SteppedOnSnake();
        assertTrue(board.get(new CellPosition(5, 0)) == 'P');
    }
    
    @Test
    public void ladderTest(){
        Board board = new Board(10,10); 
        PlayerFactory player = new StartPlayer();
        Playermodel model = new Playermodel(board, player);
        
        board.set(new CellPosition(9, 3), 'P');
        Player moved = model.getPlayerList().get(0).setPos(9, 3);
        model.getPlayerList().set(0, moved);
        model.SteppedOnLadder();
        assertTrue(board.get(new CellPosition(7, 4)) == 'P');
    }

    @Test
    public void testUpdateDiceNumber() { 
        Board board = new Board(10,10); 
        PlayerFactory player = new StartPlayer();
        Playermodel model = new Playermodel(board, player);
        model.updateDiceNumber(1);
        assertEquals(DiceState.ONE, model.getDiceState());
        model.updateDiceNumber(2);
        assertEquals(DiceState.TWO, model.getDiceState());
        model.updateDiceNumber(3);
        assertEquals(DiceState.THREE, model.getDiceState());
        model.updateDiceNumber(4);
        assertEquals(DiceState.FOUR, model.getDiceState());
        model.updateDiceNumber(5);
        assertEquals(DiceState.FIVE, model.getDiceState());
        model.updateDiceNumber(6);
        assertEquals(DiceState.SIX, model.getDiceState());
    }

    //test playerJump.. hjelp

    public void testPlayerJump(){

    }

    @Test
    public void testWinner(){
        Board board = new Board(10,10); 
        PlayerFactory player = new StartPlayer();
        Playermodel model = new Playermodel(board, player);

        Player moved = model.getPlayerList().get(0).setPos(0, 0);
        model.getPlayerList().set(0, moved);
        model.Winner();
        
        assertEquals(GameInfo.WINNER, model.getGameInfo());
        assertEquals(GameState.disbaleDice, model.getGamestate());

    }

    @Test
        public void testPlayerTurn() {

        Board board = new Board(10,10); 
        PlayerFactory player = new StartPlayer();
        Playermodel model = new Playermodel(board, player);
        List<Player> playerList = new ArrayList<Player>() ;

        playerList.add(new Player('P', null));
        playerList.add(new Player('Q', null));
        playerList.add(new Player('B', null));
        playerList.add(new Player('K', null));
        model.setAmountOfPlayers(4);    
        int initialIndex = model.getCurrentPlayerIndex();
        model.PlayerTurn();
        model.PlayerTurn();
        model.PlayerTurn();
        model.PlayerTurn();
        int newIndex = model.getCurrentPlayerIndex();
        assertEquals(initialIndex, newIndex);
        }
}
