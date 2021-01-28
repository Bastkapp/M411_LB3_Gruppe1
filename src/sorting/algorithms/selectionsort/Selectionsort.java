package sorting.algorithms.selectionsort;

import sorting.SortArray;
import sorting.algorithms.ISortAlgorithm;

/**
 * @Author: Marko Micanovic
 * @Date: 21.01.2021
 * @Version: 1.0
 * <p>
 * Hier habe ich den SelectionSort übernommen, welchen wir im Moodle am 14.01 behandelt haben.
 * Der Code wurde einfach so angepasst, dass es noch die Variablen für Zeit, Speicherverbrauch und Anzahl Durchläufe hat.
 * <p>
 * Ablauf:
 * Der Algorithmus funktioniert so, dass er die erste Zahl als lowestNumber festlegt. Die Variable currentNumber geht jede
 * Position im array durch und prüft, ob lowestNumber < currentNumber übereinstimmt. Falls das aber nicht übereinstimmt, wird
 * lowestNumber das array gesetzt, bei welchem der Vergleich nicht übereingestimmt hat. Die Anzahl Vergleiche und Anzahl
 * durchläufe wird hier hochgezählt. Wenn sich lowestNumber dann nicht mehr ändert und currentNumber die ganze Liste durch-
 * gegangen ist, dann wird am schluss der Wert von der ersten Position mit dem kleinsten Wert vertauscht und die Länge des
 * arrays verkürzt sich somit um 1 Eintrag, sodass die sortierten Werte nicht mehrmals überprüft werden.
 * <p>
 * <p>
 * Best-, Worst- und Average-Case:
 * Der Selectionsort geht immer die ganze Liste durch, weshalb Best-, Worst- und Average-Case auch alle den gleichen Wert
 * haben. Der Sort geht immer eine fixe Anzahl vergleiche durch, weshalb sich die Fälle von der Zeit her nicht unterscheiden.
 * Auch wenn die Liste schon ganz sortiert wäre, würde der Sort durch das ganze Array gehen. Also ist die Zeitkomplexität für
 * alle 3 Fälle O(n^2).
 * <p>
 * <p>
 * Stabilität:
 * Der Algorithmus ist nicht stabil, weil die Werte während dem durchlaufen immer wieder vertauscht werden und sich deshalb
 * die Reihenfolge von gleichen Werten ändern kann.
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


    @Override
    public void runSort(SortArray array) {
        long timeBefore = System.nanoTime();
        int lowestNumber, currentNumber;
        Runtime rt = Runtime.getRuntime();
        for (int i = 0; i < array.arraySize(); i++) {
            lowestNumber = array.getValue(i);
            currentNumber = i;
            for (int j = i; j < array.arraySize(); j++) {
                this.amountOfComparisons++;
                if (array.getValue(j) < lowestNumber) {
                    lowestNumber = array.getValue(j);
                    currentNumber = j;
                }
                this.loopRuns++;
            }
            if (lowestNumber < array.getValue(i)) {
                this.amountOfComparisons++;
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

