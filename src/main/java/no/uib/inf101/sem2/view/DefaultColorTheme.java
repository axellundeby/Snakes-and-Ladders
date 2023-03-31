package no.uib.inf101.sem2.view;

import java.awt.Color;
import java.awt.Font;


public class DefaultColorTheme implements ColorTheme{

    @Override
    public Color getCellColor(char c) {
      Color color = switch(c) {
        case 'G' -> new Color(152, 251, 152);//light green
        case 'J' -> Color.green;//green
        case 'T' -> Color.red;
        case 'R' -> new Color (255, 160, 122);//light red
        case 'p' -> Color.green;//spiller
        case 'q' -> Color.blue;//spiller
        //.... fyll ut dine favorittfarger
        case '-' -> new Color(0, 0, 0, 37);
        default -> throw new IllegalArgumentException(
            "No available color for '" + c + "'");
      };
      return color;
    }


    @Override
    public Color getFrameColor() {
        return new Color(0, 0, 0, 0);
    }

    @Override
    public Color getBackgroundColor() {
      return null;
    }
    
    
    @Override
    public Font getFont() {
        return new Font("Serif", Font.BOLD, 15);
    }



    @Override
    public Font getFontGameOver() {
        return new Font("Serif", Font.BOLD, 50);
    }


    @Override
    public Color getFontColor() {
        return Color.black;
    }


    @Override
    public Color getGameOverBackgroundColor() {
        return new Color(0, 0, 0, 128);
    }

    
}

