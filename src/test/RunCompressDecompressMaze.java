package test;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.util.Arrays;


public class RunCompressDecompressMaze {
    public static void main(String[] args) {
        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        boolean flag=true;
        Maze maze = mazeGenerator.generate(100, 100); //Generate new maze
        byte savedMazeBytes[] = new byte[0];
        while(flag) {
             maze = mazeGenerator.generate(100, 100); //Generate new maze

            try {
                // save maze to a file
                OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
                out.write(maze.toByteArray());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                //read maze from file
                InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
                savedMazeBytes = new byte[maze.toByteArray().length];
                in.read(savedMazeBytes);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Maze loadedMaze = new Maze(savedMazeBytes);
            boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), maze.toByteArray());
           // System.out.println(String.format("Mazes equal: %s", areMazesEquals)); //maze should be equal to loadedMaze
            flag=areMazesEquals;

        }

        System.out.println("fuck");
        maze.print();
        Print(savedMazeBytes);
        Print(maze.toByteArray());

    }

    public static void Print(byte[]a)
    {
        for(int i=0;i<a.length;i++)
        {
            System.out.println(a[i]+" ");
        }
        System.out.println();
    }
}