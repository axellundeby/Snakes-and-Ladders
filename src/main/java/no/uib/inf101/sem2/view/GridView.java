package no.uib.inf101.sem2.view;

import javax.swing.JPanel;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.DiceState;
import no.uib.inf101.sem2.model.ViewableModel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

public class GridView extends JPanel {
  private final  int yMargin = 0;
  private final  int InnMargin = 2;
  private final ViewableModel view;
  private final ColorTheme colorTheme;
  private boolean mouseIsInTheRectangle = false;
  BufferedImage boardImage = Inf101Graphics.loadImageFromResources("/boardPicture.jpeg");

  
    public GridView(ViewableModel view) {
        this.view = view;
        this.colorTheme = new DefaultColorTheme();
        
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(700, 750));
        this.setBackground(getBackground());
        this.setupMousePositionUpdater();

    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      drawBoard(g2);
      drawDice(g2);
    }

    /**
     * draws the box where the cells are defined
     * @param g
     */
    private void drawBoard(Graphics2D g) {//boksen blir ikke mindre når jeg endrer størrelsen på vinduet
      Rectangle2D boardBox = this.getBoardRectangle();
      double scale = (boardBox.getHeight() - 1)/boardImage.getHeight();
      Inf101Graphics.drawImage(g, boardImage, boardBox.getX() + 1, boardBox.getY() + 1, scale);
      
      GridDimension dimension = view.getDimension();
      CellPositionToPixelConverter converter = new CellPositionToPixelConverter(boardBox, dimension, InnMargin);
      drawCells(g, view.getTilesOnBoard(), converter, colorTheme);
      drawCells(g, view.getPiece(), converter, colorTheme);//tegner brikke
      }
      
      private Rectangle2D getBoardRectangle() {
        int boardWidth = (int) (getWidth() * 0.8); 
        int boardHeight = (int) (boardWidth * (boardImage.getHeight() / boardImage.getWidth()));
        int boardX = (getWidth() - boardWidth) / 2;
        int boardY = yMargin;
        return new Rectangle2D.Double(boardX, boardY, boardWidth, boardHeight);
     }
    
     private void drawDice(Graphics2D g) {
      Rectangle2D diceRect = this.getDiceRectangle();
      BufferedImage diceImage = null;
      DiceState diceState = view.getDiceState();
      switch (diceState) {
        case ROLE:
          diceImage = Inf101Graphics.loadImageFromResources("/dice.png");
          Inf101Graphics.drawCenteredString(g, "Trykk på ternignen for å starte spillet ", getWidth(), getHeight()-40, this.getWidth() - getWidth() * 2,10);
          break;
        case ONE:
          diceImage = Inf101Graphics.loadImageFromResources("die_1.png");
          Inf101Graphics.drawCenteredString(g, "Du rullet 1, neste sin tur", getWidth(), getHeight()-40, this.getWidth() - getWidth() * 2,10);
          break;
        case TWO:
          diceImage = Inf101Graphics.loadImageFromResources("die_2.png");
          Inf101Graphics.drawCenteredString(g, "Du rullet 2, neste sin tur", getWidth(), getHeight()-40, this.getWidth() - getWidth() * 2,10);
          break;
        case THREE:
          diceImage = Inf101Graphics.loadImageFromResources("die_3.png");
          Inf101Graphics.drawCenteredString(g, "Du rullet 3, neste sin tur", getWidth(), getHeight()-40, this.getWidth() - getWidth() * 2,10);
          break;
        case FOUR:
          diceImage = Inf101Graphics.loadImageFromResources("die_4.png");
          Inf101Graphics.drawCenteredString(g, "Du rullet 4, neste sin tur", getWidth(), getHeight()-40, this.getWidth() - getWidth() * 2,10);
          break;
        case FIVE:
          diceImage = Inf101Graphics.loadImageFromResources("die_5.png");
          Inf101Graphics.drawCenteredString(g, "Du rullet 5, neste sin tur", getWidth(), getHeight()-40, this.getWidth() - getWidth() * 2,10);
          break;
        case SIX:
          diceImage = Inf101Graphics.loadImageFromResources("die_6.png");
          Inf101Graphics.drawCenteredString(g, "Du rullet 6, neste sin tur", getWidth(), getHeight()-40, this.getWidth() - getWidth() * 2,10);
          break;
      }
      
      double scale = (diceRect.getHeight() - 1) / diceImage.getHeight();
      Inf101Graphics.drawImage(g, diceImage, diceRect.getX() + 1, diceRect.getY() + 1, scale);
      // g.setFont(colorTheme.getFont());
      // g.setColor(colorTheme.getFontColor());//whaaat??
    }

      public Rectangle2D getDiceRectangle() {
        double width = getWidth();
        double height = getHeight();
        double size = Math.min(width, height) * 0.15; 
        double x = (width - size) / 2;
        double y = height - size - 50;
        return new Rectangle2D.Double(x, y, size, size);
    }

      private void setupMousePositionUpdater() {
        // Keep the mousePosition variable up to date
        this.addMouseMotionListener(new MouseMotionAdapter() {
          @Override
          public void mouseMoved(MouseEvent e) {
            mouseIsInTheRectangle = getDiceRectangle().contains(e.getPoint());
            updateCursor();
            repaint();
          }
        });
      }
    
      private void updateCursor() {
        if (mouseIsInTheRectangle) {
          setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
          setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
      }

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
