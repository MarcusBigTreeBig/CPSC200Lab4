package helper;

import java.math.BigInteger;

public class Helper {
    private static java.util.Map<Integer, BigInteger> stash = new java.util.TreeMap<Integer, BigInteger>();

    public static BigInteger parseBig(String s, int start, int length) {
        int n = s.length();
        if (n < 10) {//base case
            return new BigInteger(s);
        }else{//recursive step
            int k = (int)Math.ceil((Math.log(n)-Math.log(27))/Math.log(2));//find value of k s.t. n/3<=9*2^k<2n/3
            return parseBig(s, start, length, k);
        }
    }

    private static BigInteger parseBig (String s, int start, int length, int k) {
        int n = s.length();
        if (n < 10) {//base case
            return new BigInteger(s.substring(start, start+length));
        }else{//recursive step
            int n1, n2;
            if (k<0) {k=0;}
            n2 = 9*(int)Math.pow(2, k);
            n1 = n-n2;
            BigInteger B = tenToThe9xTwoToThe(k);
            return B.multiply(parseBig(s, start, n1)).add(parseBig(s, n1, n2, k-1));//should parse through 2 strings, d2+Bd1
        }
    }

    public static BigInteger tenToThe9xTwoToThe (int k) {
        if (k < 0) {
            throw new IllegalArgumentException ("Positive Powers Only");
        }
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
