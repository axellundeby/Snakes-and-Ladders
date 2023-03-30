package no.uib.inf101.sem2.controller;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerMouseClicked implements MouseListener {
  private final diceController model;
  private final Component view;

  public ControllerMouseClicked(diceController model, Component view) {
    this.model = model;
    this.view = view;

    // Configure view for mouse input
    this.view.addMouseListener(this);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // når vi trykker på terning endres ternign, og vi får et tall mellom 1 og 6, kall på metoden i 
    //when the dice 
    this.model.setX(e.getX());
    this.model.setY(e.getY());
    this.view.repaint();
    ((diceController) view).rollDice();
  }

  @Override public void mousePressed(MouseEvent e) { /* ignore */ }
  @Override public void mouseReleased(MouseEvent e) { /* ignore */ }
  @Override public void mouseEntered(MouseEvent e) { /* ignore */ }
  @Override public void mouseExited(MouseEvent e) { /* ignore */ }

}
