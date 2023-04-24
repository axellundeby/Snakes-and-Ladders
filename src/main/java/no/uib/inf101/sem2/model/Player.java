package no.uib.inf101.sem2.model;
import no.uib.inf101.sem2.grid.CellPosition;


public final class Player {
    private final char PlayerID;
    private final CellPosition pos;
    
    public Player(char PlayerID, CellPosition pos) {
        this.PlayerID = PlayerID;
        this.pos = pos;
    }

    /**
        Returns a new Player object that is shifted by a given number of rows and columns.
        @param deltaRow the number of rows to shift by.
        @param deltaCol the number of columns to shift by.
        @return a new Player object that is shifted by the given deltaRow and deltaCol values.
    */
    public Player shiftedBy(int deltaRow, int deltaCol) {
        CellPosition pos = new CellPosition(this.pos.row() + deltaRow, this.pos.col() + deltaCol);
        Player ShapeCopy = new Player(PlayerID, pos);
        return ShapeCopy;
    }

    /**
    @return the ID of the player as a char.
    */
    public char getPlayerID() {
        return PlayerID;
    }

    /**
    @return the position of a player.
    */
    public CellPosition getPos() {
        return pos;
    }
    
    /**
    Returns a new Player object with a new position.
    @param newRow the new row position.
    @param newCol the new column position.
    @return a new Player object with the new position.
    */
    public Player setPos(int newRow, int newCol) {
        CellPosition pos = new CellPosition(newRow, newCol);
        Player ShapeCopy = new Player(PlayerID, pos);
        return ShapeCopy;
    }
}
