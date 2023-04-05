package no.uib.inf101.sem2.view;
import java.awt.Color;
import java.awt.Font;

public interface ColorTheme {
     /**@return the color of a cell */
     Color getCellColor(char c);
     
     /**@return the color of the frame */
     Color getFrameColor();
    
     /**@return the color of the background */
     Color getBackgroundColor();

     
     /**
      * @return the font
      */
     Font getFont();

     /**
      * @return the fontcolor
      */
     Color getFontColor();

     /**
      * @return the gameover backgroundColor
      */

     Color getGameOverBackgroundColor();

     Font getFontGameOver();

     Color getWinnerBackground();

     Font getFontWinner();


}   
