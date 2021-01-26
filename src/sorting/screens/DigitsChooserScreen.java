package sorting.screens;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import sorting.MainApp;
import sorting.algorithms.ISortAlgorithm;
import sorting.data.DataLoader;


/**
 * screen to select the digits you want to sort
 *
 * @author Bastian Kappeler
 */
public final class DigitsChooserScreen extends Screen {

  private final ArrayList<DigitRadioButton> checkBoxes;
  private final ArrayList<ISortAlgorithm> algorithm;

  /**
   * Creates the GUI screen
   *
   * @param algorithms List of algorithms to run for visualisation
   * @param app        The main application
   */
  public DigitsChooserScreen(ArrayList<ISortAlgorithm> algorithms, MainApp app) {
    super(app);
    this.algorithm = algorithms;
    checkBoxes = new ArrayList<>();
    setUpGUI();
  }

  /**
   * Creates a radiobutton for the digit and sets it into the panel
   *
   * @param digit       the digit that has to be set to this checkBox
   * @param panel       the panel where the radiobutton has to be set
   * @param buttonGroup the buttongroup the radiobutton has to be set to
   */
  private void addRaioButton(int digit, JPanel panel, ButtonGroup buttonGroup) {
    JRadioButton button = new JRadioButton("", true);
    button.setAlignmentX(LEFT_ALIGNMENT);
    button.setBackground(BACKGROUND_COLOUR);
    checkBoxes.add(new DigitRadioButton(digit, button));
    buttonGroup.add(button);
    panel.add(button);
  }

  /**
   * initialises the Container
   *
   * @param p panel to be initialised
   */
  private void initContainer(JPanel p) {
    p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
    p.setBackground(BACKGROUND_COLOUR);
  }

  /**
   * Sets up the GUI to choose the file to read the digits from
   */
  public void setUpGUI() {
    JPanel digitContainer = new JPanel();
    JPanel optionsContainer = new JPanel();
    JPanel outerContainer = new JPanel();
    JPanel buttonContainer = new JPanel();
    initContainer(this);
    initContainer(optionsContainer);
    initContainer(digitContainer);

    outerContainer.setBackground(BACKGROUND_COLOUR);
    outerContainer.setLayout(new BoxLayout(outerContainer, BoxLayout.LINE_AXIS));

    buttonContainer.setBackground(BACKGROUND_COLOUR);
    buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.LINE_AXIS));

    digitContainer.setAlignmentX(CENTER_ALIGNMENT);

    // Needed so you can only select one radiobutton
    ButtonGroup digitButtonGroup = new ButtonGroup();

    addRaioButton(10, digitContainer, digitButtonGroup);
    addRaioButton(100, digitContainer, digitButtonGroup);
    addRaioButton(1000, digitContainer, digitButtonGroup);

    JButton backButton = new JButton("Back");
    backButton
        .addActionListener((ActionEvent e) -> app.pushScreen(new AlgorithmChooserScreen(app)));
    backButton.setAlignmentX(CENTER_ALIGNMENT);

    JButton startButton = new JButton("Sort");
    startButton.addActionListener((ActionEvent e) -> {
      for (DigitRadioButton cb : checkBoxes) {
        if (cb.isSelected()) {
          DataLoader.amount = cb.getDigit();
        }
      }
      app.pushScreen(
          new SortedOutputScreen(
              algorithm,
              app
          ));
    });
    startButton.setAlignmentX(CENTER_ALIGNMENT);

    buttonContainer.add(backButton);
    buttonContainer.add(startButton);

    outerContainer.add(optionsContainer);
    outerContainer.add(Box.createRigidArea(new Dimension(5, 0)));
    outerContainer.add(digitContainer);

    JLabel title = new JLabel("<html><h1>Select the amount of digits you want to sort</h1></html>");

    int gap = 15;
    add(Box.createRigidArea(new Dimension(0, gap)));
    add(title);
    add(Box.createRigidArea(new Dimension(0, gap)));
    add(outerContainer);
    add(Box.createRigidArea(new Dimension(0, gap)));
    add(buttonContainer);
  }

  /**
   * sets every checkBox to unselected
   */
  @Override
  public void onOpen() {
    checkBoxes.forEach(DigitRadioButton::unselect);
  }

  /**
   * Gets created for the radioButton so you can see, what digit was selected
   */
  private static class DigitRadioButton {

    private final int digit;
    private final JRadioButton button;

    public DigitRadioButton(int digit, JRadioButton button) {
      this.digit = digit;
      this.button = button;
      this.button.setText(digit + "");
    }

    public void unselect() {
      button.setSelected(false);
    }


    public boolean isSelected() {
      return button.isSelected();
    }

    public int getDigit() {
      return digit;
    }
  }

}
