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
        
        board.set(new CellPosition(0, 1), 'P');//dette går ikke 
        model.SteppedOnSnake();
        assertTrue(board.get(new CellPosition(5, 0)) == 'P');
    }
    
    @Test
    public void ladderTest(){
        Board board = new Board(10,10); 
        PlayerFactory player = new StartPlayer();
        Playermodel model = new Playermodel(board, player);
        
        board.set(new CellPosition(9, 3), 'P');//dette går ikke 
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

    //testwinner

    public void testWinner(){

    }

    //PlayerAppear

    @Test
    public void testPlayerAppear() {
        Board board = new Board(10,10); 
        PlayerFactory player = new StartPlayer();
        Playermodel model = new Playermodel(board, player);
        model.PlayerAppear();
        assertEquals(model.getDiceState(), DiceState.ONE);
    }

    //PlayerTurn funker ikke 
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
          
        int initialIndex = model.getCurrentPlayerIndex();
        model.PlayerTurn();
        model.PlayerTurn();
        model.PlayerTurn();
        int newIndex = model.getCurrentPlayerIndex();
        assertEquals(playerList.get(initialIndex+3), playerList.get(newIndex));
        }
}
