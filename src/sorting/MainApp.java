package sorting;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import sorting.screens.AlgorithmChooserScreen;
import sorting.screens.Screen;

/**
 * The main application point for controlling the program The GUI part of this application is
 * inspired by the following git repository. Nearly everything was changed but form there came the
 * basic idea
 *
 * @author Bastian Kappeler
 * @see <a href="https://github.com/Hopson97/Sort-Algorithm-Visualiser.git">Hopson97
 * Sort-Algorithm-Visualiser Github</a>
 */
public class MainApp {

  private final JFrame window;

  public static final int WIN_WIDTH = 500;
  public static final int WIN_HEIGHT = 300;

  private final ArrayList<Screen> screens;

  /**
   * Creates a new window
   */
  public MainApp() {
    screens = new ArrayList<>();
    window = new JFrame("Sorting by Group 1");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setVisible(true);
  }

  /**
   * returns the current active screen
   *
   * @return the current active screen
   */
  public Screen getCurrentScreen() {
    return screens.get(screens.size() - 1);
  }

  /**
   * replaces the currently active screen in the window with a new screen
   *
   * @param screen the screen to be showed
   */
  public void pushScreen(Screen screen) {
    if (!screens.isEmpty()) {
      window.remove(getCurrentScreen());
    }
    screens.add(screen);
    window.setContentPane(screen);
    window.validate();
    screen.onOpen();
  }

  /**
   * a start method to start the Main application
   */
  public void start() {
    pushScreen(new AlgorithmChooserScreen(this));
    window.pack();
  }

  /**
   * The method to run the class
   *
   * @param args args
   */
  public static void main(String[] args) {
    System.setProperty("sun.java2d.opengl", "true");
    SwingUtilities.invokeLater(() -> {
      new MainApp().start();
    });
  }
}
