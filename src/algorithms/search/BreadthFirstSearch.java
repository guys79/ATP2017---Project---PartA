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
        LinkedList<AState> nextToBeEvaluated=new LinkedList<>();

        //Adding the start state to the list
        nextToBeEvaluated.add(start);
        //This variable will represent the state that is currently being scanned
        AState scanned;
        //This boolean variable will hold the value "true" if we have reached our goal
        boolean didWeGetThereYet=false;

        //Untill we scanned the whole graph
        while(nextToBeEvaluated.size()!=0)
        {

            scanned=nextToBeEvaluated.removeFirst();
            scanned.visited=true;
            this.numberOfNodesEvaluarted++;
            for (int i=0; i<scanned.getNumberOfPossibleMoves();i++)
            {
                if(scanned==goal)
                {
                    goal.setPredecessor(scanned);
                    this.numberOfNodesEvaluarted++;
                    didWeGetThereYet=true;

                    break;
                }
                if(!didWeGetThereYet) {
                    if (!scanned.getStateAt(i).visited) {
                        scanned.getStateAt(i).setPredecessor(scanned);
                        nextToBeEvaluated.addLast(scanned.getStateAt(i));
                    }
                }

            }
            if(didWeGetThereYet)
                break;

        }


    }
}
