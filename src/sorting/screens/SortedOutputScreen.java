package sorting.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import sorting.MainApp;
import sorting.SortArray;
import sorting.algorithms.ISortAlgorithm;
import sorting.data.DataLoader;

/**
 * The visualisation class of the sorted data and the algorithms
 *
 * @author Bastian Kappeler
 */
public final class SortedOutputScreen extends Screen {

  private final SortArray sortArray;
  private final ArrayList<ISortAlgorithm> sortAlgorithms;
  private final int[] unsorted;
  private static final int GAP = 15;

  /**
   * Creates the GUI screen
   *
   * @param algorithms List of algorithms to run for visualisation
   * @param app        The main application
   */
  public SortedOutputScreen(ArrayList<ISortAlgorithm> algorithms, MainApp app) {
    super(app);
    setLayout(new BorderLayout());

    // Gets the unsorted array
    unsorted = DataLoader.get();
    sortArray = new SortArray(unsorted);
    sortAlgorithms = algorithms;
    setUpGui();
  }

  /**
   * Sets up the GUI so it can be shown, shows it at the end
   */
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

      JTable table = new JTable(getDigitList());
      JScrollPane tablePane = new JScrollPane(table);

      add(Box.createRigidArea(new Dimension(0, GAP)));
      add(label);
      add(Box.createRigidArea(new Dimension(0, GAP)));
      add(tablePane);
    }

    JButton backButton = new JButton("Back");
    backButton
        .addActionListener(
            (ActionEvent e) -> app.pushScreen(new DigitsChooserScreen(sortAlgorithms, app)));
    backButton.setAlignmentX(CENTER_ALIGNMENT);

    add(backButton);

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
    if (time < 1000) {
      return time + " nanoseconds";
    }
    if (time < 1000000) {
      return Math.floor(time / 1000 * 100) / 100 + " microseconds";
    }
    return Math.floor(time / 1000000 * 100) / 100 + " milliseconds";
  }

  /**
   * onOpen method forced to import by the screen but not used
   */
  @Override
  public void onOpen() {
  }

  /**
   * Creates a Table from all the digits sorted and unsorted to display in a JTable
   *
   * @return table of all digits sorted and unsorted
   */
  private DigitTable getDigitList() {
    DigitTable digitList = new DigitTable();

    for (int i = 0; i < unsorted.length; i++) {
      digitList.addToList(new Digit(unsorted[i], sortArray.getValue(i)));
    }
    return digitList;
  }

  /**
   * Class used for easy use in the Table
   */
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
   * This class is used to create a Table of the digits The main part of it came from Michael
   * Kuenzli and his group task
   */
  public static class DigitTable extends AbstractTableModel {

    private Vector<Digit> digitList;
    private final String[] columnNames = {"Unsorted", "Sorted"};

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
      return columnNames[index];
    }

    /**
     * returns the number of columns in the table, here as the length of the column names
     *
     * @return number of columns
     */
    @Override
    public int getColumnCount() {
      return columnNames.length;
    }

    /**
     * returns the amount of entries in the table
     *
     * @return amount of entries
     */
    @Override
    public int getRowCount() {
      return digitList.size();
    }

    /**
     * returns the value of the cell specified cell
     *
     * @param row    row to read
     * @param column column to read
     * @return value of the cell
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
