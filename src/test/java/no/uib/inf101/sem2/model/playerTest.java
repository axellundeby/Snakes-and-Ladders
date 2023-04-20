package no.uib.inf101.sem2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;

public class playerTest {
    @Test
    public void testSetPos() {
        Player player = new Player('P', new CellPosition(0, 0));
        Player newPlayer = player.setPos(1, 1);
        assertEquals(newPlayer.getPos().row(), 1);
        assertEquals(newPlayer.getPos().col(), 1);
    }

    @Test
    public void testShiftedBY() {
        Player player = new Player('P', new CellPosition(3, 2));
        Player newPlayer = player.shiftedBy(4, 3);
        assertEquals(newPlayer.getPos().row(), 7);
        assertEquals(newPlayer.getPos().col(), 5);
    }

}
