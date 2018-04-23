package algorithms.search;

import java.util.List;

/**
 * This class represents a state in a puzzle
 */
public abstract class AState {


    private int priority;
    private List<AState> PossibleNextStates;
    

    private AState()
    {

    }
}
