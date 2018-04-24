package algorithms.search;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a state in a puzzle
 */
public abstract class AState {

    public boolean visited;//Have we been in this state already
    private int priority;//The priority of this state
    protected ArrayList<AState> possibleNextStates;//The possible moves


    /**
     * The constructor
     * @param priority - The priority of this state
     */
    protected AState(int priority)
    {
        visited=false;
        this.priority=priority;
        possibleNextStates=new ArrayList<>();
    }

    /**
     * The constructor, ww will set the priority to -1
     */
    protected  AState()
    {
        this(-1);
    }

    public int getPriority()
    {
        return priority;
    }

    public void addPossilbleState(AState aState)
    {
        this.possibleNextStates.add(aState);
    }
    public void removeState(AState aState)
    {
        this.possibleNextStates.remove(aState);
    }
    public void removeStateAt(int index)
    {
        this.possibleNextStates.remove(index);
    }
    public void sortByPriority()
    {
        ArrayList<AState> sorted =new ArrayList<>();
        int length=this.possibleNextStates.size();
        int max=-1;
        AState maxState=null;
        for (int i=0; i<length;i++)
        {
            maxState=null;
            max=-1;
            for(int j=0;j<length-i;j++)
            {
                if(possibleNextStates.get(j).priority>max)
                {max=possibleNextStates.get(j).priority;
                maxState=possibleNextStates.get(j);}

            }
            this.possibleNextStates.remove(maxState);
            sorted.add(maxState);
        }
        this.possibleNextStates=sorted;

    }


}
