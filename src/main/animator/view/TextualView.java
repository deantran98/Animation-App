package cs3500.animator.view;

import cs3500.animator.model.IAnimatorApp;
import cs3500.animator.model.IShape;
import java.io.IOException;
import java.util.Map;

/**
 * Class represent a textual view.
 */
public class TextualView implements AnimatorView {

  private final IAnimatorApp model;
  private final double tickPerSec;
  private Appendable out;

  /**
   * Constructor of the textual view.
   */
  public TextualView(IAnimatorApp model, int tickPerSec) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null.");
    }

    if (tickPerSec < 0) {
      throw new IllegalArgumentException("Tick per sec can't be negative.");
    }

    this.model = model;
    this.tickPerSec = tickPerSec;
  }

  /**
   * Constructor of the textual view that has both model and appendable.
   */
  public TextualView(IAnimatorApp model, int tickPerSec, Appendable out) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null.");
    }

    if (tickPerSec < 0) {
      throw new IllegalArgumentException("Tick per sec can't be negative.");
    }

    if (out == null) {
      throw new IllegalArgumentException("Appendable can't be null.");
    }

    this.model = model;
    this.tickPerSec = tickPerSec;
    this.out = out;
  }

  @Override
  public String toString() {

    try {

      Map<String, IShape> shapesInModel = model.getShapes();

      String renderModel = "";

      for (String str : shapesInModel.keySet()) {
        renderModel += shapesInModel.get(str).renderMotionsInfoInSecTick(tickPerSec) + "\n";
      }

      int canvasLeftMost = model.getCanvas().getLeftMost();
      int canvasTopMost = model.getCanvas().getTopMost();
      int canvasWidth = model.getCanvas().getWidth();
      int canvasHeight = model.getCanvas().getHeight();

      String render = "Canvas " + canvasLeftMost + " " + canvasTopMost + " " + canvasWidth + " " +
          canvasHeight + "\n";
      return render + renderModel;

    } catch (IllegalStateException e) {
      return "Model has no shape to display.";
    }

  }

  @Override
  public void render() throws IOException {
    try {
      this.out.append(this.toString());
    } catch (IOException e) {
      System.err.println("Couldn't append to the stream.");
    }
  }

}
