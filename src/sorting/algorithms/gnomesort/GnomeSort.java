package sorting.algorithms.gnomesort;

import sorting.algorithms.ISortAlgorithm;
import sorting.SortArray;

/**
 * Gnome Sort Algorithmus
 *
 * @author Dennis Künzle
 */

public class GnomeSort {

    public static int[] gnomeSort(int list[]){
        int index = 0;

        while (index < list.length) {
           if (index == 0) {
               index++;
           }
           if (list[index] >= list[index - 1]) {
               index++;
           }
           else {
               int temp = 0;
               temp = list[index];
               list[index] = list[index - 1];
               list[index - 1] = temp;
               index --;
           }
        }

        return list;
    }


}
