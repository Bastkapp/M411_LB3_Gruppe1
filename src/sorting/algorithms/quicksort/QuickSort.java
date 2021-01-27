package sorting.algorithms.quicksort;

import sorting.SortArray;
import sorting.algorithms.ISortAlgorithm;

/**
 * Quick sort implementation
 *
 * @author Bastian Kappeler
 */
public class QuickSort implements ISortAlgorithm {

  private double duration;
  private int comparisons = 0;
  private long memory = 0;
  private long loopRun = 0;

  private SortArray array;

  /**
   * finds the pivot point
   *
   * @param array     this is the array to cut and merge
   * @param lowIndex  the most left index of the array
   * @param highIndex the most right index of the array
   * @see SortArray
   */
  private int findPivotPoint(SortArray array, int lowIndex, int highIndex) {
    int pivotValue = array.getValue(highIndex);
    int i = lowIndex - 1;
    for (int j = lowIndex; j <= highIndex - 1; j++) {
      comparisons++;
      if (array.getValue(j) <= pivotValue) {
        i++;
        array.swap(i, j);
      }
      loopRun++;
    }
    array.swap(i + 1, highIndex);
    return i + 1;
  }

  /**
   * This is the core of the algorithm quick sort.
   *
   * @param array     this is the array to cut and merge
   * @param lowIndex  the most left index of the array
   * @param highIndex the most right index of the array
   * @see SortArray
   */
  private void quickSort(SortArray array, int lowIndex, int highIndex) {
    if (!isSorted()) return;
    comparisons++;
    if (lowIndex < highIndex) {
      int pivotPoint = findPivotPoint(array, lowIndex, highIndex);
      quickSort(array, lowIndex, pivotPoint - 1);
      quickSort(array, pivotPoint + 1, highIndex);
    }
  }

  /**
   * This is the method that call the first instance of quickSort The method takes a SortArray
   * object called array and sorts his elements
   *
   * @param array the array to be sorted
   * @see SortArray
   */
  @Override
  public void runSort(SortArray array) {
    this.array = array;

    Runtime runtime = Runtime.getRuntime();

    runtime.gc();

    long startMemory = runtime.totalMemory() - runtime.freeMemory();
    double startTime = System.nanoTime();

    quickSort(array, 0, array.arraySize() - 1);

    double endTime = System.nanoTime();
    long endMemory = runtime.totalMemory() - runtime.freeMemory();

    this.duration = endTime - startTime;
    
    this.memory = endMemory - startMemory;
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
    return duration;
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
    return "Quick Sort";
  }

}
