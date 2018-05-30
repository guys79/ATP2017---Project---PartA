package IO;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * This class is designed to decompress the given data and processing it.
 */
public class MyDecompressorInputStream  extends InputStream {
    InputStream in;//The input stream

    /**
     * The constructor
     * @param in - The given input stream
     */
    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    /**
     * The read function that we had to implement
     * @return - An integer
     */
    public int read() {
        try {
            return in.read();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
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

    /**
     * This function receives an compressed byte array and decompresses it
     * @param b - The byte array
     * @return - an integer
     */

    public  int read(byte[] b) {

        try{
            in.read(b);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }



        //The start of the maze itself in the given array
        int start = FromBinaryToInt(b[0]) + FromBinaryToInt(b[FromBinaryToInt(b[0]) + 1]) + 2;

        byte[] tempArray = new byte[b.length];
        byte current = 1;
        int index = 0;

        int base = Byte.MAX_VALUE + Math.abs(Byte.MIN_VALUE);
        //Decompressing
        for (int j = start; j < b.length; j++) {


            if (index >= tempArray.length)
                break;


            //Inserting the goal/end position
            if (b[j] == (byte) (base) || b[j] == (byte) (base - 1)) {
                if (b[j] == (byte) (base))
                    index = InsertSequence(tempArray, index, (byte) 2, 1);
                else
                    index = InsertSequence(tempArray, index, (byte) 3, 1);
                current = (byte) (1 - current);
            }
            //Inserting the sequence into the array
            else {
                current = (byte) (1 - current);
                index = InsertSequence(tempArray, index, current, b[j]);

            }
        }
        for(int i=start;i<b.length;i++)
        {
            b[i]=tempArray[i-start];
        }

        return  0;

    }

    /**
     * This function adds a sequence to the array sttarting from the cell in index
     * @param b - The byte array
     * @param index - The given index
     * @param length - The length of the sequence
     * @param current - The sequence's char
     * @return - The next free index
     */
    private int InsertSequence(byte []b,int index,byte current,int length) {
        for (int i = index; i < length + index; i++) {
            b[i] = current;
        }
        return length + index;
    }

    public  void print(byte[]a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();

    }

}
