package utilities;

import java.util.Comparator;
import java.util.Random;
import java.util.function.Predicate;

public class Utilities {

    /**
     * Swaps the first and second given indexes in the given array
     * Modifies the array given
     *
     * @param data The array that is swapping data
     * @param i The first index
     * @param j The second index
     * @param <E> The type of the array that is swapping data
     */
    public static <E> void swap (E[] data, int i, int j) {
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * Checks if an array is sorted
     * The object must extend Comparable to be checked
     *
     * @param data the array to check
     * @return true if the array is sorted
     * @param <E> the type of the data in the array
     */
    public static <E extends Comparable<? super E>> boolean isSorted (E[] data) {
        return isSorted(data, 0, data.length);
    }

    /**
     * Checks if part of an array is sorted
     * The object must extend Comparable to be checked
     *
     * @param data the array to check
     * @param i the start index of where is being checked (inclusive)
     * @param j the end index of where is being checked (exclusive)
     * @return true if the array is sorted
     * @param <E> the type of the data in the array
     */
    public static <E extends Comparable<? super E>> boolean isSorted (E[] data, int i, int j) {
        return isSorted(data, i, j, Comparator.naturalOrder());
    }

    /**
     * Checks if an array is sorted
     *
     * @param data the array to check
     * @param c the comparator used compare 2 objects of the array
     * @return true if the array is sorted
     * @param <E> the type of the data in the array
     */
    public static <E> boolean isSorted (E[] data, Comparator<? super E> c) {
        return isSorted(data, 0, data.length, c);
    }

    /**
     * Checks if part of an array is sorted
     *
     * @param data the array to check
     * @param i the start index of where is being checked (inclusive)
     * @param j the end index of where is being checked (exclusive)
     * @param c the comparator used compare 2 objects of the array
     * @return true if the array is sorted
     * @param <E> the type of the data in the array
     */
    public static <E> boolean isSorted (E[] data, int i, int j, Comparator<? super E> c) {
        for (int k = i; k < j-1; k++) {
            if (c.compare(data[k], data[k+1]) > 0) {
                /*
                Loop Invariant:
                [0, k) is sorted
                if ever this is untrue, returns false
                 */
                return false;
            }
        }
        return true;
    }

    /**
     * Partitions an array based on a pivot
     *
     * This is different from the quicksort partition algorithm because
     * the quicksort partition chooses an element from the array as the pivot, and places it in separation
     * of the partition after partitioning is complete.
     * This can be achieved with this algorithm by adding an element from the array to the predicate while creating
     * the predicate, but this is not necessary in this algorithm.
     * For the quicksort partitioning algorithm, choosing an element in the array as a pivot can introduce sentinels,
     * which cannot be done with this algorithm.
     *
     * @param data the array being partitioned
     * @param ell the start index of the part of the array being partitioned (inclusive)
     * @param arr the start index of the part of the array being partitioned (exclusive)
     * @param p Predicate containing a test method, that returns false if the element should be before the pivot, true
     * @return the first index after the partition after the algorithm
     * @param <E> the type of the data in the array
     */
    public static <E> int partition (E[] data, int ell, int arr, Predicate<E> p) {
        int length = arr-ell-1;
        arr--;
        int i = 0;
        while (i < length) {
            /*
            Loop Invariants:
            1. length = arr-ell+i
            2. [0, ell) partitioned correctly, (arr, length] partitioned correctly
             */
            if (!p.test(data[ell])) {
                ell++;
                i++;
            }else if (p.test(data[arr])) {
                arr--;
                i++;
            }else{
                swap(data, ell, arr);
                //don't increment here, because should only increment if moving arr or ell
            }
        }
        return arr;
    }

    /**
     * Randomly arranges an array
     *
     * @param rand a random object used for random number generation
     * @param data the array to be randomized
     * @param <E> the type of data in the array
     */
    public static <E> void shuffle (Random rand, E[] data) {
        for (int i = data.length; i > 1; --i) {
            swap (data, i-1, rand.nextInt(i));
        }
    }
}
