package no.uib.inf101.sem2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;

import no.uib.inf101.sem2.controller.midi.GameSong;
import no.uib.inf101.sem2.model.GameInfo;
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
  private GameState gameState = GameState.GameActive;
  private GameInfo gameInfo = GameInfo.DEFAULT;
  private final GameSong song = new GameSong();
  
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
      gameInfo = GameInfo.DEFAULT;//når kastet setter ikke enumet seg til riktig verdi
      int eyes = randomThrow.rollDice();
      model.updateDiceNumber(eyes);
      //neste spiller sin tur når en terning er rullet
      diceEyesToAnimate = eyes;
      gameState = GameState.ANIMATE;
      animationTimer.start();
    }
    view.repaint();
  }

  private void animateSingleStep() {
    if (diceEyesToAnimate > 0) {
      model.PlayerJump();
      diceEyesToAnimate--;
    } else {
      model.SteppedOnSnake();
      model.SteppedOnLadder();
      model.Winner();
      model.PlayerAppear(); 
      model.turn();
      //model.stumpPlayer();//noe gæli her
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
