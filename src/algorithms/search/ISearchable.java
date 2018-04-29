package algorithms.search;

public interface ISearchable {

    public AState getStartState();
    public AState getGoalState();
    /**
     * This function will reset the maze in order to allow multiple scans
     */
    public void reset();

}
