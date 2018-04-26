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
     * @param start - The start state
     * @param goal  - The goal state
     */
    //this is the dfs algorithem
    private void recurciveDFS(AState curr){
        this.numberOfNodesEvaluated++;
        //we mark the current vertex as visited
        curr.visited=true;
        //we cheak if we fount the goal
        if(curr==goal){
            return;
        }
        //we go over all his neibhors that we didn't visit yet
        for (int i=0;i<curr.getNumberOfPossibleMoves();i++){
            if(!curr.getStateAt(i).visited){
                curr.getStateAt(i).setPredecessor(curr);
                recurciveDFS(curr.getStateAt(i));
            }
        }
    }


    protected void solve(AState start, AState goal) {
        //we take the current goal
        this.goal=goal;
        //we call the DFS function
        recurciveDFS(start);
    }
}
