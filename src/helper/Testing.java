package helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Takes a large integer from a txt file
 * Stores the number in a BigInteger using the Helper method
 * Determines how many times the large number can be divided by a number, and finds the remainer
 */

public class Testing {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader file = new FileReader("src/helper/test-data.txt");
        Scanner scanner = new Scanner(file);
        String s = scanner.next();
        BigInteger num = Helper.parseBig(s, 0, s.length());
        int toDivide = 37;
        int evenDivisions = 0;
        while (num.divideAndRemainder(new BigInteger(toDivide + ""))[1].equals(new BigInteger("0"))) {
            evenDivisions++;
            num = num.divide(new BigInteger(toDivide + ""));
        }
        BigInteger remainder = num.divideAndRemainder(new BigInteger(toDivide + ""))[1];
        System.out.println("Divides " + toDivide + " exactly " + evenDivisions + " times, and then has remainder " + remainder);
    }
}
