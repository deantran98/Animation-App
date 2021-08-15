package cs3500.animator;

import cs3500.animator.controller.Controller;
import cs3500.animator.controller.IController;
import cs3500.animator.controller.InteractiveController;
import cs3500.animator.model.AnimatorModel.Builder;
import cs3500.animator.model.IAnimatorApp;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.util.ViewFactory;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.InteractiveView;
import cs3500.animator.view.VisualAnimationView;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A main class as an entrance of the animation model.
 */
public class Excellence {

  /**
   * A main class as an entrance of the animation model.
   */
  public static void main(String[] args) throws IOException {

    AnimationReader reader = new AnimationReader();
    Readable fileIn = null;
    Appendable fileOut = System.out;
    int tickPerSec = 1;
    String viewType = "";

    for (int i = 0; i < args.length; i++) {

      if (args[i].equals("-in") && i < args.length - 1) {
        fileIn = new FileReader(args[i + 1]);
      }

      if (args[i].equals("-out") && i < args.length - 1) {
        fileOut = new FileWriter(args[i + 1]);
      }

      if (args[i].equals("-view") && i < args.length - 1) {
        viewType += args[i + 1];
      }

      if (args[i].equals("-speed") && i < args.length - 1) {
        tickPerSec = Integer.parseInt(args[i + 1]);
      }

    }

    if (tickPerSec < 1) {
      JFrame frame = new JFrame();
      JOptionPane.showMessageDialog(frame, "Tick per sec can't be negative.");
    }

    IAnimatorApp model = reader.parseFile(fileIn, new Builder());
    AnimatorView view = new ViewFactory().createView(viewType, model, tickPerSec, fileOut);

    if (viewType.equals("text") || viewType.equals("svg")) {
      view.render();
    } else if (viewType.equals("visual")) {
      IController controller = new Controller((VisualAnimationView) view, model);
      controller.startAnimation();
    } else if (viewType.equals("interactive")) {
      IController controller = new InteractiveController((InteractiveView) view, model);
      controller.startAnimation();
    } else {
      JFrame frame = new JFrame();
      JOptionPane.showMessageDialog(frame, "Invalid view type.");
    }

  }

}
