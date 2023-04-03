package no.uib.inf101.sem2.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

public final class Player implements  Iterable<GridCell<Character>>{
    private final char c;
    private final CellPosition pos;
    static final char p = 'p';

    public Player(char c, CellPosition pos){
        this.c=c;
        this.pos=pos;
    }
            
    public Player shiftedBy(int deltaRow , int deltaCol){
        CellPosition pos =  new CellPosition(this.pos.row() + deltaRow, this.pos.col() +  deltaCol);
        Player ShapeCopy = new Player(c, pos);
        return ShapeCopy;
    }


    public Player spawnPlayer(GridDimension Dimension){
        CellPosition pos = new CellPosition(9, 0);
        Player StartShapeCopy = new Player(c, pos);
        return StartShapeCopy;
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        ArrayList<GridCell<Character>> PosList = new ArrayList<GridCell<Character>>();
        GridCell<Character> cell = new GridCell<Character>(pos, c);
        PosList.add(cell);
        return PosList.iterator();
    }
   
    @Override
    public int hashCode(){
        return Objects.hash(this.c, this.pos);
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
        return this.c == other.c &&  this.pos.equals(other.pos);
    } 
}