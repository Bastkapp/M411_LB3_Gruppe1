package sorting.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * This class loads the digits it gets asked for
 *
 * @author Bastian Kappeler
 */
public class DataLoader {

  public static final String url10 = "src/sorting/data/10Digits.dat";
  public static final String url100 = "src/sorting/data/100Digits.dat";
  public static final String url1000 = "src/sorting/data/1000Digits.dat";

  public static int[] small = null;
  public static int[] medium = null;
  public static int[] large = null;

  public static int amount = 0;

  /**
   * returns the digits of the file
   *
   * @return the specified values of the file
   */
  public static int[] get() {
    if (amount == 10) {
      if (small == null) {
        small = loadData(url10);
      }
      return small;
    }

    if (amount == 100) {
      if (medium == null) {
        medium = loadData(url100);
      }
      return medium;
    }

    if (large == null) {
      large = loadData(url1000);
    }
    return large;
  }

  /**
   * loads the data form the specified file and converts it to a int[]
   *
   * @param url the path to the file to load
   * @return the data as int[]
   */
  private static int[] loadData(String url) {
    String[] lines = null;
    try {
      lines = Files.readAllLines(new File(url).toPath()).toArray(new String[0]);
    } catch (IOException e) {
      e.printStackTrace();
    }

    ArrayList<Integer> data = new ArrayList<>();

    for (String line : lines != null ? lines : new String[0]) {
      line = line.replaceAll("\\D+", "");
      if (!line.equals("")) {
        data.add(Integer.parseInt(line));
      }
    }

    int[] output = new int[data.size()];
    for (int i=0; i < output.length; i++)
    {
      output[i] = data.get(i);
    }

    return output;
  }


}
