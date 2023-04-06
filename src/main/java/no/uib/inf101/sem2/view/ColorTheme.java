package no.uib.inf101.sem2.view;
import java.awt.Color;
import java.awt.Font;

public interface ColorTheme {
     Color getCellColor(char c);
     
     Color getFrameColor();
    
     Color getBackgroundColor();

     Font getFont();

     Color getFontColor();

     Color getGameOverBackgroundColor();

     Font getFontGameOver();

     Color getWinnerBackground();

     Font getFontWinner();
     
     Color getLadderBackground();
     
     Color getSnakeBackground();


}   
