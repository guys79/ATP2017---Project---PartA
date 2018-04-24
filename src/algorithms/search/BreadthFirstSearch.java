package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch extends ASearchingAlgorithm {

    /**
     * This function will return the name of the searching algorithm
     * @return - The name of the searching algorithm
     */
    @Override
    public String getName() {
        return "Breadth First Search";
    }


    /**
     * This function will do the actual solving using the searching algorithm
     * @param start - The start state
     * @param goal - The goal state
     */
    protected void solve(AState start,AState goal) {

        //The list of nodes that are waiting to be scanned
        //Notice - we will treat it as a queue
        LinkedList<AState> nextToBeEvaluated=new LinkedList<>();

        //Adding the start state to the list
        nextToBeEvaluated.add(start);
        //This variable will represent the state that is currently being scanned
        AState scanned;
        //This boolean variable will hold the value "true" if we have reached our goal
        boolean didWeGetThereYet=false;

        //Until we scanned the whole graph
        while(nextToBeEvaluated.size()!=0)
        {

            //Dequeue
            scanned=nextToBeEvaluated.removeFirst();
            scanned.visited=true;
            this.numberOfNodesEvaluarted++;
            //For each possible state
            for (int i=0; i<scanned.getNumberOfPossibleMoves();i++)
            {
                //If we have found the goal
                if(scanned.getStateAt(i)==goal)
                {
                    goal.setPredecessor(scanned);
                    this.numberOfNodesEvaluarted++;
                    didWeGetThereYet=true;

                    break;
                }
                //If we haven't reached the goal yet
                if(!didWeGetThereYet) {
                    //If didn't already visit this node
                    if (!scanned.getStateAt(i).visited) {

                        scanned.getStateAt(i).setPredecessor(scanned);
                        //Enqueue
                        nextToBeEvaluated.addLast(scanned.getStateAt(i));
                    }
                }

            }
            if(didWeGetThereYet)
                break;

        }


    }
}
