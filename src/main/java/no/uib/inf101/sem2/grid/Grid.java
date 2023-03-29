package no.uib.inf101.sem2.grid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Grid<E> implements IGrid<E>  {
	private final int columns;
	private final int rows;
    
    private final List<E> cells;

    public Grid(int rows, int columns){//konstruktør
        this(rows,columns, null);
    }

    public Grid(int rows, int columns, E defaultvalue){//konstruktør
        if (rows <= 0 || columns <= 0) {
			throw new IllegalArgumentException();
		}
		this.columns = columns;
		this.rows = rows;
		this.cells = new ArrayList<>(columns * rows);
		for (int i = 0; i < columns * rows; ++i) {
			this.cells.add(defaultvalue);
		}
    }

    @Override
    public int rows() {
        return rows;
    }

    @Override
    public int cols() {
        return columns;
    }

    @Override
    public Iterator<GridCell<E>> iterator() {
        ArrayList<GridCell<E>> ElementList = new ArrayList<GridCell<E>>();//tom liste
        for (int i = 0; i < rows; i++) {//itererer gjennom rader
            for (int j = 0; j < columns; j++) {//itererer gjennom kolonner
                CellPosition pos = new CellPosition(i, j);//posisjonen til en celle
                E Element = get(pos);
                GridCell<E> ElementPos = new GridCell<E>(pos, Element);
                ElementList.add(ElementPos);
            }
        }
        return ElementList.iterator();
    }

    @Override
    public void set(CellPosition pos, E value) {
        if(!positionIsOnGrid(pos)){
            throw new IndexOutOfBoundsException("The position is not valid");
        }
        else{
            cells.set(locationToIndex(pos), value);
        }
    }

    @Override
    public E get(CellPosition pos) {
        if(!positionIsOnGrid(pos)){
            throw new IndexOutOfBoundsException("The position is not valid");
        }
        else{
            return cells.get(locationToIndex(pos));
        }
    }
    /**
     * Viser til en gitt celleposisjon til en indeks i den interne listen over celler.
     * 
     * @param pos en celleposisjon som skal vise til en indeks
     * @return indeksen til cellen som svarer til den gitte posisjonen
     * @throws IndexOutOfBoundsException hvis posisjonen er ugyldig (utenfor rutenettet)
     */
    private int locationToIndex(CellPosition pos) {
        int index = pos.row() * columns + pos.col();
        if (index < 0 || index >= cells.size()) {
            throw new IndexOutOfBoundsException("The given position is not valid for this grid");
        }
        return index;
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        return pos.row() >= 0 && pos.col() >= 0 && pos.row() < rows && pos.col() < columns;     
    }
    
}
