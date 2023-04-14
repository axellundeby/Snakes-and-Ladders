package no.uib.inf101.sem2.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;

public final class Player implements Iterable<GridCell<Character>> {
    private final char c;
    private CellPosition pos;
    
    public Player(char PlayerID, CellPosition pos) {
        this.c = PlayerID;
        this.pos = pos;
    }

    // public Player(char c) {
    //     this(c,new CellPosition(9, 0));
    // }

   
    public Player shiftedBy(int deltaRow, int deltaCol) {
        CellPosition pos = new CellPosition(this.pos.row() + deltaRow, this.pos.col() + deltaCol);
        Player ShapeCopy = new Player(c, pos);
        return ShapeCopy;
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        ArrayList<GridCell<Character>> shapePosList = new ArrayList<>();

        CellPosition cellPos = new CellPosition(this.pos.row(), this.pos.col());
        GridCell<Character> cell = new GridCell<>(cellPos, c);
        shapePosList.add(cell);

        return shapePosList.iterator();
    }

    @Override
    public int hashCode() {
        return Objects.hash(c, pos);
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
        return this.c == other.c && this.pos.equals(other.pos);
    }

    public CellPosition getPos() {
        return pos;
    }
    public void setPos(CellPosition pos) {
        this.pos = pos;
    }
}
