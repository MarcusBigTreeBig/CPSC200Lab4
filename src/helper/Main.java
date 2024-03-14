package helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //FileReader file = new FileReader("test-data.txt");
        String s = "123456217486534789465";
        BigInteger num = Helper.parseBig(s, 0, s.length());
        System.out.println(num);
    }
}
