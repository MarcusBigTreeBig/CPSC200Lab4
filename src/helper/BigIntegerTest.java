package helper;

import utilities.StopWatch;

import java.math.BigInteger;
import java.util.Random;

public class BigIntegerTest {
    public static void main(String[] args) {

        int timings = 100;
        int increment = 100;
        int trials = 10;

        StopWatch clock = new StopWatch();
        BigInteger num;
        String str;
        Random rand = new Random();

        for (int i = increment; i <= timings*increment; i += increment) {
            clock.reset();
            for (int j = 0; j < trials; j++) {
                str = "";
                for (int k = 0; k < i; k++) {
                    str += rand.nextInt(10);
                }
                clock.start();
                num = new BigInteger(str);
                clock.stop();
            }
            System.out.println(i + ", " + clock.elapsed()/trials);
        }

    }
}
