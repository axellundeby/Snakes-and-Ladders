package no.uib.inf101.sem2.view;

import javax.swing.JPanel;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.DiceState;
import no.uib.inf101.sem2.model.GameInfo;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.ViewableModel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
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
  private boolean mouseIsInTheDice = false;
  private boolean mouseIsInTheStart = false;
  private boolean mouseIsInTheRectfour = false;
  private boolean mouseIsInTheRecthree = false;
  private boolean mouseIsInTheRectwo = false;
  
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
      if(view.getGamestate() == GameState.GameActive){//om spillet er aktivt
        drawBoard(g2);
        drawDice(g2);
        drawEvent(g2);
      }
      
      else if(view.getGamestate() == GameState.GameInActive){//om spillet ikke er aktivt
        startGame(g2);
        drawFourPlayer(g2);
        drawThreePlayer(g2);
        drawTwoPlayer(g2);
        drawStartGame(g2);
      }
    }

    //startscreen, start knapp, regler, kanskje antall spillere
    
  private void startGame(Graphics2D g) {
      ColorTheme StartColor = new DefaultColorTheme();
      g.setColor(StartColor.getStartBackgroundColor());
      g.fillRect(0, 0, this.getWidth(), this.getHeight());

      g.setFont(StartColor.getStartBackgroundFont());
      g.setColor(StartColor.getStartFontColor());

      Inf101Graphics.drawCenteredString(g, "Axels fantastiske stigespill",0, 0, this.getWidth(), 60); 
    }

//når man trykker innen for firkanten henter den gjør den spillet aktivt, spillet må starte innaktivt

/**
@return A RoundRectangle2D object representing the start box on the game board.
*/

public RoundRectangle2D getStartBoxRectangle() {
  double width = getWidth();
  double height = getHeight();
  double size = Math.min(width, height) * 0.15;
  double widthMultiplier = 3.0;
  double adjustedWidth = size * widthMultiplier;
  double adjustedHeight = size;
  double x = (width - adjustedWidth) / 2;
  double y = (height - 650);
  return new RoundRectangle2D.Double(x, y, adjustedWidth, adjustedHeight,30,20);
}

  /**
@return A RoundRectangle2D object representing three players.
*/
  public RoundRectangle2D getThreePlayerBoxRectangle() {
  double width = getWidth();
  double height = getHeight();
  double size = Math.min(width, height) * 0.15;
  double widthMultiplier = 3.0;
  double adjustedWidth = size * widthMultiplier;
  double adjustedHeight = size;
  double x = (width - adjustedWidth) / 2;
  double y = (height - 350);
  return new RoundRectangle2D.Double(x, y, adjustedWidth, adjustedHeight,30,20);
  }
   /**
@return A RoundRectangle2D object representing two players.
// */
  public RoundRectangle2D getTwoPlayerBoxRectangle() {
  double width = getWidth();
  double height = getHeight();
  double size = Math.min(width, height) * 0.15;
  double widthMultiplier = 3.0;
  double adjustedWidth = size * widthMultiplier;
  double adjustedHeight = size;
  double x = (width - adjustedWidth) / 2;
  double y = (height - 500);
  return new RoundRectangle2D.Double(x, y, adjustedWidth, adjustedHeight,30,20);
  }

  /**
@return A RoundRectangle2D object representing four players.
*/

public RoundRectangle2D getFourPlayerBoxRectangle() {
  double width = getWidth();
  double height = getHeight();
  double size = Math.min(width, height) * 0.15;
  double widthMultiplier = 3.0;
  double adjustedWidth = size * widthMultiplier;
  double adjustedHeight = size;
  double x = (width - adjustedWidth) / 2;
  double y = (height - 200);
  return new RoundRectangle2D.Double(x, y, adjustedWidth, adjustedHeight,30,20);
}

//skriver den i midten av firkanten, tviler på at det er riktig måte å gjøre det på
  private void drawFourPlayer(Graphics2D g) {
    RoundRectangle2D boxRect = this.getFourPlayerBoxRectangle();
    ColorTheme StartColor = new DefaultColorTheme();
    g.setColor(StartColor.getBoxColor());
    g.fill(boxRect);

    g.setFont(StartColor.getStartBackgroundFont());
    g.setColor(StartColor.getStartFontColor());
    Inf101Graphics.drawCenteredString(g, "4 player", boxRect.getX(), boxRect.getY(), boxRect.getWidth(), boxRect.getHeight());
 }
  private void drawThreePlayer(Graphics2D g) {
    RoundRectangle2D boxRect = this.getThreePlayerBoxRectangle();
    ColorTheme StartColor = new DefaultColorTheme();
    g.setColor(StartColor.getBoxColor());
    g.fill(boxRect);

    g.setFont(StartColor.getStartBackgroundFont());
    g.setColor(StartColor.getStartFontColor());

    Inf101Graphics.drawCenteredString(g, "3 player", boxRect.getX(), boxRect.getY(), boxRect.getWidth(), boxRect.getHeight());
}

private void drawTwoPlayer(Graphics2D g) {
  RoundRectangle2D boxRect = this.getTwoPlayerBoxRectangle();
  ColorTheme StartColor = new DefaultColorTheme();
  g.setColor(StartColor.getBoxColor());
  g.fill(boxRect);
  
  g.setFont(StartColor.getStartBackgroundFont());
  g.setColor(StartColor.getStartFontColor());
  Inf101Graphics.drawCenteredString(g, "2 player", boxRect.getX(), boxRect.getY(), boxRect.getWidth(), boxRect.getHeight());
}

