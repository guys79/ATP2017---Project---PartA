package algorithms.search;

/**
 * This class describes the Depth First Search algorithm
 */
public class DepthFirstSearch extends ASearchingAlgorithm{

    /**
     * This function will return the name of the searching algorithm
     *
     * @return - The name of the searching algorithm
     */
    @Override
    public String getName() {
        return "Depth First Search";
    }


    /**
     * This function will do the actual solving using the searching algorithm
     *
     * @param curr - The start state
     * @param goal  - The goal state
     */
    protected void solve(AState curr, AState goal) {

        //we call the DFS function
        this.numberOfNodesEvaluated++;
        //we mark the current vertex as visited
        curr.visited=true;

        //we go over all his neighbors that we didn't visit yet
        for (int i=0;i<curr.getNumberOfPossibleMoves();i++){
            //If we have reached our goal
            if(curr.getStateAt(i)==goal)
            {
                goal.setPredecessor(curr);
                break;
            }

            if (!curr.getStateAt(i).visited) {
                curr.getStateAt(i).setPredecessor(curr);
                solve(curr.getStateAt(i), goal);
            }

        }
    }

    /**
     * This function will return the number od nodes evaluated
     * @return - The number of nodes evaluated
     */
    @Override
    public int getNumberOfNodesEvaluated() {
        return super.getNumberOfNodesEvaluated()+1;
    }
}
