package algorithms.search;

/**
 * This interface defines the necessary function in order to be a searchable puzzle
 */
public interface ISearchable {

    /**
     * This function return the start state
     * @return - The start state
     */
    public AState getStartState();
    /**
     * This function return the goal state
     * @return - The goal state
     */
    public AState getGoalState();
    /**
     * This function will reset the maze in order to allow multiple scans
     */
    public void reset();

}
