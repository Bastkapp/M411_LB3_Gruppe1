package sorting.algorithms;

import sorting.algorithms.bubblesort.BubbleSort;
import sorting.algorithms.gnomesort.GnomeSort;
import sorting.algorithms.selectionsort.Selectionsort;

/**
 * This class is her to provide an easy implementation of new sort Algorithms in the program
 *
 * @author Bastian Kappeler
 */
public class AlgorithmManager {

  private static final ISortAlgorithm[] algorithms = new ISortAlgorithm[]{

      // Add algorithm that implements ISortAlgorithm here to include it to the GUI
      new BubbleSort(),
      new GnomeSort(),
      new Selectionsort()
  };

  public static ISortAlgorithm[] getAlgorithms() {
    return algorithms;
  }
}
