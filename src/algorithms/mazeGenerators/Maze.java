package algorithms.mazeGenerators;

/**
 * This class represents a maze
 */
public class Maze {
    //This instance of 2D array will represent our maze
    private int[][]maze;//The two dimensional array (zero's and one's)
    private Position start;//The starting position
    private Position goal;//The goal

    //This is the constructor of maze

    /**The constructor of the maze
     *
     * @param maze - The two dimensional array (zero's and one's)
     * @param start - The starting position
     * @param goal - The goal
     */
    public  Maze(int[][] maze,Position start,Position goal){
        this.start=start;
        this.goal=goal;
        //we take the length of the rows and columns in order to do deep coppy
        int column= maze[0].length;
        int row=maze.length;
        //we do deep copy
        int[][]MyMaze=new int[row][column];
        for(int i=0; i<row;i++){
            for(int j=0;j<column;j++){
                MyMaze[i][j]= maze[i][j];
            }
        }

        this.maze=MyMaze;
    }

    /**
     * This function will return the starting position (deep copy)
     * @return - will return the starting position (deep copy)
     */
    public Position getStartPosition(){
        return new Position(start.GetRowIndex(),start.GetColumnIndex());
    }
    /**
     * This function will return the goal's position (deep copy)
     * @return - will return the goal's position (deep copy)
     */
    public Position getGoalPosition(){
        return new Position(goal.GetRowIndex(),goal.GetColumnIndex());
    }

    /**
     * this function will return true if there is a pass in a certain position
     * @param row- the number of row of the position that requested
     * @param column- the number of column of the position that requested
     * @return
     */
    public boolean ifThereIsAPass(int row,int column){
        if(maze[row][column]==0){
            return true;
        }
        return false;
    }

    /**
     * a getter
     * @return number of rows in the maze
     */
    public int getNumOfRows(){
        return maze.length;
    }
    /**
     * a getter
     * @return number of columns in the maze
     */
    public int getNumOfColumns(){
        return maze[0].length;
    }

    /**
     * This function will print the maze
     */
    public void print(){
        //we take the length of the rows and columns in order to go over all the 2d array in order to print it all
        int column= maze[0].length;
        int row=maze.length;
        //we go over all the array and print it
        for(int i=0; i<row;i++){
            for(int j=0;j<column;j++){
                //we check if the current square is the start position
                if(j==start.GetColumnIndex()&&i==start.GetRowIndex()){
                    System.out.print("S");
                }
                else {
                    //we check if the current square is the goal position
                    if(j==goal.GetColumnIndex()&&i==goal.GetRowIndex()){
                        System.out.print("E");
                    }
                    //if it is not the start or end position we print it as it is(wall or a pass)
                    else{
                        System.out.print(maze[i][j]);
                    }
                }
            }
            System.out.println();
        }
    }
}
