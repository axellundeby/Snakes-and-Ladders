package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.Grid;

public class Board extends Grid<Character>{


    public Board(int rows, int columns) {
        super(rows, columns, '-');
    }

    /**
     * returns a string representation of the grid with tetrominos
     * @return a string representation of the current state of the grid.
     */
    public String prettyString(){
    StringBuilder sb = new StringBuilder();
    for (int row = 0; row < rows(); row++) {
        for (int col = 0; col < cols(); col++) {
            CellPosition pos = new CellPosition(row, col);
            sb.append(this.get(pos));
        }
        sb.append("\n");
    }
    return sb.toString().strip();
    }
}