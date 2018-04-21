package algorithms.mazeGenerators;

import javafx.geometry.Pos;

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
        //The cellon the lest
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
     */
    private void handleCurrentPosition(List<Position>nodes, int [][]array)
    {
        //The size of the list
        int size = nodes.size();
        //A random index
        int index = (int) (size * Math.random());
        //The node in that index
        Position curr = nodes.get(index);
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
     * @param goal - The position of the goal
     */


    private void settingArrayUsingPrim(int[][] array, Position start, Position goal) {
        List<Position> nodes = new ArrayList<>();
        nodes.add(start);

        //Initializing the array
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = 1;
            }
        }
        array[start.GetRowIndex()][start.GetColumnIndex()] = 0;


        //Updating the cells in the array iteratively in order to create the maze
        while (nodes.size() != 0) {
            handleCurrentPosition(nodes,array);
        }
        //The goal
        array[goal.GetRowIndex()][goal.GetColumnIndex()] = 0;


    }

    /**
     * This funcrtion will generate a new maze using Prim's algorithm
     * @param row - The number of rows
     * @param column - The number of columns
     * @return - This function will return a new random binary maze
     */
    public Maze generate(int row, int column) {
        //The array
        int [][]array=new int[row][column];
        //The start index
        int startRow=(int)(Math.random()*row);
        int startCol=(int)(Math.random()*column);
        //The goal index
        int goalRow=startRow;
        int goalCol=startCol;
        //Setting the goal's index to be different from the start's index
        while(goalCol==startCol && goalRow== goalCol)
        {
            goalRow=(int)(Math.random()*row);
            goalCol=(int)(Math.random()*column);
        }
        //Creating the position for both start and goal
        Position start=new Position(startRow,startCol);
        Position goal=new Position(goalRow,goalCol);
        //Creating the binary maze (The array of zero's and one's)
        settingArrayUsingPrim(array,start,goal);
        //Creating the ne maze
        Maze maze =new Maze(array,start,goal);
        return maze;
    }
}
