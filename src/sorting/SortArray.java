package sorting;

import java.util.Arrays;

/**
 * Class with the array including the data for the sorting algorithms to use Contains methods for
 * the sorting algorithms to work with
 *
 * @author Bastian Kappeler
 */
public class SortArray {

  private final int[] array;

  /**
   * Creates a fresh copy of the array of unsorted data
   *
   * @param array the array containing the data
   */
  public SortArray(int[] array) {
    this.array = Arrays.copyOf(array, array.length);
  }

  /**
   * Returns the size of the array with the data
   *
   * @return the size of the array
   */
  public int arraySize() {
    return array.length;
  }

  /**
   * Gets the value form the specified position out of the array with the data
   *
   * @param index position of the value you want to get
   * @return the value at the specified position
   */
  public int getValue(int index) {
    try {
      return array[index];
    } catch (IndexOutOfBoundsException e) {
      return -1;
    }
  }

  /**
   * Swaps two specified values of the array
   *
   * @param index1 position of first value
   * @param index2 position of second value
   */
  public void swap(int index1, int index2) {
    int temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }

  /**
   * Sets a value to a specified position in the array This method does not get used often, you
   * mostly just swap two values with the {@code sorting.SortArray.swap}
   *
   * @param index the position where the value has to be set
   * @param value the value that has to be set
   */
  public void setValue(int index, int value) {
    array[index] = value;
  }
}
