package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

/**
 * This class is the implementation of the Generate maze strategy
 */
public class ServerStrategyGenerateMaze implements IServerStrategy {


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

            //The size of the Maze
            int [] mazeSize=(int []) fromClient.readObject();

            if(mazeSize.length!=2)
            {
                throw new NumberFormatException(String.format("The size of the maze is &d", mazeSize.length));
            }
            //Generating the maze
            MyMazeGenerator mg=new MyMazeGenerator();
            Maze maze=mg.generate(mazeSize[0],mazeSize[1]);
           // maze.print();
            //System.out.println();
            OutputStream toClient=new MyCompressorOutputStream(outToClient);
            //Sending the maze via the compression output stream
            byte[] mazeByte=maze.toByteArray();
            toClient.write(mazeByte);
            toClient.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
    }
}
