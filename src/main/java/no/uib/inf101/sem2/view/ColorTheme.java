package no.uib.inf101.sem2.view;
import java.awt.Color;
import java.awt.Font;

public interface ColorTheme {
     /**
      * Returns the Color associated with the given character representation of a game piece.
      * @param c a character representing a cell
      * @return a cellcolor
      */
     Color getCellColor(char c);
    
     /**
     @return The font used for rendering text on the game board.
     */
     Font getFont();

     /**
     @return The color of the font used for rendering text on the game board.
     */

     Color getFontColor();

     /**
     @return The color of the background used for rendering a background on the game board.
     */

     Color getStartBackgroundColor();

     /**
     @return The font used for rendering text on the game board.
     */
     Font getStartBackgroundFont();

     /**
     @return The color of the font used for rendering text on the game board.
     */
     Color getStartFontColor();

      /**
     @return The color of the box menu
     */
     Color getBoxColor();

      /**
     @return The color of the font used when a event is triggered or a dice is rolled
     */
    
     Color getEventNDiceFontColor();

}   
