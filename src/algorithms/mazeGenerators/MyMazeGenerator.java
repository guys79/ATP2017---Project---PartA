package algorithms.mazeGenerators;



import java.util.ArrayList;
import java.util.List;

/**
 * This class will generate a maze using prim's algorithm
 */
public class MyMazeGenerator extends AMazeGenerator {
    /**
     * The constructor
     */
    public MyMazeGenerator()
    {

    }

    /**
     * This function will return if a certain cell is located near a zero or a two
     * @param array - The array (maze)
     * @param row - The row in which the cell is located
     * @param col - The column in which the cell is located
     * @return - This function will return "true" if the cell is next to more than one zero ot two
     */
    private boolean isNearZero(int [][] array,int row,int col)
    {
        int count=0;
        //The cell above
        if(row>0 && (array[row-1][col]==0||array[row-1][col]==2))
            count++;
        //The cell on the lest
        if(col>0 && (array[row][col-1]==0||array[row][col-1]==2))
            count++;
        //The cell below
        if(row<array.length-1&& (array[row+1][col]==0||array[row+1][col]==2))
            count++;
        //The cell on the right
        if(col<array[0].length-1&& (array[row][col+1]==0||array[row][col+1]==2))
            count++;
        return count>1;
    }

    /**
     * This function will choose a random cell in the list, and will check the adjacent cells
     * @param nodes - The list of cells
     * @param array - The array
     * @param emptySlots - The list of emptySlots
     */
    private void handleCurrentPosition(List<Position>nodes, int [][]array,List<Position>emptySlots)
    {
        //The size of the list
        int size = nodes.size();
        //A random index
        int index = (int) (size * Math.random());
        //The node in that index
        Position curr = nodes.get(index);
        //Updating the empty slots
        emptySlots.add(curr);
        //Removing the node from the list
        nodes.remove(index);
        array[curr.GetRowIndex()][curr.GetColumnIndex()] = 0;

        //The cell above
        if ((curr.GetRowIndex() != 0) && (array[curr.GetRowIndex() - 1][curr.GetColumnIndex()] == 1) && !isNearZero(array, curr.GetRowIndex() - 1, curr.GetColumnIndex())) {
            nodes.add(new Position(curr.GetRowIndex() - 1, curr.GetColumnIndex()));
            array[curr.GetRowIndex() - 1][curr.GetColumnIndex()] = 2;

        }
        //The cell on the left
        if ((curr.GetColumnIndex() != 0) && (array[curr.GetRowIndex()][curr.GetColumnIndex() - 1] == 1) && !isNearZero(array, curr.GetRowIndex(), curr.GetColumnIndex() - 1)) {
            nodes.add(new Position(curr.GetRowIndex(), curr.GetColumnIndex() - 1));
            array[curr.GetRowIndex()][curr.GetColumnIndex() - 1] = 2;
        }
        //The cell below
        if ((curr.GetRowIndex() != array.length - 1) && (array[curr.GetRowIndex() + 1][curr.GetColumnIndex()] == 1) && !isNearZero(array, curr.GetRowIndex() + 1, curr.GetColumnIndex())) {
            nodes.add(new Position(curr.GetRowIndex() + 1, curr.GetColumnIndex()));
            array[curr.GetRowIndex() + 1][curr.GetColumnIndex()] = 2;
        }
        //The cell on the right
        if ((curr.GetColumnIndex() != array[0].length - 1) && (array[curr.GetRowIndex()][curr.GetColumnIndex() + 1] == 1) && !isNearZero(array, curr.GetRowIndex(), curr.GetColumnIndex() + 1)) {
            nodes.add(new Position(curr.GetRowIndex(), curr.GetColumnIndex() + 1));
            array[curr.GetRowIndex()][curr.GetColumnIndex() + 1] = 2;
        }
    }

    /**
     * This function will receive a two dimensional array (consist of one's and zero's) and will change it to a binary maze using the Prims's algorithm
     *
     * @param array - The array the we receive
     * @param start - The position of the start
     * @return - The goal position
     */


    private Position settingArrayUsingPrim(int[][] array, Position start) {
        List<Position> nodes = new ArrayList<>();
        nodes.add(start);

        //Initializing the array
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = 1;
            }
        }
        array[start.GetRowIndex()][start.GetColumnIndex()] = 0;

        List<Position> emptySlots = new ArrayList<>();
        //Updating the cells in the array iteratively in order to create the maze
        while (nodes.size() != 0) {
            handleCurrentPosition(nodes,array,emptySlots);
        }


        //Choosing the goal
        //The size of the list
        int size = emptySlots.size();
        //A random index
        int index = (int) (size * Math.random());
        //The node in that index
        Position curr = emptySlots.get(index);
        while(curr.GetRowIndex()==start.GetRowIndex()&&curr.GetColumnIndex()==start.GetColumnIndex())
        {
            emptySlots.remove(curr);
            size--;
            index = (int) (size * Math.random());
            curr= emptySlots.get(index);
        }
        return curr;
    }

    /**
     * This function will generate a new maze using Prim's algorithm
     * @param row - The number of rows
     * @param column - The number of columns
     * @return - This function will return a new random binary maze
     */
    public Maze generate(int row, int column) {
        //If the size of the maze is invalid
        if(row<1 || column<1|| (row==1 && column==1))
        {
            return null;
        }
        //The array
        int [][]array=new int[row][column];
        //The start index
        int startRow=(int)(Math.random()*row);
        int startCol=(int)(Math.random()*column);

        //Creating the position for both start and goal
        Position start=new Position(startRow,startCol);

        //Creating the binary maze (The array of zero's and one's) and getting the goal position
        Position goal =settingArrayUsingPrim(array,start);
        //Creating the ne maze
        Maze maze =new Maze(array,start,goal);
        return maze;
    }
}
