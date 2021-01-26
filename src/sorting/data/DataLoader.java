package sorting.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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

  private static void amount(int amount) {
    DataLoader.amount = amount;
  }

  private static int[] loadData(String url) {
    String[] lines = null;
    try {
      // File path from Repository Root
      lines = Files.readAllLines(new File(url).toPath()).toArray(new String[0]);
    } catch (IOException e) {
      e.printStackTrace();
    }

    assert lines != null;
    int[] data = new int[lines.length];

    for (int i = 0; i < lines.length; i++) {
      String line = lines[i].replaceAll("\\D+", "");
      if (!line.equals("")) {
        data[i] = Integer.parseInt(line);
      }
    }

    return data;
  }


}
