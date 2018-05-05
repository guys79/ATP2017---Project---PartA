package test;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

/**
 * Created by Aviadjo on 3/22/2017.
 */
public class RunMazeGenerator {
    public static void main(String[] args) {
        ISearchable searchable=null;
        Maze maze;
        int [][] binMaze={{0,1,1,1,1}
                ,{0,0,0,0,1}
                ,{0,1,1,0,1}
                ,{0,0,1,0,1}
                ,{0,0,0,0,0}};
        Position start=new Position(0,0);
        Position goal=new Position(4,4);
        maze=new Maze(binMaze,start,goal);
        searchable=new SearchableMaze(maze);
        BestFirstSearch bestFirstSearch=new BestFirstSearch();
        Solution sol=bestFirstSearch.solve(searchable);
        System.out.println(sol.getSolutionPath());
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