package Server;


import algorithms.mazeGenerators.Maze;

import algorithms.mazeGenerators.Position;
import algorithms.search.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is the implementation of the maze solution strategy
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy{

    /**
     *This function is the actual implementation of the strategy
     * @param inFromClient - The input stream
     * @param outToClient - The output stream
     */
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {

        try {
            //Creating the I/O streams
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient=new ObjectOutputStream(outToClient);
            toClient.flush();

            //The expected maze
            Maze maze=(Maze) fromClient.readObject();
            Solution solution=null;


            //The Location of the file
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            //The name of the file is a combination of the number of rows, number of columns, start Position,end Position, and index (we will add it later)
            String filename=maze.getNumOfRows()+"_"+maze.getNumOfColumns()+"_"+maze.getStartPosition().GetRowIndex()+"_"+maze.getStartPosition().GetColumnIndex()+
                    "_"+maze.getGoalPosition().GetRowIndex()+"_"+maze.getGoalPosition().GetColumnIndex();
            String filePath=tempDirectoryPath+filename;

            //The counter of the files
            int index=0;
            //This function compresses the maze into a string (the maze itself... without the start/goal positions or the size of the maze)
            String mazeString=maze.convertMazeToString();
            //the file
            File file;
            boolean flag=true;//If The solution exists
            while(flag)
            {
                //Creating the file (with the new name - including the counter)
                index++;
                file = new File(filePath+"_"+index+".txt");


                //If there is such a file
                if(file.exists() && !file.isDirectory())
                {
                    //If this file is the solution for this maze
                    if(this.checkIfFileExist(file,mazeString))
                    {
                        //Get the solution from the file and return it
                        solution= this.SolutionExists(file);
                        break;
                    }
                }
                //If there is no such file
                else
                {
                    flag=false;
                }


            }
            //If there is no such file then we have to add the file
            if(!flag)
            {
                //Solve the maze and put it's solution in the file
                solution=solutionDoesNotExists(maze,filePath+"_"+index+".txt");
            }

            //Getting the arrayList of the solution (without the neighbors - The neighbors list of every state is null. avoids stackOverFlow)
            ArrayList<AState>list=solution.getSolutionPath();
            ArrayList<AState> mazeList=new ArrayList<>();
            for(int i=0;i<list.size();i++)
            {
                mazeList.add(new MazeState((MazeState)list.get(i)));
            }

            //Creating the modified solution
            solution=new Solution(mazeList);

            //Sending the solution of the maze via the output stream
            toClient.writeObject(solution);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function will extract the solution of the given maze from the right file and return it
     * @param file - The file in which the solution is located
     * @return - The solution
     */
    private Solution SolutionExists(File file)
    {
        //The list that will become the solution
        ArrayList<AState> list=new ArrayList<>();
        String str="";

        //Reading the solution from the file
        try {
            Scanner sc = new Scanner(file);
            sc.nextLine();
            str=sc.nextLine();
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        int index=-1;
        String row="";
        String col="";
        //Extracting the solution from the
        while(index!=str.length()-1)
        {
            index=str.indexOf(",");
            row=str.substring(0,index);
            str=str.substring(index+1);
            index=str.indexOf(",");
            col=str.substring(0,index);
            list.add(new MazeState(new Position(this.ConvertFromStringToInt(row),this.ConvertFromStringToInt(col))));

            if(index!=str.length()-1)
                str=str.substring(index+1);
        }

        //Creating and returning th solution
        return new Solution(list);
    }

    /**
     * This function will recieve a string that represents a number and will return the number in int
     * @param str - The given string
     * @return - The number that the string represents
     */
    private int ConvertFromStringToInt(String str)
    {
        int num=0;
        for(int i=0;i<str.length();i++)
        {
            num=num*10+(str.charAt(i)-'0');
        }
        return num;
    }

    /**
     * This function will check if a current file is has the solution of the current maze
     * @param file - The given file
     * @param maze - The given maze represented in string
     * @return - True if the file is the file that holds the solution to the maze
     */
    private boolean checkIfFileExist(File file,String maze)
    {
        String str="";
        //Read the first line (has the representation of the maze in string)
        try {
            Scanner sc = new Scanner(file);
            str=sc.nextLine();
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Compare between the strings
        return str.equals(maze);

    }

    /**
     * This function will create a file with the solution of the current maze
     * @param maze - The maze
     * @param filePath - The path of the file to be created
     * @return - The solution of the maze
     */
    private Solution solutionDoesNotExists(Maze maze,String filePath)
    {

        //Creating the tools to solve the maze
        BestFirstSearch bestFirstSearch = new BestFirstSearch();
        SearchableMaze searchableMaze = new SearchableMaze(maze);

        //Solving the maze
        Solution solution= bestFirstSearch.solve(searchableMaze);

        //Restoring the solution
        ArrayList<AState>list=solution.getSolutionPath();
        String str="";
        String str2="";
        for (int i=0; i<list.size();i++)
        {
            str2=list.get(i).toString();
            str+=str2.substring(1,str2.length()-1)+",";
        }




        //Putting the solution in the file
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filePath, "UTF-8");
            //The representation of the maze in string - we will it inorder to compare between two mazes
            writer.println(maze.convertMazeToString());
            writer.println(str);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return solution;
    }

}
