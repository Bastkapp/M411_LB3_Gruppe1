package sorting.screens;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import sorting.MainApp;
import sorting.algorithms.ISortAlgorithm;
import sorting.data.DataLoader;


/**
 * @author Bastian Kappeler
 */
public final class DitigsChooser extends Screen {

  private final ArrayList<DigitRadioButton> checkBoxes;
  private final ArrayList<ISortAlgorithm> algorithm;

  public DitigsChooser(ArrayList<ISortAlgorithm> algorithm, MainApp app) {
    super(app);
    this.algorithm = algorithm;
    checkBoxes = new ArrayList<>();
    setUpGUI();
  }

  private void addCheckBox(int digit, JPanel panel, ButtonGroup buttonGroup) {
    JRadioButton button = new JRadioButton("", true);
    button.setAlignmentX(LEFT_ALIGNMENT);
    button.setBackground(BACKGROUND_COLOUR);
    checkBoxes.add(new DigitRadioButton(digit, button));
    buttonGroup.add(button);
    panel.add(button);
  }

  private void initContainer(JPanel p) {
    p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
    p.setBackground(BACKGROUND_COLOUR);
  }

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

    ButtonGroup digitButtonGroup = new ButtonGroup();

    // to use sorter, create a new sorter here
    addCheckBox(10, digitContainer, digitButtonGroup);
    addCheckBox(100, digitContainer, digitButtonGroup);
    addCheckBox(1000, digitContainer, digitButtonGroup);

    JButton backButton = new JButton("Back");
    backButton.addActionListener((ActionEvent e) -> app.pushScreen(new MainMenuScreen(app)));
    backButton.setAlignmentX(CENTER_ALIGNMENT);

    JButton startButton = new JButton("Sort");
    startButton.addActionListener((ActionEvent e) -> {
      for (DigitRadioButton cb : checkBoxes) {
        if (cb.isSelected()) {
          DataLoader.amount = cb.getDigit();
        }
      }
      app.pushScreen(
          new sortedOutputScreen(
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

    int gap = 15;
    add(Box.createRigidArea(new Dimension(0, gap)));
    add(outerContainer);
    add(buttonContainer);
  }

  @Override
  public void onOpen() {
    checkBoxes.forEach(DigitRadioButton::unselect);
  }

  private class DigitRadioButton {

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
