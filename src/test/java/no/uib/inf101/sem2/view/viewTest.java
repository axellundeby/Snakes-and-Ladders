
package no.uib.inf101.sem2.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.Board;

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

    @Test
    public void sanityTest() {
      GridDimension gd = new Board(3, 4);
      CellPositionToPixelConverter converter = new CellPositionToPixelConverter(new Rectangle2D.Double(29, 29, 340, 240), gd, 30);
      Ellipse2D expected = new Ellipse2D.Double(214, 129, 47.5, 40);
      assertEquals(expected, converter.getBoundsForCell(new CellPosition(1, 2)));
    }
  

}

