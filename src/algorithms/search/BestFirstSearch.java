package algorithms.search;


import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * This class describes the Best First Search algorithm
 */
public class BestFirstSearch extends ASearchingAlgorithm {
    /**
     * This function will return the name of the searching algorithm
     *
     * @return - The name of the searching algorithm
     */
    @Override
    public String getName() {
        return "Best First Search";
    }

    /**
     * This function will do the actual solving using the searching algorithm
     *
     * @param start - The start state
     * @param goal  - The goal state
     */
    protected void solve(AState start, AState goal) {
    }
}
