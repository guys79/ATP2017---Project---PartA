package test;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Aviadjo on 3/22/2017.
 */

public class RunSearchOnMaze {

    public static void main(String[] args) {
        /*SimpleMazeGenerator bla=new SimpleMazeGenerator();
        Maze maze= bla.generate(6,6);
        maze.print();
        SearchableMaze x= new SearchableMaze(maze);
        DepthFirstSearch W= new DepthFirstSearch();
        Solution s= W.solve(x);
        for (int i=0;i<s.getSolutionPath().size();i++){
            s.getSolutionPath().get(i).toString();
        }*/
        /*AState a,b,c,d,e,f;
        a=new MazeState(new Position(1,1),-1);
        b=new MazeState(new Position(1,2),-1);
        c=new MazeState(new Position(1,3),-1);
        d=new MazeState(new Position(1,4),-1);
        e=new MazeState(new Position(1,5),-1);
        PriorityQueue<AState>p=new PriorityQueue<>();
        p.add(a);
        p.add(b);
        p.add(c);
        p.add(d);
        p.add(e);
        while(p.size()!=0)
        {
            System.out.println(p.remove());
        }*/
      //  SearchableMaze searchableMaze=new SearchableMaze(new MazeState(new Position(1,1)),new MazeState(new Position(4,4)));
    // BestFirstSearch bestFirstSearch=new BestFirstSearch();
     //Solution sol=bestFirstSearch.solve()


        AState a=new MazeState(new Position(1,1),1);
        AState b=new MazeState(new Position(1,2),2);
        AState c=new MazeState(new Position(1,3),3);
        AState d=new MazeState(new Position(1,4),4);
        AState f=new MazeState(new Position(1,5),5);
        AState g=new MazeState(new Position(1,6),6);
        AState h=new MazeState(new Position(1,7),7);
        AState i=new MazeState(new Position(1,8),8);
        AState e=new MazeState(new Position(1,9),9);
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

       // BestFirstSearch breadthFirstSearch = new BestFirstSearch();
       // BreadthFirstSearch breadthFirstSearch=new BreadthFirstSearch();
        BestFirstSearch breadthFirstSearch=new BestFirstSearch();
        SearchableMaze searchableMaze=new SearchableMaze(a,h);
        Solution sol=breadthFirstSearch.solve(searchableMaze);
        for(int y=0; y<sol.getSolutionPath().size();y++)
        {
            System.out.println(sol.getSolutionPath().get(y));
        }
        //System.out.println("1");
     /*   System.out.println("a= "+a);
        System.out.println("b= "+b);
        System.out.println("c= "+c);
        System.out.println("d= "+d);
        System.out.println("e= "+e);
        System.out.println("f= "+f);
        System.out.println("g= "+g);
        System.out.println("h= "+h);
        System.out.println("i= "+i);
       Solution sol=breadthFirstSearch.solve(searchableMaze);
        System.out.println(sol.getSolutionPath().size());
        System.out.println(sol.getSolutionPath().get(0)==a);
        System.out.println(sol.getSolutionPath().get(1)==b);
        System.out.println(sol.getSolutionPath().get(2)==d);
        System.out.println(sol.getSolutionPath().get(3)==h);
        System.out.println(breadthFirstSearch.getNumOfNodesEvaluated());
        MazeState mazeStatez=new MazeState(new Position(4,5));
        System.out.println(mazeStatez);
        */
    }
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