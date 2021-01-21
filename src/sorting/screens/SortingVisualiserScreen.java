package sorting.screens;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.SwingWorker;
import sorting.algorithms.ISortAlgorithm;
import sorting.MainApp;
import sorting.SortArray;

/**
 * The main class for the sort visualiser GUI
 *
 * @author Matt Hopson
 */
public final class SortingVisualiserScreen extends Screen {

  private final SortArray sortArray;
  private final ArrayList<ISortAlgorithm> sortQueue;

  /**
   * Creates the GUI
   *
   * @param algorithms List of algorithms to run for visualisation
   * @param app        The main application
   */
  public SortingVisualiserScreen(ArrayList<ISortAlgorithm> algorithms, MainApp app) {
    super(app);
    setLayout(new BorderLayout());
    sortArray = new SortArray(getData());
    add(sortArray, BorderLayout.CENTER);
    sortQueue = algorithms;
  }

  private int[] getData() {
    String[] lines = null;
    try {
      // File path from Repository Root
      lines = Files.readAllLines(new File("src\\sorting\\data\\10Digits.dat").toPath()).toArray(new String[0]);
    } catch (IOException e) {
      e.printStackTrace();
    }

    assert lines != null;
    int[] data = new int[lines.length];

    for (int i = 0; i < lines.length; i++) {
      String line = lines[i].replaceAll("\\D+","");
      if (!line.equals(""))
        data[i] = Integer.parseInt(line);
    }

    return data;

  }

  private void longSleep() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }

  public void onOpen() {
    //This would block the EventDispatchThread, and so
    //it must run on a worker thread
    SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
      @Override
      protected Void doInBackground() throws Exception {
        try {
          Thread.sleep(250);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
        for (ISortAlgorithm algorithm : sortQueue) {

          sortArray.setName(algorithm.getName());
          sortArray.setAlgorithm(algorithm);

          System.out.println(Arrays.toString(sortArray.getArray()));

          algorithm.runSort(sortArray);
          System.out.println("Algorithm: " + algorithm.getName());
          System.out.println("Time: " + algorithm.getDuration());
          System.out.println("Changes: " + algorithm.getAmountOfChanges());

          System.out.println(Arrays.toString(sortArray.getArray()));

          longSleep();
        }
        return null;
      }

      @Override
      public void done() {
        app.popScreen();
      }
    };

    swingWorker.execute();
  }
}
