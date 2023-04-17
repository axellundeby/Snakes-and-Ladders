package no.uib.inf101.sem2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;

public class TestModelPlayer {
 
    @Test
    public void ladderTest(){
        Board board = new Board(20, 10);
        PlayerFactory factory = new PatternedPlayerFactory("P");
        Playermodel model = new Playermodel(board, factory, factory.getNext());
        //model.movePlayerTo(0, 1);//om en spiller flyttes til en slange skal den nye posisjonen være slangen sin hale
        board.set(new CellPosition(0, 1), 'L');
        model.SteppedOnLadder();
        assertTrue(board.get(new CellPosition(5, 0)) == 'P');
    }
}
