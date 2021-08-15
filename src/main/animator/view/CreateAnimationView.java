package cs3500.animator.view;

import cs3500.animator.model.IAnimatorApp;
import cs3500.animator.model.IShape;
import java.io.IOException;
import java.util.Map;

/**
 * Class helps to render information to the out file (create an animation as a txt file).
 */
public class CreateAnimationView implements AnimatorView {
  private final IAnimatorApp model;
  private Appendable out;

  /**
   * Constructor of the create animation view that has both model and appendable.
   */
  public CreateAnimationView(IAnimatorApp model, Appendable out) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null.");
    }

    if (out == null) {
      throw new IllegalArgumentException("Appendable can't be null.");
    }

    this.model = model;
    this.out = out;
  }

  @Override
  public String toString() {

    try {

      Map<String, IShape> shapesInModel = model.getShapes();

      String renderModel = "";

      for (String str : shapesInModel.keySet()) {
        renderModel += shapesInModel.get(str).renderMotionsInfo() + "\n";
      }

      int canvasLeftMost = model.getCanvas().getLeftMost();
      int canvasTopMost = model.getCanvas().getTopMost();
      int canvasWidth = model.getCanvas().getWidth();
      int canvasHeight = model.getCanvas().getHeight();

      String render = "canvas " + canvasLeftMost + " " + canvasTopMost + " " + canvasWidth + " " +
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
