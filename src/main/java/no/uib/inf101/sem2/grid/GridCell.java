package no.uib.inf101.sem2.grid;

/**

 * A recordclass that represents a cell in a grid with a generic type E
 * @param pos the position of a given cell
  * @param value the value of a given cell
 */
public record GridCell<E>(CellPosition pos, E value) {
    
}
