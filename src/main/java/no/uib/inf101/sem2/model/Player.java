package no.uib.inf101.sem2.model;
import no.uib.inf101.sem2.grid.CellPosition;


public final class Player {
    private final char PlayerID;
    private CellPosition pos;
    
    public Player(char PlayerID, CellPosition pos) {
        this.PlayerID = PlayerID;
        this.pos = pos;
    }
  
    public Player shiftedBy(int deltaRow, int deltaCol) {
        CellPosition pos = new CellPosition(this.pos.row() + deltaRow, this.pos.col() + deltaCol);
        Player ShapeCopy = new Player(PlayerID, pos);
        return ShapeCopy;
    }

    public char getPlayerID() {
        return PlayerID;
    }


    public CellPosition getPos() {
        return pos;
    }

    public Player setPos(int newRow,int newCol) {
        CellPosition pos = new CellPosition(newRow, newCol);
        Player ShapeCopy = new Player(PlayerID, pos);
        return ShapeCopy;
    }
}
