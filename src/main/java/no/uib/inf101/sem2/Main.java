package no.uib.inf101.sem2;


import no.uib.inf101.sem2.controller.ControllerMouseClicked;
import no.uib.inf101.sem2.model.Board;
import no.uib.inf101.sem2.model.PlayerFactory;
import no.uib.inf101.sem2.model.Playermodel;
import no.uib.inf101.sem2.model.StartPlayer;
import no.uib.inf101.sem2.view.GridView;

import javax.swing.JFrame;

public class Main {
  public static final String WINDOW_TITLE = "INF101 Snake and Ladders";
  
  public static void main(String[] args) {
    Board board = new Board(10,10); 
    PlayerFactory player = new StartPlayer();
    Playermodel model = new Playermodel(board, player, player.getNext());
    GridView view = new GridView(model);
    new ControllerMouseClicked(model, view);
  
    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
  }  
}
