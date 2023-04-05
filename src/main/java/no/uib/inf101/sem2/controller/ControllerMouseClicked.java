package no.uib.inf101.sem2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;
import no.uib.inf101.sem2.model.Playermodel;
import no.uib.inf101.sem2.model.RandomThrow;
import no.uib.inf101.sem2.view.GridView;

public class ControllerMouseClicked implements MouseListener {
  private final Playermodel model;
  private final GridView view;
  private RandomThrow randomThrow = new RandomThrow();
  private int diceEyesToAnimate = 0;
  private Timer animationTimer;
  private static final int ANIMATION_DELAY_MS = 100;
  private GameState gameState = GameState.PLAY;

  public ControllerMouseClicked(Playermodel model, GridView view) {
    this.model = model;
    this.view = view;
    this.view.addMouseListener(this);
    this.animationTimer = new Timer(ANIMATION_DELAY_MS, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        animateSingleStep();
      }
    });
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if(view.getDiceRectangle().contains(e.getPoint())){
      int eyes = randomThrow.rollDice();
      model.updateDiceNumber(eyes);
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
      view.repaint();
    } else {
      model.SteppedOnLadder();
      gameState = GameState.PLAY;
      animationTimer.stop();
    }
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

  private enum GameState {
    PLAY,
    ANIMATE
  }
}
