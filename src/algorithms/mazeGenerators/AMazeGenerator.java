package algorithms.mazeGenerators;

/**
 * This class represents a general maze generator
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    /**
     * This function will measure the time it takes to generate a maze in the size of (column X row)
     * @param column - The number of columns
     * @param row - the number of rows
<<<<<<< Updated upstream
     * @return - This function will return the rime it takes to generate a maze
=======
     * @return - This function will return the rime it takes to ge  nerate a maze
>>>>>>> Stashed changes
     */
    public long  measureAlgorithmTimeMillis(int row,int column)
    {

        //Starting time
        long start = System.currentTimeMillis();
        //The function
        generate(column,row);
        //Ending time
        long end=System.currentTimeMillis();
        return end-start;

    }

}
