package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;


public class MyMazeGenerator {
    public MyMazeGenerator()
    {

    }

    private boolean isNearZero(int [][] array,int row,int col)
    {
        int count=0;
        if(row>0 && (array[row-1][col]==0||array[row-1][col]==2))
            count++;
        if(col>0 && (array[row][col-1]==0||array[row][col-1]==2))
            count++;
        if(row<array.length-1&& (array[row+1][col]==0||array[row+1][col]==2))
            count++;
        if(col<array[0].length-1&& (array[row][col+1]==0||array[row][col+1]==2))
            count++;
        return count>1;
    }
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
        array[curr.getX()][curr.getY()] = 0;
        if ((curr.getX() != 0) && (array[curr.getX() - 1][curr.getY()] == 1) && !isNearZero(array, curr.getX() - 1, curr.getY())) {
            nodes.add(new Position(curr.getX() - 1, curr.getY()));
            array[curr.getX() - 1][curr.getY()] = 2;

        }
        if ((curr.getY() != 0) && (array[curr.getX()][curr.getY() - 1] == 1) && !isNearZero(array, curr.getX(), curr.getY() - 1)) {
            nodes.add(new Position(curr.getX(), curr.getY() - 1));
            array[curr.getX()][curr.getY() - 1] = 2;
        }
        if ((curr.getX() != array.length - 1) && (array[curr.getX() + 1][curr.getY()] == 1) && !isNearZero(array, curr.getX() + 1, curr.getY())) {
            nodes.add(new Position(curr.getX() + 1, curr.getY()));
            array[curr.getX() + 1][curr.getY()] = 2;
        }
        if ((curr.getY() != array[0].length - 1) && (array[curr.getX()][curr.getY() + 1] == 1) && !isNearZero(array, curr.getX(), curr.getY() + 1)) {
            nodes.add(new Position(curr.getX(), curr.getY() + 1));
            array[curr.getX()][curr.getY() + 1] = 2;
        }
    }
    /**
     * This function will receive a two dimensional array (consist of one's and zero's) and will change it to an array using the Prims's algorithm
     *
     * @param array - The array thta we recieve
     */

    public void settingArrayUsingBFS(int[][] array, Position start, Position goal) {
        List<Position> nodes = new ArrayList<>();
        nodes.add(start);

        //Initializing the array
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = 1;
            }
        }
        array[start.getX()][start.getY()] = 0;



        while (nodes.size() != 0) {
            handleCurrentPosition(nodes,array);
        }


    }

}
