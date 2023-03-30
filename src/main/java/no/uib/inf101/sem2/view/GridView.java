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
    private final int diceMargin = 100;

    public GridView(ViewableModel view) {
        this.view = view;
        this.colorTheme = new DefaultColorTheme();
        
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(612, 800));
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
      BufferedImage boardImage = Inf101Graphics.loadImageFromResources("/boardPicture.jpeg");
      Inf101Graphics.drawCenteredImage(g, boardImage, this.getWidth()/2, this.getHeight()/2 - yMargin - 50, this.getWidth()/612 );
      
      Rectangle2D Box = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight() - yMargin * 2 - 80);
      GridDimension dimension = view.getDimension();
      CellPositionToPixelConverter converter = new CellPositionToPixelConverter(Box, dimension, InnMargin);
      
      g.setFont(colorTheme.getFont());
      g.setColor(colorTheme.getFontColor());
      
      drawCells(g, view.getTilesOnBoard(), converter, colorTheme);
      
      Inf101Graphics.drawCenteredString(g, "Trykk på ternignen for å starte spillet ", getWidth(), 410, this.getWidth() - getWidth() * 2, 450);

      //skal lage terning, med switch 
      } 
    
      private void drawDice(Graphics2D g) {
        if (view.getDiceState() == DiceState.ROLE) {
          BufferedImage diceImage = Inf101Graphics.loadImageFromResources("/dice.png");
          Inf101Graphics.drawCenteredImage(g, diceImage, this.getWidth()/2, this.getHeight()- diceMargin, 0.3);
        }

        // if(view.getDiceState() == "one"){
        //   //bilde til 1
        // }
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
