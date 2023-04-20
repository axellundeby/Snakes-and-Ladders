package no.uib.inf101.sem2.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class diceTester {
    
    @Test
    public void testRollDice() {
        RandomThrow randomThrow = new RandomThrow();
        int result = randomThrow.rollDice();
        assertTrue(result >= 1 && result <= 6);
    }

}
