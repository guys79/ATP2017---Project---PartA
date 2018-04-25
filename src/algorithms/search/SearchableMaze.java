package algorithms.search;
import algorithms.mazeGenerators.*;
//
public class SearchableMaze implements ISearchable {
    /*public SearchableMaze(Maze maze) {
        MazeState[][]
        for (int i = 0; i < maze.getNumOfRows()) {
            for (int j = 0; j < maze.getNumOfColumns()) {
                if (maze.ifThereIsAPass(i, j)) {
                    new MazeState(new Position(i, j));
                }
            }
        }
        for (int i = 0; i < maze.getNumOfRows()) {
            for (int j = 0; j < maze.getNumOfColumns()) {
                if (maze.ifThereIsAPass(i, j)) {
                   if(i-1>=0&&maze.ifThereIsAPass(i-1, j)){

                   }
                }
            }
        }
    }*/
    private AState start,goal;
    public SearchableMaze(AState start, AState goal)
    {
        this.goal=goal;
        this.start=start;

    }
    public AState getStartState()
    {
        return this.start;
    }
    public AState getGoalState()
    {
        return this.goal;
    }
}
