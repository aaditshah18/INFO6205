/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;
import java.util.Random;
import java.util.Arrays;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Config;
import edu.neu.coe.info6205.util.Timer;


/**
 * Class InsertionSort.
 *
 * @param <X> the underlying comparable type.
 */
public class InsertionSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for any sub-classes to use.
     *
     * @param description the description.
     * @param N           the number of elements expected.
     * @param config      the configuration.
     */
    protected InsertionSort(String description, int N, Config config) {
        super(description, N, config);
    }

    /**
     * Constructor for InsertionSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public InsertionSort(int N, Config config) {
        this(DESCRIPTION, N, config);
    }

    public InsertionSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION, config));
    }

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        super(helper);
    }

    public InsertionSort() {
        this(BaseHelper.getHelper(InsertionSort.class));
    }

    /**
     * Sort the sub-array xs:from:to using insertion sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();

        int currentIndex, previousIndex;
        for (currentIndex = from; currentIndex < to; currentIndex++) {
            previousIndex = currentIndex;

            while (previousIndex > from) {
                if (helper.swapConditional(xs, previousIndex - 1, previousIndex))
                    previousIndex = previousIndex - 1;
                else
                    break;
            }
        }
    }

    public static void main(String[] args) {
        // Define different values of n following the doubling method
        int[] ns = {10000, 20000, 40000, 80000, 160000};

        for (int n : ns) {
            Integer[] array = generateRandomArray(n);
            benchmarkInsertionSort(array);
        }
    }

    // Method to generate a random Integer array
    private static Integer[] generateRandomArray(int n) {
        Integer[] array = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    // Method to benchmark insertion sort using Timer and print results
    private static void benchmarkInsertionSort(Integer[] array) {
        Timer timer = new Timer();

        double meanLapTime = timer.repeat(10, false,
                () -> Arrays.copyOf(array, array.length),
                arr -> {
                    new InsertionSort<Integer>().sort(arr, 0, arr.length);
                    return null; // The sort modifies the array in place, and we don't need a result.
                },
                null, // preFunction not provided
                null  // No postFunction
        );

        System.out.println("Array Size (n = " + array.length + "): " + meanLapTime + " milliseconds");
    }

    public static final String DESCRIPTION = "Insertion sort";

    public static <T extends Comparable<T>> void sort(T[] ts) {
        new InsertionSort<T>().mutatingSort(ts);
    }
}