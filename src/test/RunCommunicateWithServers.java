package test;

import Client.Client;
import Client.IClientStrategy;
import IO.MyDecompressorInputStream;

import Server.*;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.Solution;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class RunCommunicateWithServers {
    public static void main(String[] args) {
        //Initializing servers
        Server mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        Server solveSearchProblemServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
        //Server stringReverserServer = new Server(5402, 1000, new ServerStrategyStringReverser());

        //Starting  servers
        solveSearchProblemServer.start();
        mazeGeneratingServer.start();
        //stringReverserServer.start();

        //Communicating with servers
        CommunicateWithServer_MazeGenerating();
        CommunicateWithServer_SolveSearchProblem();
        //CommunicateWithServer_StringReverser();

        //Stopping all servers
        mazeGeneratingServer.stop();
        solveSearchProblemServer.stop();
        //stringReverserServer.stop();
    }

    private static void CommunicateWithServer_MazeGenerating() {
        try {

            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {

                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);

                        toServer.flush();

                        int[] mazeDimensions = new int[]{10, 6};

                        toServer.writeObject(mazeDimensions); //send maze dimensions to server
                        toServer.flush();



                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        byte[] compressedMaze = (byte[]) fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server
                        InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                        byte[] decompressedMaze = new byte[10000000 /*CHANGE SIZE ACCORDING TO YOU MAZE SIZE*/]; //allocating byte[] for the decompressed maze -
                        is.read(decompressedMaze); //Fill decompressedMaze with bytes
                        Maze maze = new Maze(decompressedMaze);
                        maze.print();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            client.communicateWithServer();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void CommunicateWithServer_SolveSearchProblem() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5401, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {

                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        MyMazeGenerator mg = new MyMazeGenerator();
                        Maze maze = mg.generate(25, 25);
                        System.out.println(maze.convertMazeToString());
                   /*     Maze maze;
                        int [][] binMaze={{ 0, 0, 0, 1, 1, 1, 0 },// 0 0 0 1 1 1 0
                                          { 0, 1, 0, 0, 0, 1, 0 },// 0 1 0 0 0 1 0
                                          { 0, 0, 1, 0, 0, 0, 1 },// 0 0 1 E 0 0 1
                                          { 0, 1, 1, 1, 0, 0, 0 },// 0 1 1 1 0 0 0
                                          { 0, 0, 0, 0, 0, 0, 1 },// 0 0 0 0 S 0 1
                                          { 0, 0, 0, 0, 0, 0, 1 },// 0 0 0 0 0 0 1
                                          { 1, 0, 1, 0, 0, 0, 0}};// 1 0 1 0 0 0 0

                        Position start=new Position(4,4);
                        Position goal=new Position(2,3);
                        maze=new Maze(binMaze,start,goal);*/
                        maze.print();
                        toServer.writeObject(maze); //send maze to server
                        toServer.flush();

                        Solution mazeSolution = (Solution) fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server

                        //Print Maze Solution retrieved from the server
                        System.out.println(String.format("Solution steps: %s", mazeSolution));
                        ArrayList<AState> mazeSolutionSteps = mazeSolution.getSolutionPath();
                        for (int i = 0; i < mazeSolutionSteps.size(); i++) {
                            System.out.println(String.format("%s. %s", i, mazeSolutionSteps.get(i).toString()));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


}