package no.uib.inf101.sem2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;

public class startPlayerTest {
    
    
    private StartPlayer startPlayer;

    @BeforeEach
    public void setUp() {
        startPlayer = new StartPlayer();
    }

    @Test
    public void testGetNext() {
         // sjekker om spiller P finnes
        Player player1 = startPlayer.getNext();
        assertNotNull(player1);
        assertEquals('P', player1.getPlayerID());
        assertEquals(new CellPosition(9, 0), player1.getPos());

        // sjekker om spiller B finnes
        Player player2 = startPlayer.getNext();
        assertNotNull(player2);
        assertEquals('B', player2.getPlayerID());
        assertEquals(new CellPosition(9, 0), player2.getPos());



    }
   
    @Test
    public void testGetPlayerList() {
        List<Player> players = startPlayer.getPlayerList();
        assertEquals(4, players.size());
        assertEquals('P', players.get(0).getPlayerID());
        assertEquals(new CellPosition(9, 0), players.get(0).getPos());

        assertEquals('B', players.get(1).getPlayerID());
        assertEquals(new CellPosition(9, 0), players.get(1).getPos());

        assertEquals('Q', players.get(2).getPlayerID());
        assertEquals(new CellPosition(9, 0), players.get(2).getPos());

        assertEquals('K', players.get(3).getPlayerID());
        assertEquals(new CellPosition(9, 0), players.get(3).getPos());

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            players.remove(player);
        }
        assertTrue(startPlayer.hasMorePlayers());
    }
    
}
