package test;

import IO.MyCompressorOutPutStream;
import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.util.ArrayList;

/**
 * Created by Aviadjo on 3/22/2017.
 */
public class RunMazeGenerator {

    public static void write(byte[] b) {
     }
    /**
     * This function converts signed binary number into a number (unsigned binary)
     *
     * @param num - The number
     * @return
     */
    public static int FromBinaryToInt(byte num) {
        if (num < 0)
            return 128 + (128 - (num * -1));
        return (int) (num);
    }

    public static void fff(int []a)
    {
        a=new int[5];
        a[0]=1;
        a[1]=1;
        a[2]=1;
        a[3]=1;
        a[4]=1;
    }
    public static void main(String[] args) {

        int []a={1,2,3,4,5};
        fff(a);
        System.out.println(a[0]+" ");
        System.out.print(a[1]+" ");
        System.out.print(a[2]+" ");
        System.out.print(a[3]+" ");
        System.out.print(a[4]+" ");
        ISearchable searchable=null;
        Maze maze;
        int [][] binMaze={{ 0, 0, 0, 1, 1, 1, 0 },
                          { 0, 1, 0, 0, 0, 1, 0 },
                          { 0, 0, 0, 0, 0, 0, 1 },
                          { 0, 1, 1, 1, 0, 0, 0 },
                          { 0, 0, 0, 0, 0, 0, 1 },
                          { 0, 0, 0, 0, 0, 0, 1 },
                          { 1, 0, 1, 0, 0, 0, 0}};
        //000 111 00 1 000 1 0000G00 1 0 111 0000000S0 1 000000 11 0 1 0000
        // 3   3   2 1  3  1  4 -2 2 1 1  3      9     1     6   2 1 1  4
        Position start=new Position(4,4);
        Position goal=new Position(2,3);
        maze=new Maze(binMaze,start,goal);
        byte[] byteMaze=maze.toByteArray();
        MyCompressorOutPutStream myCompressorOutPutStream=new MyCompressorOutPutStream(System.out);
        write(byteMaze);
        myCompressorOutPutStream.write(byteMaze);
        //write(byteMaze);
        //maze.print();
        /*
        searchable=new SearchableMaze(maze);
        BestFirstSearch bestFirstSearch=new BestFirstSearch();
        BreadthFirstSearch breadthFirstSearch=new BreadthFirstSearch();
        Solution sol=bestFirstSearch.solve(searchable);
        Solution sol1=breadthFirstSearch.solve(searchable);
        System.out.println(sol.getSolutionPath());
        System.out.println(bestFirstSearch.getNumberOfNodesEvaluated());
        System.out.println(sol1.getSolutionPath());
        System.out.println(breadthFirstSearch.getNumberOfNodesEvaluated());*/
        //unsigned byte test=0;
        byte b=0;
        int counter=0;
      /*  for (int i=0; i<1000;i++)
        {
            System.out.println("*****"+counter+"*****");
            System.out.println(b);
            System.out.println(FromIntToBinary(counter));
            System.out.println(FromBinaryToInt(b));
            b++;
            counter++;
            System.out.println();
            System.out.println();

        }*/

       // byte [] num=getNumberInDifferentBase(16387323);
   //     for(int i=0;i<num.length;i++)
   //     {
           //System.out.println(num[i]);
       //     System.out.println(FromBinaryToInt(num[i]));
      //  }

    }

    public static  int FromIntToBinary(int num)
    {
        return (1-(num/128)%2)*(num%128) +((num/128)%2)*(-128+num%128);
    }

    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(15/*rows*/,15/*columns*/)));
        // generate another maze
        Maze maze = mazeGenerator.generate(15/*rows*/, 15/*columns*/);

        // prints the maze
        maze.print();

        // get the maze entrance
        Position startPosition = maze.getStartPosition();

        // print the position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"

        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}