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
     * The copy constructor
     * @param mazeState - The other MazeState
     */
    public MazeState(MazeState mazeState)
    {
        super(mazeState);
        current=mazeState.current;
        this.stateToString=mazeState.stateToString;

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

    /**
     * This function will determine whether two mazeStates are equal to each other
     * @param obj -The other object
     * @return - True if they are equal and false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MazeState) {
            return super.equals(obj) && ((MazeState)obj).current.equals(current);
        }
        return false;
    }
}
