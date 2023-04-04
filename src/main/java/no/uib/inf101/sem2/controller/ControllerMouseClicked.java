package no.uib.inf101.sem2.controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import no.uib.inf101.sem2.model.Playermodel;
import no.uib.inf101.sem2.model.RandomThrow;
import no.uib.inf101.sem2.view.GridView;

public class ControllerMouseClicked implements MouseListener {
  private final Playermodel model;
  private final GridView view;
  private RandomThrow randomThrow = new RandomThrow();

  public ControllerMouseClicked(Playermodel model, GridView view) {
    this.model = model;
    this.view = view;
    this.view.addMouseListener(this);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if(view.getDiceRectangle().contains(e.getPoint())){
      //model.rollDice();
      int eyes = randomThrow.rollDice();
      model.updateDiceNumber(eyes);
      model.movePlayer(eyes);
    }
    view.repaint();
  }

  @Override 
public void mousePressed(MouseEvent e) { //når musen trykkes innenfor view.dice
  /* ignore */ }
  @Override public void mouseReleased(MouseEvent e) { /* ignore */ }
  @Override 
  public void mouseEntered(MouseEvent e) { //når musen er innenfor view.dice
    /* ignore */ }
  @Override public void mouseExited(MouseEvent e) { /* ignore */ }

}
