package IO;

import algorithms.mazeGenerators.Maze;

import java.io.OutputStream;
import java.util.ArrayList;

public class MyCompressorOutPutStream extends OutputStream {
    OutputStream out;//The out put stream

    public MyCompressorOutPutStream(OutputStream out) {
        this.out = out;
    }

    /**
     * The write function that we have to implement
     *
     * @param num - The given number
     */
    public void write(int num) {
        try {
            out.write(num);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function overrides The write method in the OutPutStream class
     * This function will compress the binry array ands send it
     *
     * @param b
     */
    public void write(byte[] b) {
        //The start of the maze itself in the given array
        int start = FromBinaryToInt(b[0]) + FromBinaryToInt(b[FromBinaryToInt(b[0]) + 1]) + 2;
        //The compressed list to be
        ArrayList<Byte> compressed = new ArrayList<>();

        //Copying the data on the size of the maze to the list
        for (int i = 0; i < start; i++) {
            compressed.add(b[i]);
        }


        int counter = 0;//Will count the sequence's length
        int current = 0;//The current note (0 or 1)
        int base = Byte.MAX_VALUE + Math.abs(Byte.MIN_VALUE) - 2; //The maximum length of a sequence

        int temp;
        for (int i = start; i < b.length; i++) {

            temp = FromBinaryToInt(b[i]);
            if (temp == 2 || temp == 3) {
                compressed.add((byte) counter);
                if (temp == 2)
                    compressed.add((byte) (base + 2));
                else
                    compressed.add((byte) (base + 1));

                counter = 0;
                if(i!=b.length-1)
                    current=FromBinaryToInt(b[i+1]);
            } else {
                if (counter >= base || current != temp) {

                    current = temp;
                    compressed.add((byte) counter);
                    if (counter >= base) {
                        i--;
                        counter = 0;
                        compressed.add((byte)0);
                    } else

                        counter = 1;

                } else
                    counter++;
            }
        }
        if(counter>=1)
            compressed.add((byte)counter);
        byte[] array = new byte[compressed.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = compressed.get(i);
            System.out.print(array[i]+" ");
        }

        try {
            out.write(array);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * This function converts signed binary number into a number (unsigned binary)
     *
     * @param num - The number
     * @return- The integer
     */
    private int FromBinaryToInt(byte num) {
        if (num < 0)
            return 128 + (128 - (num * -1));
        return (int) (num);
    }

}
