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
    /*
    public static void main(String[] args) {
        AState a=new MazeState(new Position(1,1));
        AState b=new MazeState(new Position(1,1));
        AState c=new MazeState(new Position(1,1));
        AState d=new MazeState(new Position(1,1));
        AState f=new MazeState(new Position(1,1));
        AState g=new MazeState(new Position(1,1));
        AState h=new MazeState(new Position(1,1));
        AState i=new MazeState(new Position(1,1));
        AState e=new MazeState(new Position(1,1));
        a.addPossilbleState(b);
        a.addPossilbleState(c);

        b.addPossilbleState(d);
        b.addPossilbleState(e);
        b.addPossilbleState(a);

        c.addPossilbleState(a);
        c.addPossilbleState(e);
        c.addPossilbleState(i);

        d.addPossilbleState(b);
        d.addPossilbleState(g);
        d.addPossilbleState(h);

        e.addPossilbleState(b);
        e.addPossilbleState(c);
        e.addPossilbleState(g);

        f.addPossilbleState(i);
        f.addPossilbleState(g);
        f.addPossilbleState(h);

        g.addPossilbleState(d);
        g.addPossilbleState(e);
        g.addPossilbleState(f);
        g.addPossilbleState(h);

        h.addPossilbleState(d);
        h.addPossilbleState(g);
        h.addPossilbleState(f);

        i.addPossilbleState(f);
        i.addPossilbleState(c);

        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();

        SearchableMaze searchableMaze=new SearchableMaze(h,h);
        //System.out.println("1");
        Solution sol=breadthFirstSearch.solve(searchableMaze);
        //System.out.println("2");
        System.out.println(sol.getSolutionPath().get(0)==h);
        //System.out.println(sol.getSolutionPath().get(1)==h);
        //System.out.println(sol.getSolutionPath().get(2)==d);
        //System.out.println(sol.getSolutionPath().get(3)==h);

    }*/

    /*    IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(30, 30);
        SearchableMaze searchableMaze = new SearchableMaze(maze);

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
    }*/
}