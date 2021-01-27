package sorting.algorithms.bubblesort;

import sorting.SortArray;
import sorting.algorithms.ISortAlgorithm;

/**
 * Bubble sort implementation
 *
 * @author Bastian Kappeler
 */
public class BubbleSort implements ISortAlgorithm {

  private double duration;
  private int comparisons = 0;
  private long memory = 0;
  private long loopRun = 0;

  private SortArray array;

  /**
   * This method implements the bubble sort algorithm Takes a SortArray object called array and
   * sorts his elements as an int
   *
   * @param array the array to be sorted
   * @see SortArray
   */
  @Override
  public void runSort(SortArray array) {
    this.array = array;
    Runtime rt = Runtime.getRuntime();

    double startTime = System.nanoTime();

    bubbleSort();

    double endTime = System.nanoTime();

    this.duration = endTime - startTime;

    this.memory = rt.totalMemory() - rt.freeMemory();

  }

  private void bubbleSort() {
    int len = array.arraySize();
    for (int i = 0; i < len - 1 && !isSorted(); i++) {
      for (int j = 0; j < len - i - 1; j++) {
        comparisons++;
        if (array.getValue(j) > array.getValue(j + 1)) {
          array.swap(j, j + 1);
        }
        loopRun++;
      }
    }
  }

  /**
   * Checks if the array is sorted and returns a boolean regarding the sorted state
   *
   * @return boolean if array is sorted
   */
  private boolean isSorted() {
    for (int i = 0; i < array.arraySize() - 1; i++) {
      comparisons++;
      if (array.getValue(i) > array.getValue(i + 1)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public double getDuration() {
    return this.duration;
  }

  @Override
  public int getAmountOfComparisons() {
    return comparisons;
  }

  @Override
  public long getMemoryUsage() {
    return memory;
  }

  @Override
  public long getLoopRuns() {
    return loopRun;
  }

  @Override
  public String getName() {
    return "Bubble Sort";
  }
}