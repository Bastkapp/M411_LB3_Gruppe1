package sorting.algorithms.mergesort;
import sorting.algorithms.ISortAlgorithm;
import sorting.SortArray;
/**
 * @author noé
 * @version 0.8
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
    /* Opens up Sort Method */
    sort(array, 0, len - 1);
    double endTime = System.nanoTime();
    this.duration = endTime - startTime;
    this.memory = rt.totalMemory() - rt.freeMemory();
  }
  /**
   *
   * @param array
   * @param l
   * @param r
   */
  // Main function that sorts arr[l..r] using
  // merge()
  public void sort(SortArray array, int l, int r) {
    if (l < r) {
      // Find the middle point
      int m = (l + r) / 2;
      // Sort first and second halves
      sort(array, l, m);
      sort(array, m + 1, r);
      // Merge the sorted halves
      merge(array, l, m, r);
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
    /* Create temporary arrays */
    int leftArray[] = new int[leftSize];
    int rightArray[] = new int[rightSize];
    /* Fills the left array */
    for (int i = 0; i < leftSize; ++i) {
      leftArray[i] = array.getValue(left + i);
    }
    /* Fills the right array */
    for (int j = 0; j < rightSize; ++j) {
      rightArray[j] = array.getValue(mid + 1 + j);
    }
    /* Merge the temp arrays */
    // Initial indexes of first and second sub-arrays
    int i = 0, j = 0;
    // Initial index of merged subarray
    int k = left;
    while (i < leftSize && j < rightSize) {
      if (leftArray[i] <= rightArray[j]) {
        array.setValue(k, leftArray[i]);
        i++;
      } else {
        array.setValue(k, rightArray[j]);
        j++;
      }
      k++;
    }
    /* Copy remaining elements of left Array if any */
    while (i < leftSize) {
      array.setValue(k, leftArray[i]);
      i++;
      k++;
    }
    /* Copy remaining elements of right Array if any */
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