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
        System.out.println("Server Strategy Solve maze!");
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
            String filename=maze.getNumOfRows()+"_"+maze.getNumOfColumns()+"_"+maze.getStartPosition().GetRowIndex()+"_"+maze.getStartPosition().GetColumnIndex()+
                    "_"+maze.getGoalPosition().GetRowIndex()+"_"+maze.getGoalPosition().GetColumnIndex();
            String filePath=tempDirectoryPath+filename;


            int counter=0;
            String mazeString=maze.convertMazeToString();
            //Creating the file
            File f;
            boolean flag=true;//If The solution exists
            while(flag)
            {
                counter++;
                f = new File(filePath+"_"+counter+".txt");

                System.out.println(filePath+"_"+counter+".txt");
                //If there is such a file
                if(f.exists() && !f.isDirectory())
                {
                    //If this file is the solution for this maze
                    if(this.checkIfFileExist(f,mazeString))
                    {
                        //Get the solution from the file and return it
                        solution= this.SolutionExists(f);
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
                solution=solutionDoesNotExists(maze,filePath+"_"+counter+".txt");
            }



            //Sending the solution of the maze via the output stream
            toClient.writeObject(solution);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private Solution SolutionExists(File file)
    {

        System.out.println("Exist");

        ArrayList<AState> list=new ArrayList<>();
        String str="";
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


        return new Solution(list);
    }

    private int ConvertFromStringToInt(String str)
    {
        int num=0;
        for(int i=0;i<str.length();i++)
        {
            num=num*10+(str.charAt(i)-'0');
        }
        return num;
    }
    private boolean checkIfFileExist(File file,String maze)
    {
        String str="";
        try {
            Scanner sc = new Scanner(file);
            str=sc.nextLine();
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return str.equals(maze);

    }
    private Solution solutionDoesNotExists(Maze maze,String filePath)
    {
        System.out.println("Dose not exist");
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
        System.out.println("The solution "+str);




        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filePath, "UTF-8");
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
