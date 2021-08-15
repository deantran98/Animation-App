package cs3500.animator.view;

import cs3500.animator.model.IAnimatorApp;
import cs3500.animator.model.IShape;
import java.io.IOException;
import java.util.Map;

/**
 * Class represent a SVG view.
 */
public class SvgView implements AnimatorView {

  private final IAnimatorApp model;
  private final double tickPerSec;
  private Appendable out;

  /**
   * Constructor of the SVG view.
   */
  public SvgView(IAnimatorApp model, int tickPerSec) {
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
   * Constructor of the SVG view that has both model and appendable.
   */
  public SvgView(IAnimatorApp model, int tickPerSec, Appendable out) {
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
        renderModel += shapesInModel.get(str).renderMotionsInfoSVG(tickPerSec) + "\n";
      }

      int canvasWidth = model.getCanvas().getWidth();
      int canvasHeight = model.getCanvas().getHeight();

      String headerRender = "<svg width='" + canvasWidth + "' " + "height='" + canvasHeight + "' " +
          "version='1.1'\n";
      headerRender += "     xmlns='http://www.w3.org/2000/svg'>\n\n";
      return headerRender + renderModel + "</svg>";

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
