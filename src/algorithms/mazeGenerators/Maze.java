package algorithms.mazeGenerators;

public class Maze {
    //this instance of 2d array will represent our maze
    private int[][]maze;
    private Position start;
    private Position goal;
    //this is the constructor of maze
    Maze(int[][] maze,Position start,Position goal){
        this.start=start;
        this.goal=goal;
        //we take the length of the rows and columns in order to do deep coppy
        int column= maze[0].length;
        int row=maze.length;
        //we do deep coppy
        int[][]MyMaze=new int[row][column];
        for(int i=0; i<row;i++){
            for(int j=0;j<column;j++){
                MyMaze[i][j]= maze[i][j];
            }
        }

        this.maze=MyMaze;
    }
    public Position getStartPosition(){
        return new Position(start.GetColumnIndex(),start.GetRowIndex());
    }
    public Position getGoalPosition(){
        return new Position(goal.GetColumnIndex(),goal.GetRowIndex());
    }
    public void print(){
        //we take the length of the rows and columns in order to go over all the 2d array in order to print it all
        int column= maze[0].length;
        int row=maze.length;
        //we go over all the array and print it
        for(int i=0; i<row;i++){
            for(int j=0;j<column;j++){
                //we check if the current square is the start position
                if(column==start.GetColumnIndex()&&row==start.GetRowIndex()){
                    System.out.print("S");
                }
                else {
                    //we check if the current square is the goal position
                    if(column==goal.GetColumnIndex()&&row==goal.GetRowIndex()){
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
