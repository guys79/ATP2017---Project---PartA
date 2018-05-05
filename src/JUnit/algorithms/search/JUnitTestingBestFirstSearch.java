package algorithms.search;
import static org.junit.jupiter.api.Assertions.*;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;


import java.util.ArrayList;
import java.util.List;



class JUnitTestingBestFirstSearch {

    @org.junit.jupiter.api.Test
    void getName() {
        BestFirstSearch bestFirstSearch=new BestFirstSearch();
        assertEquals("Best First Search", bestFirstSearch.getName());
    }
    @org.junit.jupiter.api.Test
    void solveNull(){
        IMazeGenerator iMazeGenerator=new MyMazeGenerator();
        Maze maze=iMazeGenerator.generate(20,20);
        ISearchable searchable=null;
        BestFirstSearch bestFirstSearch=new BestFirstSearch();
        assertEquals(null, bestFirstSearch.solve(searchable));




    }
    @org.junit.jupiter.api.Test
    void solve() {
        int[][] binMaze = {{0, 1, 1, 1, 1}
                , {0, 0, 0, 0, 1}
                , {0, 1, 1, 0, 1}
                , {0, 0, 1, 0, 1}
                , {0, 0, 0, 0, 0}};
        Position start = new Position(0, 0);
        Position goal = new Position(4, 4);
        Maze maze = new Maze(binMaze, start, goal);
        ISearchable searchable = new SearchableMaze(maze);
        BestFirstSearch bestFirstSearch = new BestFirstSearch();
        Solution sol = bestFirstSearch.solve(searchable);

        List<AState> list = new ArrayList<>();
        list.add(new MazeState(new Position(0, 0)));
        list.add(new MazeState(new Position(1, 1)));
        list.add(new MazeState(new Position(1, 2)));
        list.add(new MazeState(new Position(2, 3)));
        list.add(new MazeState(new Position(3, 3)));
        list.add(new MazeState(new Position(4, 4)));
        MazeState temp,temp1;
        if (list.size() != sol.getSolutionPath().size())
            assertEquals(1, 2);
        for (int i = 0; i < list.size(); i++) {
            temp=(MazeState)list.get(i);
            temp1=(MazeState)sol.getSolutionPath().get(i);
            if (!temp.getCurrentPosition().equals(temp1.getCurrentPosition())) {
                System.out.println(list.get(i));
                System.out.println(sol.getSolutionPath().get(i));
                assertEquals(1, 2);
            }
        }
    }
}