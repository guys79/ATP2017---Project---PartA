package algorithms.search;

import java.util.ArrayList;

/**
 * This class represents a searching algorithm
 */
public abstract class ASearchingAlgorithm implements  ISearchingAlgorithm{

    protected int numberOfNodesEvaluated;//The number of nodes evaluated in the search

    /**
     * The constructor
     */
    protected  ASearchingAlgorithm()
    {
        this.numberOfNodesEvaluated=0;
    }
    /**
     * This function returns the number of nodes that were evaluated during the search
     * @return - The number of nodes that were evaluated during the search
     */
    public int getNumberOfNodesEvaluated()
    {
        return numberOfNodesEvaluated;
    }

    /**
     * This function will receive searchable puzzle and will return the solution to it
     * @param iSearchable - The Searchable puzzle
     * @return - The solution to the puzzle
     */
    public Solution solve(ISearchable iSearchable) {
        //Getting the start and end states
        AState start=iSearchable.getStartState();
        AState goal = iSearchable.getGoalState();
        this.numberOfNodesEvaluated=0;
        if(goal==start) {
            ArrayList<AState>single=new ArrayList<>();
            single.add(goal);
            this.numberOfNodesEvaluated=1;
            return new Solution(single);
        }
        //solve the puzzle
        solve(start,goal);

        //The list of states that will be a part of the solution
        ArrayList<AState> sol=new ArrayList<>();
        //The goal is obviously a part of the solution
        AState partOfTheSolution=goal;

        //We are tracing the route of the sca backwards (from the goal to the start)
        while(partOfTheSolution!=null)
        {
          sol.add(0,partOfTheSolution);
          partOfTheSolution=partOfTheSolution.getPredecessor();
          //System.out.println(partOfTheSolution);
        }
        //Creating the solution
        Solution solution=new Solution(sol);
        iSearchable.reset();
        return solution;
    }

    /**
     * This function will do the actual solving using the searching algorithm
     * @param start - The start state
     * @param goal - The goal state
     */
    protected abstract void solve(AState start,AState goal);
}
