package test;

import algorithms.mazeGenerators.*;

/**
 * Created by Aviadjo on 3/22/2017.
 */
public class RunMazeGenerator {
    public static void main(String[] args) {
        testMazeGenerator(new SimpleMazeGenerator());
       // testMazeGenerator(new MyMazeGenerator());
    }

    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {

        //System.out.println("1");
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(5/*rows*/,5/*columns*/)));
        //System.out.println("2");
        // generate another maze
        Maze maze = mazeGenerator.generate(5/*rows*/, 5/*columns*/);
        //System.out.println("3");

        // prints the maze
        maze.print();
        //System.out.println("4");
        // get the maze entrance
        Position startPosition = maze.getStartPosition();
        //System.out.println("5");
        // print the position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
        //System.out.println("1");
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
        //System.out.println("1");
    }
}