package no.uib.inf101.sem2.view;

import java.awt.Color;
import java.awt.Font;


public class DefaultColorTheme implements ColorTheme{

    @Override
    public Color getCellColor(char c) {
      Color color = switch(c) {
        case 'P' -> Color.BLACK;//spiller
        case 'Q' -> Color.cyan;//spiller
        //.... fyll ut dine favorittfarger
        case '-' -> new Color(0, 0, 0, 37);
        default -> throw new IllegalArgumentException(
            "No available color for '" + c + "'");
      };
      return color;
    }

    
    @Override
    public Font getFont() {
        return new Font("Serif", Font.BOLD, 5);
    }

    @Override
    public Color getFontColor() {
        return Color.magenta;
    }

    @Override
    public Color getStartBackgroundColor() {
        return new Color(250, 163, 0);
    }

    @Override
    public Font getStartBackgroundFont() {
        return new Font("Serif", Font.BOLD, 5);
    }

    @Override
    public Color getStartFontColor() {
        return new Color(255, 98, 1);
    }

    
}

