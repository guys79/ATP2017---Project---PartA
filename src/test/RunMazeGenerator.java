package test;

import algorithms.mazeGenerators.*;
import algorithms.search.DepthFirstSearch;
import algorithms.search.SearchableMaze;

/**
 * Created by Aviadjo on 3/22/2017.
 */
public class RunMazeGenerator {
    public static void main(String[] args) {
        SimpleMazeGenerator s= new SimpleMazeGenerator();
        Maze maze= s.generate(4,4);
        DepthFirstSearch d= new DepthFirstSearch();
        SearchableMaze m= new SearchableMaze(maze);
        maze.print();
        d.solve(m);
        System.out.println(d.getNumberOfNodesEvaluated());
        System.out.println();
       // testMazeGenerator(new SimpleMazeGenerator());
        //testMazeGenerator(new MyMazeGenerator());
    }

    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(15/*rows*/,15/*columns*/)));
        // generate another maze
        Maze maze = mazeGenerator.generate(15/*rows*/, 15/*columns*/);

        // prints the maze
        maze.print();

        // get the maze entrance
        Position startPosition = maze.getStartPosition();

        // print the position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"

        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}