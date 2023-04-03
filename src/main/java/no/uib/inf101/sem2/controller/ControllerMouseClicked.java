package no.uib.inf101.sem2.controller;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import no.uib.inf101.sem2.model.Playermodel;

public class ControllerMouseClicked implements MouseListener {
  private final diceController model;
  private final Component view;

  public ControllerMouseClicked(Playermodel model, Component view) {
    this.model = model;
    this.view = view;
    //skjlnner ikke terning
    // Configure view for mouse input
    this.view.addMouseListener(this);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (mouseEntered(e)){
      model.rollDice();
      //noe repaint greier
    }
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
