
package no.uib.inf101.sem2.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;

import org.junit.jupiter.api.Test;


public class viewTest{

    
    @Test
    public void testGetCellColor() {
        DefaultColorTheme startPlayer = new DefaultColorTheme();
        assertEquals(Color.BLACK, startPlayer.getCellColor('P'));
        assertEquals(Color.cyan, startPlayer.getCellColor('Q'));
        assertEquals(Color.PINK, startPlayer.getCellColor('B'));
        assertEquals(Color.yellow, startPlayer.getCellColor('K'));
        assertEquals(new Color(0, 0, 0, 37), startPlayer.getCellColor('-'));
        // test that an exception is thrown for an invalid character
        assertThrows(IllegalArgumentException.class, () -> {
            startPlayer.getCellColor('X');
        });
    }

    

}

