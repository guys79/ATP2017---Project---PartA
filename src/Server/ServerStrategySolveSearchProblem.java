package Server;


import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;

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

            //Creating the tools to solve the maze
            BestFirstSearch bestFirstSearch=new BestFirstSearch();
            SearchableMaze searchableMaze=new SearchableMaze(maze);

            //Solving the maze
            Solution solution=bestFirstSearch.solve(searchableMaze);


            //Sending the solution of the maze via the output stream
            toClient.writeObject(solution);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
