package test;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Aviadjo on 3/26/2017.
 */
public class RunCompressDecompressMaze {
    public static void main(String[] args) {
        String mazeFileName = "savedMaze.txt";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
       // Maze maze = mazeGenerator.generate(5, 5); //Generate new maze
        int [][] binMaze={{ 0, 0, 0, 1, 1, 1, 0 },
                          { 0, 1, 0, 0, 0, 1, 0 },
                          { 0, 0, 0, 0, 1, 0, 1 },
                          { 0, 1, 1, 1, 0, 0, 0 },
                          { 0, 0, 0, 0, 0, 0, 1 },
                          { 0, 0, 0, 0, 0, 0, 1 },
                          { 1, 0, 1, 0, 0, 0, 0}};
        //000 111 00 1 000 1 0000G00 1 0 111 0000000S0 1 000000 11 0 1 0000
        // 3   3   2 1  3  1  4 -2 2 1 1  3      9     1     6   2 1 1  4
        Position start=new Position(4,4);
        Position goal=new Position(2,3);
       Maze maze=new Maze(binMaze,start,goal);

        try {
            // save maze to a file
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
            out.write(maze.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte savedMazeBytes[] = new byte[0];
        try {
            //read maze from file
            InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
            savedMazeBytes = new byte[maze.toByteArray().length];
            in.read(savedMazeBytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        maze.print();
        Maze loadedMaze = new Maze(savedMazeBytes);
        print(maze.toByteArray());
        System.out.println();
        loadedMaze.print();
        print(loadedMaze.toByteArray());

        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());
        System.out.println(String.format("Mazes equal: %s",areMazesEquals)); //maze should be equal to loadedMaze
    }
    public static void print(byte[]a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

        System.out.println();

    }
}