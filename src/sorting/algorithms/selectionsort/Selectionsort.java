package sorting.algorithms.selectionsort;

import sorting.algorithms.ISortAlgorithm;
import sorting.SortArray;

/**
 * @Author: Marko Micanovic
 * @Version: 1.0
 */

public class Selectionsort implements ISortAlgorithm {
    private int amountOfComparisons = 0;
    private long memory = 0;
    private long duration = 0;
    private long loopRuns = 0;


    @Override
    public String getName() {
        return "SelectionSort";
    }

    /**
     * Hier habe ich den SelectionSort übernommen, welchen wir im Moodle am 14.01 behandelt haben.
     * Der Code wurde einfach so angepasst, dass es noch die Variablen für Zeit, Speicherverbrauch und Anzahl Durchläufe hat.
     */

    @Override
    public void runSort(SortArray array) {
        long timeBefore = System.nanoTime();
        int lowestNumber, currentNumber;
        Runtime rt = Runtime.getRuntime();
        for (int i = 0; i < array.arraySize(); i++) {
            lowestNumber = array.getValue(i);
            currentNumber = i;
            for (int j = i; j < array.arraySize(); j++) {
                if (array.getValue(j) < lowestNumber) {
                    lowestNumber = array.getValue(j);
                    currentNumber = j;
                    this.amountOfComparisons++;
                    this.loopRuns++;
                }
            }
            if (lowestNumber < array.getValue(i)) {
                array.swap(i, currentNumber);
            }
        }
        this.memory = rt.totalMemory() - rt.freeMemory();
        long timeAfter = System.nanoTime();
        this.duration = timeAfter - timeBefore;
    }

    @Override
    public double getDuration() {
        return duration;
    }

    @Override
    public int getAmountOfComparisons() {
        return amountOfComparisons;
    }

    @Override
    public long getMemoryUsage() {
        return memory;
    }

    @Override
    public long getLoopRuns() {
        return loopRuns;
    }
}

