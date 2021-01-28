package sorting.algorithms.mergesort;
import sorting.algorithms.ISortAlgorithm;
import sorting.SortArray;
/**
 * @author noé
 * @version 1.0
 * @date 28.01.2021
 *
 * The Merge Sort algorithm is a sorting algorithm, that takes e.g. an array and recursively splits it into two half-arrays.
 * This happens until every number is by itself. After every number has been separated they are 'merged' back together in order.
 * Simplistically said, the (here) array deconstructs itself and then slowly reconstructs until all of the numbers are in their places.
 */
public class MergeSort implements ISortAlgorithm {
  private double duration;
  private int comparisons = 0;
  private long memory = 0;
  private long loopRun = 0;
  /**
   * This method implements the merge sort algorithm
   * Takes a SortArray object called array and sorts its elements as an int
   *
   * @param array the array to be sorted
   * @see SortArray
   */
  @Override
  public void runSort(SortArray array) {
    Runtime rt = Runtime.getRuntime();
    double startTime = System.nanoTime();
    int len = array.arraySize();
    // Opens the sort method, starts the sorting algorithm
    sort(array, 0, len - 1);
    double endTime = System.nanoTime();
    this.duration = endTime - startTime;
    this.memory = rt.totalMemory() - rt.freeMemory();
  }
  /**
   *
   * @param array
   * @param left
   * @param right
   * Function that sorts the array
   */
  public void sort(SortArray array, int left, int right) {
    if (left < right) {
      int middle = (left + right) / 2; // Find the middle point of the array, gets automatically rounded due to its data type (int)
      loopRun++; // Counts up the amount of iterations
      /* At this point, the recursive element gets implemented to keep on splitting the array  */
      sort(array, left, middle); // Sorts the left side of the array
      sort(array, middle + 1, right); // Sorts the right side of the array
      merge(array, left, middle, right); // Merge the sorted halves
    }
  }
  /**
   *
   * @param array
   * @param left
   * @param mid
   * @param right
   */
  public void merge(SortArray array, int left, int mid, int right) {
    // Find sizes of two sub-arrays to be merged
    int leftSize = mid - left + 1;
    int rightSize = right - mid;
    // Create the two temporary half-arrays
    int leftArray[] = new int[leftSize];
    int rightArray[] = new int[rightSize];
    // Fills the left array
    for (int i = 0; i < leftSize; ++i) {
      leftArray[i] = array.getValue(left + i);
    }
    // Fills the right array
    for (int j = 0; j < rightSize; ++j) {
      rightArray[j] = array.getValue(mid + 1 + j);
    }
    // Starting to merge the sub-arrays
    int i = 0, j = 0;
    int k = left;
    while (i < leftSize && j < rightSize) {
      comparisons++; // Adds the comparisons for both left and right side
      if (leftArray[i] <= rightArray[j]) {
        array.setValue(k, leftArray[i]);
        i++;
      } else {
        array.setValue(k, rightArray[j]);
        j++;
      }
      k++;
    }
    // Copies the remaining elements of the left array
    while (i < leftSize) {
      array.setValue(k, leftArray[i]);
      i++;
      k++;
    }
    // Copies the remaining elements of the right array
    while (j < rightSize) {
      array.setValue(k, rightArray[j]);
      j++;
      k++;
    }
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
    return "Merge Sort";
  }
}