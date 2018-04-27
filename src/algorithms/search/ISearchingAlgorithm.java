package algorithms.search;

/**
 * This interface defines the necessary function in order to be a searching algorithm
 */
public interface ISearchingAlgorithm {

    /**
     * This function will receive searchable puzzle and will return the solution to it
     * @param iSearchable - The Searchable puzzle
     * @return - The solution to the puzzle
     */
    public Solution solve(ISearchable iSearchable);

    /**
     * This function will return the name of the class that solves the puzzle.
     * @return - the name of the class that solves the puzzle.
     */
    public String getName();

    /**
     * This function will return the number of nodes evaluated in the search
     * @return - return the number of nodes evaluated in the search
     */
    public int getNumberOfNodesEvaluated();

}

