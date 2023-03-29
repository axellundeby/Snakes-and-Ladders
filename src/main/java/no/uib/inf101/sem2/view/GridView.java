package no.uib.inf101.sem2.view;

import javax.swing.JPanel;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.ViewableModel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Dimension;

public class GridView extends JPanel {
    
    private final  int xMargin = 15;
    private final  int yMargin = 40;
    private final  int InnMargin = 2;
    private final ViewableModel view;
    private final ColorTheme colorTheme;

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
    }

    /**
     * draws the box where the cells are defined
     * @param g
     */
    private void drawGame(Graphics2D g) {
      BufferedImage image = Inf101Graphics.loadImageFromResources("/boardPicture.jpeg");
      Inf101Graphics.drawCenteredImage(g, image, this.getWidth()/2, this.getHeight()/2 - yMargin - 50, getWidth()/612);
      
      Rectangle2D Box = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight() - yMargin * 2 - 80);
      GridDimension dimension = view.getDimension();
      CellPositionToPixelConverter converter = new CellPositionToPixelConverter(Box, dimension, InnMargin);
      
      g.setFont(colorTheme.getFont());
      g.setColor(colorTheme.getFontColor());
      
      drawCells(g, view.getTilesOnBoard(), converter, colorTheme);
      


      Inf101Graphics.drawCenteredString(g, "Axels StigeSpill  ", xMargin,450, this.getWidth() - xMargin * 2, 450);

      //skal lage terning, med switch 
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
