package helper;

import java.math.BigInteger;

/**
 * Contains an improved algorithm for the construction of a BigInteger
 */

public class Helper {
    private static java.util.Map<Integer, BigInteger> stash = new java.util.TreeMap<Integer, BigInteger>();

    /**
     * Constructs a BigInteger object
     * O(n^1.6) time complexity
     *
     * @param s a string to input, must represent a base 10 number
     * @param start where to start reading in the string
     * @param length how many digits to read from the string
     * @return the constructed BigInteger
     */
    public static BigInteger parseBig(String s, int start, int length) {
        int n = length;
        /*
        want to find value of k such that n/3 <= 9*2^k < 2n/3
         */
        int k = Math.max((int)Math.ceil((Math.log(n)-Math.log(27))/Math.log(2)), 0);
        return parseBig(s, start, length, k);
    }

    /**
     * Recursively parses through the string for the parseBig algorithm of
     * constructing a BigInteger object
     *
     * @param s a string to input, must represent a base 10 number
     * @param start where to start reading in the string
     * @param length how many digits to read from the string
     * @param k length/3 <= 9*2^k < 2length/3
     * @return the constructed BigInteger
     */
    private static BigInteger parseBig (String s, int start, int length, int k) {
        int n = length;
        if (n < 10) {//base case
            return new BigInteger(s.substring(start, start+n));
        }else{//recursive section
            int n1, n2;
            n2 = 9*(int)Math.pow(2, k);
            n1 = n-n2;
            BigInteger B = tenToThe9xTwoToThe(k);
            return B.multiply(parseBig(s, start, n1)).add(parseBig(s, start+n1, n2, k-1));//splits the string in 2, and calls each recursively
            /*
            The algorithm written in java does split into 2 smaller sub-problems and make recursive calls
            Instead, it just splits the string in even sections of 9, and multiplies each section by a different 10^(9k)
             */
        }
    }

    /**
     *
     * @param k must be >=0
     * @return 10^(9*(2^k))
     */
    public static BigInteger tenToThe9xTwoToThe (int k) {
        if (k < 0) {
            throw new IllegalArgumentException ("Positive Powers Only");
        }

        /*
        The largest value possible for k is:
        ceiling(log(n)-log(27)) where log is the base 2 log, and n is the length of the total string
        When tenToThe9xTwoToThe is called, it recursively calls the method for each k-1 until k=0
        This means that ceiling(log(n)-log(27)) values will be stashed
         */

        if (!stash.containsKey(k)) {
            BigInteger answer;
            if (k==0) {//base case
                answer = BigInteger.TEN.pow(9);
            }else{//recursive section
                BigInteger temp = tenToThe9xTwoToThe(k-1);
                answer = temp.multiply(temp);
            }
            stash.put(k, answer);
        }
        return stash.get(k);
    }
}
