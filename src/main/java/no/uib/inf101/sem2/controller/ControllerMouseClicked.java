package no.uib.inf101.sem2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;

import no.uib.inf101.sem2.controller.midi.GameSong;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.Playermodel;
import no.uib.inf101.sem2.model.RandomThrow;
import no.uib.inf101.sem2.view.GridView;

public class ControllerMouseClicked implements MouseListener {
  private final Playermodel model;
  private final GridView view;
  private RandomThrow randomThrow = new RandomThrow();
  private int diceEyesToAnimate = 0;
  private Timer animationTimer;
  private static final int DELAY = 200;
  private GameState gameState = GameState.GameInActive;
  private final GameSong song = new GameSong();
  private int eyes = 0;
  
  //hvorfor har jeg interface her?
  public ControllerMouseClicked(Playermodel model, GridView view) {
    this.model = model;
    this.view = view;
    this.view.addMouseListener(this);
    song.run();
    
    this.animationTimer = new Timer(DELAY, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        animateSingleStep();
      }
    });
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if(view.getDiceRectangle().contains(e.getPoint()) && gameState == GameState.GameActive){//når en vinner er kåret kan fortsatt terningen rulles, d er feil 
      model.updateGameinfo();
      eyes = randomThrow.rollDice();
      model.updateDiceNumber(eyes);
      diceEyesToAnimate = eyes;
      gameState = GameState.ANIMATE;
      animationTimer.start();
      view.repaint();
    }
    else if (gameState == GameState.GameInActive){
      if(view.getStartBoxRectangle().contains(e.getPoint())){
        model.updateGameState();
      }
      else if (view.getThreePlayerBoxRectangle().contains(e.getPoint())){
          model.setAmountOfPlayers(3);
      }
      else if (view.getFourPlayerBoxRectangle().contains(e.getPoint())){
          model.setAmountOfPlayers(4);
      }
      else if (view.getTwoPlayerBoxRectangle().contains(e.getPoint())){
          model.setAmountOfPlayers(2);
      }
    }
    view.repaint();
  }

  private void animateSingleStep() {
    if (diceEyesToAnimate > 0) {
      model.PlayerJump();
      if(model.playerOnNextTileChecker()){//dette går ikke
        diceEyesToAnimate--;
      }
      model.Winner();//feil her
      diceEyesToAnimate--;
    } else {
      model.SteppedOnSnake();
      model.SteppedOnLadder();
      model.PlayerAppear();
      if (eyes<6){
        model.PlayerTurn();
      }
      model.stumpPlayer();//noe gæli her
      gameState = GameState.GameActive;
      animationTimer.stop();
    }
    view.repaint();
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // ignore
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // ignore
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // ignore
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // ignore
  }

}
