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
     * @param start - The start state
     * @param goal  - The goal state
     */
    //this is the dfs algorithem



    protected void solve(AState start, AState goal) {
        start.visited=true;
        this.numberOfNodesEvaluated++;
        //we mark the current vertex as visited


        //we cheak if we fount the goal
        if(start==goal){
            return;
        }

        //we go over all his neibhors that we didn't visit yet

        for (int i=0;i<start.getNumberOfPossibleMoves();i++){
            if(!start.getStateAt(i).visited){
                start.getStateAt(i).setPredecessor(start);
                solve(start.getStateAt(i),goal);
            }
        }
    }
}
