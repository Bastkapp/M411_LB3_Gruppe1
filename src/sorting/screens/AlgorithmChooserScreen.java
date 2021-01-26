package sorting.screens;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sorting.MainApp;
import sorting.algorithms.AlgorithmManager;
import sorting.algorithms.ISortAlgorithm;

/**
 * screen to select the algorithms you want to look at
 *
 * @author Bastian Kappeler
 */
public final class AlgorithmChooserScreen extends Screen {

  private final ArrayList<AlgorithmCheckBox> checkBoxes;

  /**
   * Creates the screen
   *
   * @param app the main application
   */
  public AlgorithmChooserScreen(MainApp app) {
    super(app);
    checkBoxes = new ArrayList<>();
    setUpGUI();
  }

  /**
   * Creates a checkbox for the algorithm and sets it into the panel
   *
   * @param algorithm the algorithm that has to be set to this checkBox
   * @param panel     the panel where the checkbox has to be set
   */
  private void addCheckBox(ISortAlgorithm algorithm, JPanel panel) {
    JCheckBox checkBox = new JCheckBox("", true);
    checkBox.setAlignmentX(LEFT_ALIGNMENT);
    checkBox.setBackground(BACKGROUND_COLOUR);
    checkBoxes.add(new AlgorithmCheckBox(algorithm, checkBox));
    panel.add(checkBox);
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
   * Sets up the GUI to choose the algorithm
   */
  public void setUpGUI() {
    JPanel sortAlgorithmContainer = new JPanel();
    JPanel optionsContainer = new JPanel();
    JPanel outerContainer = new JPanel();
    initContainer(this);
    initContainer(optionsContainer);
    initContainer(sortAlgorithmContainer);

    outerContainer.setBackground(BACKGROUND_COLOUR);
    outerContainer.setLayout(new BoxLayout(outerContainer, BoxLayout.LINE_AXIS));

    sortAlgorithmContainer.setAlignmentX(CENTER_ALIGNMENT);

    // For every algorithm in the AlgorithmManager a checkBox gets created
    for (ISortAlgorithm algorithm : AlgorithmManager.getAlgorithms()) {
      addCheckBox(algorithm, sortAlgorithmContainer);
    }

    JButton startButton = new JButton("Choose Amount of Digits to sort");
    startButton.addActionListener((ActionEvent e) -> {
      ArrayList<ISortAlgorithm> algorithms = new ArrayList<>();
      for (AlgorithmCheckBox cb : checkBoxes) {
        if (cb.isSelected()) {
          algorithms.add(cb.getAlgorithm());
        }
      }
      app.pushScreen(
          new DigitsChooserScreen(
              algorithms,
              app
          ));
    });
    startButton.setAlignmentX(CENTER_ALIGNMENT);

    outerContainer.add(optionsContainer);
    outerContainer.add(Box.createRigidArea(new Dimension(5, 0)));
    outerContainer.add(sortAlgorithmContainer);

    JLabel title = new JLabel("<html><h1>Select the algorithms you want to sort with</h1></html>");

    int gap = 15;
    add(Box.createRigidArea(new Dimension(0, gap)));
    add(title);
    add(Box.createRigidArea(new Dimension(0, gap)));
    add(outerContainer);
    add(Box.createRigidArea(new Dimension(0, gap)));
    add(startButton);
  }

  /**
   * sets every checkBox to unselected
   */
  @Override
  public void onOpen() {
    checkBoxes.forEach(AlgorithmCheckBox::unselect);
  }

  /**
   * Gets created for the checkBox so you can see, what algorithm was selected
   */
  private static class AlgorithmCheckBox {

    private final ISortAlgorithm algorithm;
    private final JCheckBox box;

    public AlgorithmCheckBox(ISortAlgorithm algorithm, JCheckBox box) {
      this.algorithm = algorithm;
      this.box = box;
      this.box.setText(algorithm.getName());
    }

    public void unselect() {
      box.setSelected(false);
    }


    public boolean isSelected() {
      return box.isSelected();
    }

    public ISortAlgorithm getAlgorithm() {
      return algorithm;
    }
  }

}
