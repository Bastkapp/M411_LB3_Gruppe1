package sorting.algorithms.insertionsort;

import sorting.algorithms.ISortAlgorithm;
import sorting.SortArray;

/**
 * Selection Sort
 * @author Yannic Markant
 * @version 1.0
 *
 * Der Begriff Selection-Sort, bezeichnet einen naiven Sortieralgorithmus, der in-place arbeitet und in seiner Grundform instabil ist,
 * wobei er sich auch stabil implementieren lässt. Die Komplexität von SelectionSort ist, in der Landau-Notation ausgedrückt, O(n2).
 *
 * Best Case: O(n²)
 * Worst Case: O(n²)
 * Average Case: O(n²)
 */

public class InsertionSort implements ISortAlgorithm {
    private double duration;
    private int comparison = 0;
    private int loopRun = 0;
    private long memory = 0;

    /**
    *@param array the array to be sorted
    *@see SortArray
    */

    @Override
    public void runSort(SortArray array) {
        Runtime runtime = Runtime.getRuntime();

        double startTime = System.nanoTime();

        int k = array.arraySize();
        for (int i = 0; i < array.getValue.length; i++) {
            for (int j = array.getValue.length-1; j > 0; j--) {
                if (array.getValue[j-1] > array.getValue[j]) {
                    k = array.getValue[j];
                    array.getValue[j] = intArr[j - 1];
                    array.getValue[j - 1] = k;
                }
            }
        }

        double endTime = System.nanoTime();

        this.duration = endTime - startTime;

        this.memory = runtime.totalMemory() - runtime.freeMemory();
    }

    @Override
    public String getName() {
        return "InsertionSort";
    }

    @Override
    public double getDuration() {
        return duration;
    }

    @Override
    public int getAmountOfComparisons() {
        return comparison;
    }

    @Override
    public long getMemoryUsage() {
        return memory;
    }

    @Override
    public long getLoopRuns() {
        return loopRun;
    }
}

