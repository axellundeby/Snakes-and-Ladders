package no.uib.inf101.sem2.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;

public final class Player implements Iterable<GridCell<Character>> {
    private final char c;
    private final String playerID;
    private CellPosition pos;

    static final String PLAYER_1 = "P";
    static final String PLAYER_2 = "Q";
    static final String PLAYER_3 = "B";

    public Player(char c, String playerID, CellPosition pos) {
        this.c = c;
        this.playerID = playerID;
        this.pos = pos;
    }

    public static Player newPlayer(char c) {
        CellPosition pos = new CellPosition(0, 0);

        String playerID = switch (c) {
            case 'P' -> PLAYER_1;
            case 'Q' -> PLAYER_2;
            case 'B' -> PLAYER_3;
            default -> throw new IllegalArgumentException(
                "Denne spilleren er ikke gyldig");
        };
        
        return new Player(c, playerID, pos);
    }

    public Player shiftedBy(int deltaRow, int deltaCol) {
        CellPosition pos = new CellPosition(this.pos.row() + deltaRow, this.pos.col() + deltaCol);
        Player ShapeCopy = new Player(c, playerID, pos);
        return ShapeCopy;
    }

    public Player spawnPlayer(GridDimension dimension) {
        CellPosition pos = new CellPosition(9, 0);
        return new Player(c, playerID, pos);
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
        return Objects.hash(c, playerID, pos);
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
        return this.c == other.c && this.playerID.equals(other.playerID) && this.pos.equals(other.pos);
    }

    public CellPosition getPos() {
        return pos;
    }
    public void setPos(CellPosition pos) {
        this.pos = pos;
    }

//løkke som dytter spillere inn i en liste, listen skal iterere for amountOfPlayers
    public Player getPlayer() {
    // for (int j = 0; j < 3; j++) {
        return new Player(c, playerID, pos);
    // }
} 
}
