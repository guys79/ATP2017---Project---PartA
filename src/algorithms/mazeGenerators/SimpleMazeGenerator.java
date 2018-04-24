package algorithms.mazeGenerators;

/**
 * This class represents a simple maze generator. Which means, a Maze generator that creates random mazes
 */
public class SimpleMazeGenerator extends AMazeGenerator {
    /**
     * This constructor
     */
    public SimpleMazeGenerator(){

    }

    /**
     * This function gives me a position that could be the start position or goal position randomly
     * @param column - Number of columns
     * @param row - Number of rows
     * @return - The new position
     */
    private Position getStartEndPositions(int column, int row){
       int col= (int )(Math.random() * column);
       int row2= (int )(Math.random() * row);
     return new Position(row2, col);
    }

    private void createMazeHelper(Position start,Position goal,int[][] myMaze){
        int SubtractionRows= (start.GetRowIndex()-goal.GetRowIndex());
        for(int i=0;i<SubtractionRows;i++){
            myMaze[start.GetRowIndex()+i+1][start.GetColumnIndex()]=2;
        }
        int SubtractionCol;
        if(start.GetColumnIndex()>goal.GetColumnIndex()) {
            SubtractionCol = start.GetRowIndex() - goal.GetRowIndex();
            for (int i = 0; i < SubtractionCol; i++) {
                myMaze[goal.GetRowIndex()][start.GetColumnIndex() - i - 1] = 2;
            }
        }
        else{
            SubtractionCol= (start.GetRowIndex()-goal.GetRowIndex())*-1;
            for(int i=0;i<SubtractionCol;i++){
                myMaze[goal.GetRowIndex()][start.GetColumnIndex()+i+1]=2;
            }
        }
    }

    /**
     * This function should make a pass in the maze
     * @param start _ The start position
     * @param goal - The goal's position
     * @param column  - number of columns
     * @param row - number of rows
     * @param myMaze - The array that represent the binary maze
     * @return - The corrected maze
     */
    private void makePass(Position start,Position goal, int column, int row, int[][] myMaze){
        //we make the start and goal positions as positions that you can pass through
        /*
        myMaze[start.GetRowIndex()][start.GetColumnIndex()]=0;
        myMaze[goal.GetRowIndex()][goal.GetColumnIndex()]=0;
        boolean found=false;
        int random=(int )(Math.random() * 4);
        int myRow= start.GetRowIndex();
        int myColumn=start.GetColumnIndex();
        //we start to make our way from the start position to the goal position
        while(!found) {
            //the random variable tells us where to go
            random = (int) (Math.random() * 4);
            //if we cant move to the place that the random variable told us to go to we take other random variable
            while (random == 0 && myRow == 0 || random == 1 && myRow == row - 1 || random == 2 && myColumn == 0 || random == 3 && myColumn == column - 1) {
                random = (int) (Math.random() * 4);
            }
            //if the random variable is 0 we go up
            if (random == 0) {
                myRow = myRow - 1;
            }
            //if the random variable is 1 we go down
            if (random == 1) {
                myRow = myRow + 1;
            }
            //if the random variable is 2 we go left
            if (random == 2) {
                myColumn = myColumn - 1;
            }
            //if the random variable is 3 we go right
            if (random == 3) {
                myColumn = myColumn + 1;
            }
            //wherever we go we mark this place as position that we can go through
            myMaze[myRow][myColumn] = 0;
            if (myRow == goal.GetRowIndex() && myColumn == goal.GetColumnIndex()) {
                found = true;
            }
        }
        return myMaze;
         */
           if(start.GetRowIndex()>goal.GetRowIndex()){
               createMazeHelper(start,goal,myMaze);
           }
           else{
               createMazeHelper(goal,start,myMaze);
           }
    }

    /**
     * This funcrtion will generate a new maze using Prim's algorithm
     * @param row - The number of rows
     * @param column - The number of columns
     * @return - This function will return a new random binary maze
     */
    public Maze generate(int row,int column) {
        //we get a start and goal position
        Position start= getStartEndPositions(column,row);
        Position goal= getStartEndPositions(column,row);
        //we make shore the positions are different
        while(goal.GetColumnIndex()==start.GetColumnIndex()&&start.GetRowIndex()==goal.GetRowIndex()){
           // System.out.println(goal.GetColumnIndex() +" , "+goal.GetRowIndex());
            goal=getStartEndPositions(column,row);

        }

        //we initialize the maze with walls only
        int[][] myMaze = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                myMaze[i][j] = 1;
                //System.out.print(j+i*column);
            }
        }

        //we make a pass through the maze
        makePass(start,goal,column,row,myMaze);

        //we put randomly places that you can go through
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if(myMaze[i][j]==1){
                    if((int )(Math.random() * 2 + 1)==1) {
                        myMaze[i][j] = 0;
                    }
                }
            }
        }
        /*
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if(myMaze[i][j]==2){
                    myMaze[i][j]=0;
                }
            }
        }
        */
        //we return the maze
        return new Maze(myMaze,start,goal);
    }
}