private void drawStartGame(Graphics2D g) {
  RoundRectangle2D boxRect = this.getStartBoxRectangle();
  ColorTheme StartColor = new DefaultColorTheme();

  g.setColor(StartColor.getBoxColor());
  g.fill(boxRect);
  g.setFont(StartColor.getStartBackgroundFont());
  g.setColor(StartColor.getStartFontColor());
  Inf101Graphics.drawCenteredString(g, "Trykk her for å starte spillet", boxRect.getX(), boxRect.getY(), boxRect.getWidth(), boxRect.getHeight());
}
    
    
private void drawBoard(Graphics2D g) {
    Rectangle2D boardBox = this.getBoardRectangle();
    double scale = (boardBox.getHeight() - 1)/boardImage.getHeight();
    Inf101Graphics.drawImage(g, boardImage, boardBox.getX() + 1, boardBox.getY() + 1, scale);
    GridDimension dimension = view.getDimension();
    CellPositionToPixelConverter converter = new CellPositionToPixelConverter(boardBox, dimension, InnMargin);
    drawCells(g, view.getTilesOnBoard(), converter, colorTheme);
    drawCells(g, view.getPiece(), converter, colorTheme);
     
  }

  private Rectangle2D getBoardRectangle() {
    int boardWidth = (int) (getWidth() * 0.8); 
    int boardHeight = (int) (boardWidth * (boardImage.getHeight() / boardImage.getWidth()));
    int boardX = (getWidth() - boardWidth) / 2;
    int boardY = yMargin;
    return new Rectangle2D.Double(boardX, boardY, boardWidth, boardHeight);
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

    private void  drawEvent(Graphics2D g){
      Rectangle2D eventRect = this.getEventRectangle();
      BufferedImage eventImage = null;
      GameInfo gameInfo = view.getGameInfo();

      switch(gameInfo){
        case DEFAULT:
        eventImage = Inf101Graphics.loadImageFromResources("/boardPicture.jpeg");
          break;
        case LADDER:
        eventImage = Inf101Graphics.loadImageFromResources("/ladder.png");
        Inf101Graphics.drawCenteredString(g, "Du tråkket på en stige",getWidth(), getHeight()-55, this.getWidth() - getWidth() * 2,10);
          break;
        case SNAKE:
        eventImage = Inf101Graphics.loadImageFromResources("/snake.png");
        Inf101Graphics.drawCenteredString(g, "Du tråkket på en slange",getWidth(), getHeight()-55, this.getWidth() - getWidth() * 2,10);
          break;
        case STUMP:
        eventImage = Inf101Graphics.loadImageFromResources("/boot.png");
        Inf101Graphics.drawCenteredString(g, "Du ble tråkket på, du blir flyttet tilbake til start",getWidth(), getHeight()-55, this.getWidth() - getWidth() * 2,10);
          break;
        case WINNER:
        eventImage = Inf101Graphics.loadImageFromResources("/pokal.png");
        Inf101Graphics.drawCenteredString(g, "Spiller bla bla vant!",getWidth(), getHeight()-55, this.getWidth() - getWidth() * 2,10);
          break;
      }
      double scale = (eventRect.getHeight() - 1) / eventImage.getHeight();
      Inf101Graphics.drawImage(g, eventImage, eventRect.getX() + 1, eventRect.getY() + 1, scale);
    }
/**
 * 
 * @return a rectangle that is 15% of the width and height of the board, and 
 *  is centered on the board.
 */
    public Rectangle2D getEventRectangle() {
      double width = getWidth();
      double height = getHeight();
      double size = Math.min(width, height) * 0.15;
      double x = (width - size) / 2 - 70; 
      double y = height - size - 60;
      return new Rectangle2D.Double(x, y, size, size);
  }
      
    
     private void drawDice(Graphics2D g) {
      Rectangle2D diceRect = this.getDiceRectangle();
      BufferedImage diceImage = null;
      DiceState diceState = view.getDiceState();
      switch (diceState) {
        case ROLE:
          diceImage = Inf101Graphics.loadImageFromResources("/dice.png");
          Inf101Graphics.drawCenteredString(g, "Trykk på ternignen for å starte spillet ", getWidth(), getHeight()-40, this.getWidth() - getWidth() * 2,15);
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
    }  

  /**
 * 
 * @return a rectangle that is 15% of the width and height of the board, and 
 *  is centered on the board.
 */
    public Rectangle2D getDiceRectangle() {
      double width = getWidth();
      double height = getHeight();
      double size = Math.min(width, height) * 0.15;
      double x = (width - size) / 2 + 70;
      double y = height - size - 60;
      return new Rectangle2D.Double(x, y, size, size);
  }

  private void setupMousePositionUpdater() {
        // Keep the mousePosition variable up to date
        this.addMouseMotionListener(new MouseMotionAdapter() {
          @Override
          public void mouseMoved(MouseEvent e) {//dette er sus
            mouseIsInTheDice = getDiceRectangle().contains(e.getPoint());
            mouseIsInTheStart = getStartBoxRectangle().contains(e.getPoint());
            mouseIsInTheRectfour = getFourPlayerBoxRectangle().contains(e.getPoint());
            mouseIsInTheRecthree = getThreePlayerBoxRectangle().contains(e.getPoint());
            mouseIsInTheRectwo = getTwoPlayerBoxRectangle().contains(e.getPoint());
            updateCursor();
            repaint();
          }
        });
      }
    
      private void updateCursor() {
        if ((mouseIsInTheStart||mouseIsInTheRectfour||mouseIsInTheRecthree||mouseIsInTheRectwo) && view.getGamestate() == GameState.GameInActive) {
          setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        else if(view.getGamestate() == GameState.GameActive && mouseIsInTheDice){
          setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
          setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
      }
}
