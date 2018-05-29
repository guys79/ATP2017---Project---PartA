package IO;

import algorithms.mazeGenerators.Maze;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * This class is designed to compress the given data and send it
 */
public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;//The out put stream

    public MyCompressorOutputStream(OutputStream out) {
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
        //The compression
        for (int i = start; i < b.length; i++) {

            temp = FromBinaryToInt(b[i]);
            if (temp == 2 || temp == 3) {
                compressed.add((byte) counter);
                if (temp == 2)
                    compressed.add((byte) (base + 2));
                else
                    compressed.add((byte) (base + 1));

                counter = 0;

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
        //Taking care og the last sequence
        if(counter>=1)
            compressed.add((byte)counter);
        byte[] array = new byte[b.length];

        //Transferring it to an array
        for (int i = 0; i < compressed.size(); i++) {
            array[i] = compressed.get(i);

        }
        //Resetting the last cells
        for(int j=compressed.size();j<array.length;j++)
        {
            array[j]=0;
        }

        //Writing the data
        try {
            ObjectOutputStream out2=new ObjectOutputStream(out);
            out2.writeObject(array);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * This function converts signed binary number into a number (unsigned binary)
     *
     * @param num - The number
     * @return- The integer.
     */
    private int FromBinaryToInt(byte num) {
        if (num < 0)
            return 128 + (128 - (num * -1));
        return (int) (num);
    }


  }