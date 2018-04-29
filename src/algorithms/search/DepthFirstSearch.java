package algorithms.search;

/**
 * This class describes the Depth First Search algorithm
 */
public class DepthFirstSearch extends ASearchingAlgorithm{
    AState goal;

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
        //we take the current goal
        this.goal=goal;//
        //we call the DFS function
        this.numberOfNodesEvaluated++;
        //we mark the current vertex as visited
        curr.visited=true;
        //we check if we fount the goal
        if(curr==goal){
            return;
        }
        //we go over all his neighbors that we didn't visit yet
        for (int i=0;i<curr.getNumberOfPossibleMoves();i++){
            if(!curr.getStateAt(i).visited){
                this.numberOfNodesEvaluated++;
                curr.getStateAt(i).setPredecessor(curr);
                solve(curr.getStateAt(i),goal);
            }
        }
    }
}
