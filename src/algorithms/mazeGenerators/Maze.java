package algorithms.mazeGenerators;

import java.io.Serializable;

/**
 * This class represents a maze.
 */
public class  Maze implements Serializable{
    //This instance of 2D array will represent our maze
    private int[][] maze;//The two dimensional array (zero's and one's)
    private Position start;//The starting position
    private Position goal;//The goal

    //This is the constructor of maze

    /**
     * The constructor of the maze
     *
     * @param maze  - The two dimensional array (zero's and one's)
     * @param start - The starting position
     * @param goal  - The goal
     */
    //private  Maze(int[][] maze,Position start,Position goal){
    public Maze(int[][] maze, Position start, Position goal) {
        this.start = start;
        this.goal = goal;
        //we take the length of the rows and columns in order to do deep coppy
        int column = maze[0].length;
        int row = maze.length;
        //we do deep copy
        int[][] MyMaze = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                MyMaze[i][j] = maze[i][j];
            }
        }

        this.maze = MyMaze;
    }

    /**
     * The constructor of the maze
     * @param MyMaze - A byte array that represents the maze
     */
    public Maze(byte[] MyMaze) {

        //Finding the number of rows in the maze
        int numCountRows=FromBinaryToInt(MyMaze[0]);
        int numOfRows=0;
        for(int i=0;i<numCountRows;i++)
        {
            numOfRows=numOfRows+(int)Math.pow(Byte.MAX_VALUE+Math.abs(Byte.MIN_VALUE)-1,numCountRows-1-i)*FromBinaryToInt(MyMaze[i+1]);
        }

        //Finding the number of columns in the maze
        int numCountCols=FromBinaryToInt(MyMaze[numCountRows+1]);
        int numOfCols=0;
        for(int i=0;i<numCountRows;i++)
        {
            numOfCols=numOfCols+(int)Math.pow(Byte.MAX_VALUE+Math.abs(Byte.MIN_VALUE)-1,numCountCols-1-i)*FromBinaryToInt(MyMaze[numCountRows+2+i]);
        }

        //The binary maze
        this.maze=new int[numOfRows][numOfCols];

        //Creating the binary maze and the start/goal positions
        for(int i=0;i<this.maze.length;i++)
        {
            for(int j=0;j<this.maze[0].length;j++)
            {
                if(FromBinaryToInt(MyMaze[i*this.maze[0].length+j+numCountCols+numCountRows+2])==2)
                {
                    this.start=new Position(i,j);
                    this.maze[i][j]=0;
                }
                else
                {
                    if(FromBinaryToInt(MyMaze[i*this.maze[0].length+j+numCountCols+numCountRows+2])==3) {
                        this.goal = new Position(i, j);
                        this.maze[i][j] = 0;
                    }
                    else
                        this.maze[i][j]=FromBinaryToInt(MyMaze[i*this.maze[0].length+j+numCountCols+numCountRows+2]);
                }

            }
        }



    }

    /**
     * This function converts signed binary number into a number (unsigned binary)
     * @param num - The number
     * @return
     */
    private  int FromBinaryToInt(byte num)
    {
        if(num<0)
            return 128 + (128-(num*-1));
        return (int)(num);
    }
    /**
     * This function will return the starting position (deep copy)
     *
     * @return - will return the starting position (deep copy)
     */
    public Position getStartPosition() {
        return new Position(start.GetRowIndex(), start.GetColumnIndex());
    }

    /**
     * This function will return the goal's position (deep copy)
     *
     * @return - will return the goal's position (deep copy)
     */
    public Position getGoalPosition() {
        return new Position(goal.GetRowIndex(), goal.GetColumnIndex());
    }

    /**
     * this function will return true if there is a pass in a certain position
     *
     * @param row-    the number of row of the position that requested
     * @param column- the number of column of the position that requested
     * @return
     */
    public boolean ifThereIsAPass(int row, int column) {
        if (maze[row][column] == 0) {
            return true;
        }
        return false;
    }

    /**
     * a getter
     *
     * @return number of rows in the maze
     */
    public int getNumOfRows() {
        return maze.length;
    }

    /**
     * a getter
     *
     * @return number of columns in the maze
     */
    public int getNumOfColumns() {
        return maze[0].length;
    }

    /**
     * This function will print the maze
     */
    public void print() {
        //we take the length of the rows and columns in order to go over all the 2d array in order to print it all
        int column = maze[0].length;
        int row = maze.length;
        //we go over all the array and print it
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                //we check if the current square is the start position
                if (j == start.GetColumnIndex() && i == start.GetRowIndex()) {
                    System.out.print("S");
                } else {
                    //we check if the current square is the goal position
                    if (j == goal.GetColumnIndex() && i == goal.GetRowIndex()) {
                        System.out.print("E");
                    }
                    //if it is not the start or end position we print it as it is(wall or a pass)
                    else {
                        System.out.print(maze[i][j]);
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * This function receives a n imteger and converts it to an array of bytes that represents that number in the base of 254
     * @param num - The number
     * @return - The array of bytes
     */
    private byte [] getNumberInDifferentBase(int num,int base)
    {

        //Finding the number of digits that the new number will have
        int temp=num/ base;
        int counter=1;
        while(temp!=0)
        {
            temp=temp/base;
            counter++;
        }

        //The new number
        byte [] numberInBytes=new byte[counter];
        counter=0;
        //Assembling the digits of the new number
        while(num!=0)
        {

            numberInBytes[numberInBytes.length-counter-1]=(byte) (num%base);
            num=num/base;
            counter++;
        }

        return numberInBytes;
    }

    /**
     * This function will turn the binary maze into a byte array.(The start position is 2 and the goal position is 3)
     * @return - The binary array
     */
    private byte[] mazeToByteArray()
    {
        byte [] array=new byte[maze.length*maze[0].length];
        //Converting the maze into a long one dimensional array
        for(int i=0;i<array.length;i++)
        {
            array[i]=(byte)maze[i/maze[0].length][i%maze[0].length];
        }
        //Updating the start/goal positions in the new array
        array[this.start.GetRowIndex()*maze[0].length+this.getStartPosition().GetColumnIndex()]=2;
        array[this.goal.GetRowIndex()*maze[0].length+this.getGoalPosition().GetColumnIndex()]=3;

        return array;

    }

    /**
     * This function will convert the maze into a binary array with all the necessary data
     * @return - The binary array
     */
    public byte[] toByteArray() {
        //We are assuming that the size of the maze cannot be bigger than 256^256 X 256^256

        //The number of bytes to represent the size of the maze
        byte[] num_of_bytes_to_row = getNumberInDifferentBase(maze.length,Byte.MAX_VALUE+Math.abs(Byte.MIN_VALUE));
        byte[] num_of_bytes_to_col = getNumberInDifferentBase(maze[0].length,Byte.MAX_VALUE+Math.abs(Byte.MIN_VALUE));

        //The binary maze in bytes
        byte [] mazeInByte=mazeToByteArray();

        //The maze to be
        byte [] theMaze=new byte[num_of_bytes_to_col.length+num_of_bytes_to_row.length+mazeInByte.length+2];

        //Assembling all the data

        theMaze[0]=(byte)num_of_bytes_to_row.length;
        for(int i=0;i<num_of_bytes_to_row.length;i++)
        {
                theMaze[i+1]=num_of_bytes_to_row[i];
        }
        theMaze[num_of_bytes_to_row.length+1]=(byte)num_of_bytes_to_col.length;
        for(int i=0;i<num_of_bytes_to_col.length;i++)
        {
            theMaze[num_of_bytes_to_row.length+2+i]=num_of_bytes_to_col[i];
        }



        for(int i=0;i<mazeInByte.length;i++)
        {
            theMaze[num_of_bytes_to_col.length+num_of_bytes_to_row.length+2+i]=mazeInByte[i];
        }

        return  theMaze;

    }
}
