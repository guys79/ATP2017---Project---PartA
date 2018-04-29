package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.*;

import java.util.ArrayList;

/**
 * Created by Aviadjo on 3/22/2017.
 */
public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(2, 1);
        /*int [][]a={
                {0,0,0,0,0,1,0,1,0,0},
                {1,1,1,1,0,1,0,1,0,1},
                {0,0,0,0,0,0,0,0,0,0},
                {0,1,0,1,0,1,0,1,0,1},
                {1,0,0,0,1,0,0,0,1,1},
                {0,1,0,1,0,1,0,1,0,1},
                {0,0,0,1,0,0,0,0,0,0},
                {0,1,0,1,0,1,0,1,0,1},
                {0,1,0,0,1,0,0,0,1,1},
                {0,1,0,1,1,1,0,1,1,1}};
        Position start=new Position(2,6);
        Position goal=new Position(9,6);*/
        //Maze maze=new Maze(a,start,goal);
        SearchableMaze searchableMaze = new SearchableMaze(maze);

        maze.print();
        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
    }
}