package sorting.algorithms.bubblesort;

import sorting.ISortAlgorithm;
import sorting.SortArray;

/**
 * Bubble sort implementation
 *
 * @author mhops
 */
public class BubbleSort implements ISortAlgorithm {

  private double duration;
  private int changes = 0;

  /**
   * This method implements the bubble sort algorithm, see
   * <a href="https://en.wikipedia.org/wiki/Bubble_sort">Bubble_sort</a> to understand more.
   * Takes a SortArray object called array and sorts his elements according to the mathematical
   * theory of the order "less than", see <a href="https://en.wikipedia.org/wiki/Order_theory">Order_theory</a>
   * to understand more.
   *
   * @param array the array to be sorted
   * @see SortArray
   */
  @Override
  public void runSort(SortArray array) {
    double startTime = System.currentTimeMillis();

    int len = array.arraySize();
    for (int i = 0; i < len - 1; i++) {
      for (int j = 0; j < len - i - 1; j++) {
        if (array.getValue(j) < array.getValue(j + 1)) {
          array.swap(j, j + 1);
          changes++;
        }
      }
    }

    double endTime = System.currentTimeMillis();

    this.duration = endTime - startTime;

  }

  @Override
  public double getDuration() {
    return this.duration;
  }

  @Override
  public int getAmountOfChanges() {
    return changes;
  }

  @Override
  public String getName() {
    return "Bubble Sort";
  }
}