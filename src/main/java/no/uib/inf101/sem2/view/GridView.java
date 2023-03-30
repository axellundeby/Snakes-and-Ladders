package no.uib.inf101.sem2.view;

import javax.swing.JPanel;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.DiceState;
import no.uib.inf101.sem2.model.ViewableModel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Dimension;


public class GridView extends JPanel {
    
    //private final  int xMargin = 15;
    private final  int yMargin = 40;
    private final  int InnMargin = 2;
    private final ViewableModel view;
    private final ColorTheme colorTheme;

    public GridView(ViewableModel view) {
        this.view = view;
        this.colorTheme = new DefaultColorTheme();
        
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(635, 800));
        this.setBackground(getBackground());
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      drawGame(g2);
      drawDice(g2);
    }

    /**
     * draws the box where the cells are defined
     * @param g
     */
    private void drawGame(Graphics2D g) {
      Rectangle2D boardBox = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight() - yMargin * 2 - 80);
      BufferedImage boardImage = Inf101Graphics.loadImageFromResources("/boardPicture.jpeg");
      double scale = (boardBox.getHeight() - 1)/boardImage.getHeight();
      Inf101Graphics.drawImage(g, boardImage, boardBox.getX() + 1, boardBox.getY() + 1, scale);
      
    
      GridDimension dimension = view.getDimension();
      CellPositionToPixelConverter converter = new CellPositionToPixelConverter(boardBox, dimension, InnMargin);
      g.setFont(colorTheme.getFont());
      g.setColor(colorTheme.getFontColor());
      drawCells(g, view.getTilesOnBoard(), converter, colorTheme);
      
      Inf101Graphics.drawCenteredString(g, "Trykk på ternignen for å starte spillet ", getWidth(), 420, this.getWidth() - getWidth() * 2, 450);
      } 
    
      private void drawDice(Graphics2D g) {
     
        Rectangle2D diceRect = this.getRectangle();
        Color color = colorTheme.getBackgroundColor();
        g.setColor(color);
        g.draw(diceRect);

        if (view.getDiceState() == DiceState.ROLE) {
          BufferedImage diceImage = Inf101Graphics.loadImageFromResources("/dice.png");
          double scale = (diceRect.getHeight() - 1)/diceImage.getHeight();
          Inf101Graphics.drawImage(g, diceImage, diceRect.getX() + 1, diceRect.getY() + 1, scale);


          //Inf101Graphics.drawCenteredImage(g, diceImage, this.getWidth()/2, this.getHeight()- diceMargin, 0.3);
        }
      }
      private Rectangle2D getRectangle() {
        return new Rectangle2D.Double(getWidth()/2 - 50 , getHeight() - 140 , 120, 120);
      }
  
    /**
     * draws the cells
     * @param g
     * @param tetrisgrid
     * @param converter
     * @param colorTheme
     */ 
    private void drawCells(Graphics2D g, Iterable<GridCell<Character>> grid, CellPositionToPixelConverter converter, ColorTheme colorTheme) {
        for (GridCell<Character> cell : grid) {
        CellPosition cordinate = cell.pos();
        Ellipse2D rectangle = converter.getBoundsForCell(cordinate);
        
        Color color = colorTheme.getCellColor(cell.value());
        g.setColor(color);
        g.fill(rectangle);
        }
    }
}
