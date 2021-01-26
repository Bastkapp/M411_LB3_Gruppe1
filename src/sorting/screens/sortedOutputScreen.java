package sorting.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import sorting.MainApp;
import sorting.SortArray;
import sorting.algorithms.ISortAlgorithm;
import sorting.data.DataLoader;

/**
 * The main class for the sort visualiser GUI
 *
 * @author Bastian Kappeler
 */
public final class sortedOutputScreen extends Screen {

  private final SortArray sortArray;
  private final ArrayList<ISortAlgorithm> sortAlgorithms;
  private final int[] unsorted;
  private static final int GAP = 15;

  /**
   * Creates the GUI
   *
   * @param algorithms List of algorithms to run for visualisation
   * @param app        The main application
   */
  public sortedOutputScreen(ArrayList<ISortAlgorithm> algorithms, MainApp app) {
    super(app);
    setLayout(new BorderLayout());
    unsorted = DataLoader.get();
    sortArray = new SortArray(unsorted);
    sortAlgorithms = algorithms;
    setUpGui();
  }

  public void setUpGui() {

    setLayout(new GridLayout());

    for (ISortAlgorithm sortAlgorithm : sortAlgorithms) {

      setBackground(BACKGROUND_COLOUR);
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

      sortAlgorithm.runSort(sortArray);

      String name = "Algorithm: " + sortAlgorithm.getName();
      String time = "Time used: " + nanosecondsToString(sortAlgorithm.getDuration());
      String changes = "Comparisons made: " + sortAlgorithm.getAmountOfComparisons();
      String loopRuns = "Loop runned: " + sortAlgorithm.getLoopRuns();
      String memory = "Memory used: " + humanReadableByteCountSI(sortAlgorithm.getMemoryUsage());

      JLabel label = new JLabel(
          "<html><p>" + name + "</p><p>" + time + "</p><p>" + changes + "</p><p>" + loopRuns
              + "</p><p>" + memory + "</p></html>");

      JLabel nameLabel = new JLabel(name);
      JLabel timeLabel = new JLabel(time);
      JLabel changesLabel = new JLabel(changes);
      JLabel memoryLabel = new JLabel(memory);

      JTable table = new JTable(getDigitList());
      JScrollPane tablePane = new JScrollPane(table);

      add(Box.createRigidArea(new Dimension(0, GAP)));
      add(label);
      add(Box.createRigidArea(new Dimension(0, GAP)));
      add(tablePane);


    }
  }

  private DigitTable getDigitList() {
    DigitTable digitList = new DigitTable();

    for (int i = 0; i < unsorted.length; i++) {
      digitList.addToList(new Digit(unsorted[i], sortArray.getValue(i)));
    }
    return digitList;
  }

  @Override
  public void onOpen() {

  }

  private static class Digit {

    int unsorted;
    int sorted;

    public Digit(int unsorted, int sorted) {
      this.unsorted = unsorted;
      this.sorted = sorted;
    }

    public int getUnsorted() {
      return unsorted;
    }

    public int getSorted() {
      return sorted;
    }
  }

  /**
   * turns bytes into readable Strings
   *
   * @param bytes the bytes you want to convert
   * @return the bytes converted into easily readable String
   * @see <a href="https://stackoverflow.com/questions/3758606/how-can-i-convert-byte-size-into-a-human-readable-format-in-java">Stackoverflow</a>
   */
  public static String humanReadableByteCountSI(long bytes) {
    if (-1000 < bytes && bytes < 1000) {
      return bytes + " B";
    }
    CharacterIterator ci = new StringCharacterIterator("kMGTPE");
    while (bytes <= -999_950 || bytes >= 999_950) {
      bytes /= 1000;
      ci.next();
    }
    return String.format("%.1f %cB", bytes / 1000.0, ci.current());
  }

  /**
   * Converts nanoseconds into easy to read time Stirng
   *
   * @param time the time in nanoseconds
   * @return the time as a String converted to the right time
   */
  public String nanosecondsToString(double time) {
    if (time < 10000) {
      return time + " nanoseconds";
    }
    if (time < 10000000) {
      return Math.floor(time / 1000 * 100) / 100 + " microseconds";
    }
    return Math.floor(time / 1000000 * 100) / 100 + " milliseconds";
  }

  /**
   * @author Michael Kuenzli
   */
  public static class DigitTable extends AbstractTableModel {

    private Vector<Digit> digitList;
    private final String[] tableTitel = {"Unsorted", "Sorted"};

    /**
     * Implements a new list of digits
     */
    public DigitTable() {
      digitList = new Vector<>();
    }

    /**
     * Adds a Digit to the list so it can be shown in the table
     *
     * @param d the digit you want to add
     */
    public void addToList(Digit d) {
      digitList.add(d);
    }

    /**
     * returns the column name at the specified index
     *
     * @param index the index you want to have the column name from
     * @return column name
     */
    @Override
    public String getColumnName(int index) {
      return tableTitel[index];
    }

    /**
     * Liefert die Anzahl Spalten in der Liste. Die Gr�sse entspricht der Anzahl Elemente f�r die
     * Spaltentitel.
     *
     * @return Anzahl der Spalten
     */
    @Override
    public int getColumnCount() {
      return tableTitel.length;
    }

    /**
     * Liefert die Anzahl der Eintr�ge in der Liste (=Zeilenzahl)
     *
     * @return Anzahl der Eintr�ge
     */
    @Override
    public int getRowCount() {
      return digitList.size();
    }

    /**
     * Liefert einen Zellenwert an der Position row/column.
     *
     * @param row    Zeile die gelesen wird
     * @param column Spalte die gelesen wird
     * @return Zellen-Wert an der Stelle row/column
     */
    @Override
    public Object getValueAt(int row, int column) {
      Digit d = digitList.get(row);
      switch (column) {
        case 0:
          return d.getUnsorted();
        case 1:
          return d.getSorted();
      }
      return null;
    }
  }
}
