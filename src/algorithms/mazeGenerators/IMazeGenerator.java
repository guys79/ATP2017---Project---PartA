package algorithms.mazeGenerators;

/**
 * This interface defines the necessary function in order to be a maze generator
 */
public interface IMazeGenerator {
    /**
     * This funcrtion will generate a new maze using Prim's algorithm
     * @param row - The number of rows
     * @param column - The number of columns
     * @return - This function will return a new random binary maze
     */
    Maze generate(int row,int column);
    /**
     * This function will measure the time it takes to generate a maze in the size of (column X row)
     * @param column - The number of columns
     * @param row - the number of rows
     * @return - This function will return the rime it takes to generate a maze
     */
    long measureAlgorithmTimeMillis(int row,int column);
}
