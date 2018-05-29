package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents the solution to the puzzle
 */
public class Solution implements Serializable {

    private ArrayList<AState> sol;//The solution

    /**
     * The constructor
     * @param sol - The solution
     */
    public Solution(ArrayList<AState> sol){

        this.sol=sol;
    }

    /**
     * This function returns the solution path (Deep  Copy)
     * @return - The solution path
     */
    public ArrayList<AState> getSolutionPath()
    {
        return new ArrayList<>(this.sol);

    }



}
