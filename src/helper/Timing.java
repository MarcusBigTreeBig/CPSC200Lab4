package helper;

import utilities.StopWatch;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

/**
 * Plots a graph of the running time of
 * The Java written constructor for BigInteger
 * against the Helper algorithm for BigInteger
 */

public class Timing {
    public static void main(String[] args) throws IOException {

        int timings = 100;
        int increment = 10000;
        int trials = 10;

        StopWatch javaClock = new StopWatch();
        StopWatch helperClock = new StopWatch();
        BigInteger num;
        String str;
        Random rand = new Random();
        FileWriter file = new FileWriter("timing-data.txt");

        for (int i = increment; i <= timings*increment; i += increment) {
            javaClock.reset();
            for (int j = 0; j < trials; j++) {
                str = "";
                for (int k = 0; k < i; k++) {
                    str += rand.nextInt(10);
                }
                javaClock.start();
                num = new BigInteger(str);
                javaClock.stop();
                helperClock.start();
                num = Helper.parseBig(str, 0, str.length());
                helperClock.stop();
            }
            file.write(i + ", " + javaClock.elapsed()/trials + ", " + helperClock.elapsed()/trials + "\n");
            file.flush();
        }

    }
}
