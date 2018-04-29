package algorithms.search;
import algorithms.mazeGenerators.*;

/**
 * This class represents a state in the Maze Puzzle
 */
public class MazeState extends AState {
    private Position current;//This position is the current position of the player in the maze

    /**
     * The constructor
     * @param curr - The current position of the player in the maze
     */
    public MazeState(Position curr)
    {
        super();
        current=curr;
        this.stateToString="{"+curr.GetRowIndex()+","+curr.GetColumnIndex()+"}";

    }

    /**
     *
     * The constructor
     * @param curr - The current position of the player in the maze
     * @param priority - The priority
     */
    public MazeState(Position curr, int priority)
    {
        super(priority);
        current=curr;
        this.stateToString="{"+curr.GetRowIndex()+","+curr.GetColumnIndex()+"}";
    }

    /**
     * This function will return the current position of the player in the maze
     * @return - the current position of the player in the maze
     */
    public Position getCurrentPosition()
    {
        return new Position(current);
    }
}
