package sorting.algorithms.selectionsort;

import sorting.algorithms.ISortAlgorithm;
import sorting.SortArray;

/**
 * @Author: Marko Micanovic
 * @Version: 1.0
 */

public class Selectionsort implements ISortAlgorithm {
    private int swaps = 0;


    @Override
    public String getName() {
        return "SelectionSort";
    }

    /**
     * Hier habe ich den SelectionSort übernommen, welchen wir im Moodle am 14.01 behandelt haben.
     * Der Code wurde einfach so angepasst, dass es noch die Variablen für Zeit, Swaps und Anzahl Durchläufe hat.
     */

    @Override
    public void runSort(SortArray array) {
        int minValue, minIndex;

        for (int i = 0; i < array.arraySize(); i++) {
            minValue = array.getValue(i);
            minIndex = i;
            for (int j = i; j < array.arraySize(); j++) {
                if (array.getValue(j) < minValue) {
                    minValue = array.getValue(j);
                    minIndex = j;
                }
            }
            if (minValue < array.getValue(i)) {
                array.swap(array.getValue(i), minIndex);
                swaps++;
            }
        }

        for (int k = 0;  k < array.arraySize() -1; k++) {
            System.out.println(array.getValue(k));
        }
    }

    @Override
    public double getDuration() {
        return 0;
    }

    @Override
    public int getAmountOfChanges() {
        return swaps;
    }
}

