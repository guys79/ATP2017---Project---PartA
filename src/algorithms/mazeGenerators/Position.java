package algorithms.mazeGenerators;

public class Position {
    private int row;
    private int column;
    Position(int row,int column){
        this.row=row;
        this.column=column;
    }
    public int GetRowIndex(){
        return row;
    }
    public int GetColumnIndex(){
        return column;
    }
    //we override the printing in order to print an instance of position
    public String toString(){
        return "{"+row+","+column+"}";
    }
}
