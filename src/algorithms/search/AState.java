package algorithms.search;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a state in a puzzle
 */
public abstract class AState implements Comparable{

    public boolean visited;//Have we been in this state already
    private int priority;//The priority of this state
    protected ArrayList<AState> possibleNextStates;//The possible moves
    private AState predecessor;
    protected String stateToString;


    /**
     * This function will compare to AStates using the priority
     * @param o - The object
     * @return - A negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Object o) {
        if(o instanceof AState)
        {
            AState aState=(AState)o;
            return aState.priority-this.priority;
        }
        throw new IllegalArgumentException("The given object is not a state");
    }

    /**
     * The constructor
     *
     * @param priority - The priority of this state
     */
    protected AState(int priority) {
        visited = false;
        this.priority = priority;
        possibleNextStates = new ArrayList<>();
        predecessor=null;
        this.stateToString="";
    }
    /**
     * The constructor, ww will set the priority to -1
     */
    protected AState() {
        this(-1);
    }

    /**
     * This function will set the predecessor
     * @param predecessor - The given predecessor
     */
    public void setPredecessor(AState predecessor)
    {
        this.predecessor=predecessor;
    }

    /**
     * This function over ride the to string method
     * @return
     */
    public String toString()
    {
        return this.stateToString;
    }

    /**
     * This function will return the predecessor of this state
     * @return
     */
    public AState getPredecessor()
    {
        return  this.predecessor;
    }

    /**
     * This function will return the priority of this state
     *
     * @return - This state's priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * This function will set a state's priority
     * @param priority - The given priority
     */
    public void setPriority(int priority)
    {
        this.priority=priority;
    }
    /**
     * This function will add a state to the possible states list
     *
     * @param aState - The given state
     */
    public void addPossilbleState(AState aState) {
        this.possibleNextStates.add(aState);
    }

    /**
     * This function will remove a given state fro the list of all possible states
     *
     * @param aState - The given state
     */
    public void removeState(AState aState) {
        this.possibleNextStates.remove(aState);
    }

    /**
     * This function will remove a state from the list of possible states uusing a given index
     *
     * @param index - The given index
     */
    public void removeStateAt(int index) {
        this.possibleNextStates.remove(index);
    }

    /**
     * This function will return the state in thee list that is located in "index"
     * @param index - The given index
     * @return- The state in thee list that is located in "index"
     */
    public AState getStateAt(int index)
    {
        return this.possibleNextStates.get(index);
    }

    /**
     * This function wll return the size of the list (The number of possible moves)
     * @return - The umber of possible moves
     */
    public int getNumberOfPossibleMoves()
    {
        return this.possibleNextStates.size();
    }
    /**
     * This function will sort the list of possible moves using the priority as a key to compare between to states
     * Notice that this list will be sorted as such: The state with the maximum priority will be the first node
     * the state with the minimum priority will be at the end of the list
     * We used Selection Sort
     */
    public void sortByPriority() {
        //The sorted list to be
        ArrayList<AState> sorted = new ArrayList<>();
        int length = this.possibleNextStates.size();

        //The maximum priority
        int max;
        //The state with the maximum priority
        AState maxState;

        for (int i = 0; i < length; i++) {
            maxState = null;
            max = -1;
            for (int j = 0; j < length - i; j++) {
                if (possibleNextStates.get(j).priority > max) {
                    max = possibleNextStates.get(j).priority;
                    maxState = possibleNextStates.get(j);
                }

            }
            this.possibleNextStates.remove(maxState);
            sorted.add(maxState);
        }
        this.possibleNextStates = sorted;

    }


}
