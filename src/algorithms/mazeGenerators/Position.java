package algorithms.mazeGenerators;

/**
 * This class represents a position in the maze
 */
public class Position {
    private int row;//The index of the row
    private int column;//The index of the column

    /**
     * The constructor
     * @param row  - The index of the row
     * @param column - The index of the column
     */
    public Position(int row,int column){
        this.row=row;
        this.column=column;
    }

    /**
     * The copy constructor
     * @param pos - The given position
     */
    public Position(Position pos)
    {
        this(pos.row,pos.column);
    }

    /**
     * This function will return the index of the row
     * @return - The index of the row
     */
    public int GetRowIndex(){
        return row;
    }
    /**
     * This function will return the index of the column
     * @return - The index of the column
     */
    public int GetColumnIndex(){
        return column;
    }

    @Override
    /**
     * This function overrides the "toString()" method
     */
    public String toString(){
        //we override the printing in order to print an instance of position
        return "{"+row+","+column+"}";
    }
}
