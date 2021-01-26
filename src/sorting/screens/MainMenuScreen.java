package sorting.screens;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import sorting.MainApp;
import sorting.algorithms.AlgorithmManager;
import sorting.algorithms.ISortAlgorithm;

/**
 * @author Bastian Kappeler
 */
public final class MainMenuScreen extends Screen {

  private final ArrayList<AlgorithmCheckBox> checkBoxes;

  public MainMenuScreen(MainApp app) {
    super(app);
    checkBoxes = new ArrayList<>();
    setUpGUI();
  }

  private void addCheckBox(ISortAlgorithm algorithm, JPanel panel) {
    JCheckBox checkBox = new JCheckBox("", true);
    checkBox.setAlignmentX(LEFT_ALIGNMENT);
    checkBox.setBackground(BACKGROUND_COLOUR);
    checkBoxes.add(new AlgorithmCheckBox(algorithm, checkBox));
    panel.add(checkBox);
  }

  private void initContainer(JPanel p) {
    p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
    p.setBackground(BACKGROUND_COLOUR);
  }

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
          new DitigsChooser(
              algorithms,
              app
          ));
    });
    startButton.setAlignmentX(CENTER_ALIGNMENT);

    outerContainer.add(optionsContainer);
    outerContainer.add(Box.createRigidArea(new Dimension(5, 0)));
    outerContainer.add(sortAlgorithmContainer);

    int gap = 15;
    add(Box.createRigidArea(new Dimension(0, gap)));
    add(outerContainer);
    add(Box.createRigidArea(new Dimension(0, gap)));
    add(startButton);
  }

  @Override
  public void onOpen() {
    checkBoxes.forEach(AlgorithmCheckBox::unselect);
  }

  private class AlgorithmCheckBox {

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
