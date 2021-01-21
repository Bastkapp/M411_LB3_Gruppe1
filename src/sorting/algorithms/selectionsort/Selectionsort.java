package sorting.algorithms.selectionsort;

import sorting.algorithms.ISortAlgorithm;
import sorting.SortArray;

/**
 * @Author: Marko Micanovic
 * @Version: 1.0
 */

public class Selectionsort implements ISortAlgorithm {

    public static void main(String[] args) {
        int[] list = {4, 9, 2, 5, 1};


        printToSort(SelectionSort(list));
    }



    public static int[] SelectionSort(int[] list) {
        int i = 0, j;
        while (i != list.length) {
            int index_of_smallest = i;
            for (j = i; j < list.length; j++) {
                if (list[j] < list[index_of_smallest]) {
                    index_of_smallest = j;
                }
                int temp = list[i];
                list[i] = list[index_of_smallest];
                list[index_of_smallest] = temp;
                i++;
            }
        }
        return list;
    }


    public static void printToSort(int[] list) {
        for (int i = 0;  i < list.length -1; i++) {
            System.out.println(list[i]);
        }
        System.out.println(list[list.length -1]);
    }


    @Override
    public String getName() {
        String name = "SelectionSort";
        return name;
    }

    @Override
    public void runSort(SortArray array) {

    }

    @Override
    public double getDuration() {
        return 0;
    }

    @Override
    public int getAmountOfChanges() {
        return 0;
    }
}
