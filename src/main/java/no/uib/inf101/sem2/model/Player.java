package no.uib.inf101.sem2.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import javax.swing.JPanel;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

import java.awt.Graphics;
import java.awt.Color;



public final class Player implements  Iterable<GridCell<JPanel>>{
    private final char c;
    private final JPanel shape;
    private final CellPosition pos;
//deffiner en spiller på en annen måte, en sirkel med en farge, hvordan kan jeg gjøre om booolean[][] 
//til en sirkel inni i et grid
    

    public Player(char c, JPanel shape, CellPosition pos ){
        this.c=c;
        this.shape=shape;
        this.pos=pos;
    }
   
    public static Player newCirclePlayer(char c, int row, int col){
        JPanel circleShape = new CirclePanel();
        CellPosition pos = new CellPosition(row, col);
        Player symbol = new Player(c, circleShape, pos);
        return symbol; 
    }
            
    public Player shiftedBy(int deltaRow , int deltaCol){
        CellPosition pos =  new CellPosition(this.pos.row() + deltaRow, this.pos.col() +  deltaCol);
        Player ShapeCopy = new Player(c, shape, pos);
        return ShapeCopy;
    }


    public Player spawnPlayer(GridDimension Dimension){
        CellPosition pos = new CellPosition(9, 0);
    
        Player CenterdShapeCopy = new Player(c, shape, pos);
        return CenterdShapeCopy;
    }


    @Override
    public Iterator<GridCell<JPanel>> iterator() {
        ArrayList<GridCell<JPanel>> ShapePosList = new ArrayList<GridCell<JPanel>>();
        GridCell<JPanel> cell = new GridCell<JPanel>(pos, shape);
        ShapePosList.add(cell);
        return ShapePosList.iterator();
    }
   
    @Override
    public int hashCode(){
        return Objects.hash(this.c, this.shape, this.pos);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Player)) {
            return false;
        }
        Player other = (Player) obj;
        return this.c == other.c && this.shape.equals(other.shape) &&  this.pos.equals(other.pos);
    }

      private static class CirclePanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillOval(0, 0, this.getWidth(), this.getHeight());
        }
    }
    
}