package sorting.algorithms.gnomesort;

import sorting.algorithms.ISortAlgorithm;
import sorting.SortArray;

/**
 * Gnome Sort Algorithmus
 *
 * @author Dennis Künzle
 * @date 21.01.2021
 * @version 1.0
 *
 * Der Gnome Sort Algorithmus basiert auf dem Prinzip, dass ein Gartenzwerg(Gnome) Blumentöpfe der Grösse nach sortiert.
 * Der "Zwerg" beginnt ganz links und arbeitet sich nach recht vor. Dabei schaut er sich immer zwei Töpfe an.
 * Stimmt die Reihenfolge, geht er einen Topf weiter und vergleicht die wieder miteinander.
 * Wenn die Reihenfolge nicht stimmt, also der linke Topf ist grösser als der rechte Topf so werden diese ausgetauscht.
 *
 * Die Struktur ist sehr simpel aufgebaut. Es gibt eine while Schleife die von der ersten Zahl bis zur letzten Zahl durcharbeitet.
 * in der while schlaufe befinden sich zwei if und eine else Schlaufe. Die sind zum Überprüfen ob die linke oder rechte Zahl grösser ist.
 * Die Schleifen haben alle drei Zenarien umgesetzt: was wenn die Zahlen gleich gross sind, was wenn die linke Zahl kleiner ist als die rechte und was wenn die linke Zahl grösser ist als die rechte.
 * Wenn die Reihenfolge falsch ist geht der "Gnome" nach links bis der Topf an der richtigen Stelle ist.
 *
 * Der Gnome Sort gehört zu der Kathegorie der stabilen Algorithmen.
 *
 * Best-case: O(n)
 * Worst-case: O(n^2)
 * Average-case: O(n^2)
 */

public class GnomeSort implements ISortAlgorithm{

    private double duration;
    private int comparison = 0;
    private int loopRun = 0;
    private long memory = 0;

    @Override
    public void runSort(SortArray array) {
        Runtime runtime = Runtime.getRuntime();

        double startTime = System.nanoTime();

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
        double endTime = System.nanoTime();

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