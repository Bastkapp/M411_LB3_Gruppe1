package sorting.algorithms.gnomesort;

import sorting.algorithms.ISortAlgorithm;
import sorting.SortArray;

/**
 * Gnome Sort Algorithmus
 *
 * @author Dennis Künzle
 * @version 1.0
 */

public class GnomeSort implements ISortAlgorithm{

    private double duration;
    private int comparison = 0;
    private int loopRun = 0;
    private long memory = 0;
    /**
     *
     */
    @Override
    public void runSort(SortArray array) {
        Runtime runtime = Runtime.getRuntime();

        double startTime = System.currentTimeMillis();

        int index = 0;
        while (index < array.arraySize()) {
            comparison++;
            if (index == 0) {
                index++;
            } else {
                comparison++;
                if (array.getValue(index) >= array.getValue(index - 1)) {
                    index++;
                } else {
                    array.swap(index, index - 1);
                    index--;
                }
            }
            loopRun++;
        }
        double endTime = System.currentTimeMillis();

        this.duration = endTime - startTime;

        this.memory = runtime.totalMemory() - runtime.freeMemory();
    }

    @Override
    public String getName() {
        return "GnomeSort";
    }

    @Override
    public double getDuration() {
        return this.duration;
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
