import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorApp;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.TextualView;
import java.io.IOException;
import org.junit.Test;

/**
 * Class to test textual view.
 */
public class TextualViewTest {

  @Test
  public void testToString() {
    IAnimatorApp model = AnimatorModel.getBuilder().setBounds(0, 0, 500, 500).
        declareShape("R1", "rectangle").declareShape("C2", "circle").
        addMotion("R1", 0, 0, 0, 10, 10, 0, 0, 0, 10, 50,
            100, 10, 10, 0, 0, 0).addMotion("C2", 6, 50, 50,
        30, 30, 0, 0, 255, 10, 50, 50, 50, 50, 0, 0,
        255).build();

    AnimatorView view = new TextualView(model, 2);

    assertEquals("Canvas 0 0 500 500\n"
        + "Shape R1 rectangle\n"
        + "motion R1 0.00 0 0 10 10 0 0 0     5.00 50 100 10 10 0 0 0\n"
        + "\n"
        + "Shape C2 circle\n"
        + "motion C2 3.00 50 50 30 30 0 0 255     5.00 50 50 50 50 0 0 255\n"
        + "\n", view.toString());
  }

  @Test
  public void testToStringButModelHasNoShape() {
    IAnimatorApp noShapeModel = new AnimatorModel();

    AnimatorView view = new TextualView(noShapeModel, 2);

    assertEquals("Model has no shape to display.", view.toString());
  }

  @Test
  public void testToStringButModelHasNoMotion() {
    IAnimatorApp noMotionModel = new AnimatorModel();
    noMotionModel.addShape("R1", "rectangle");

    AnimatorView view = new TextualView(noMotionModel, 2);

    assertEquals("Canvas 0 0 0 0\n"
        + "Shape R1 rectangle has no motion to display.\n", view.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testToStringButModelHasInvalidCanvas() {
    //Canvas has a negative width -100
    IAnimatorApp model = AnimatorModel.getBuilder().setBounds(0, 0, -100, 100).
        declareShape("R1", "rectangle").declareShape("C2", "circle").
        addMotion("R1", 0, 0, 0, 10, 10, 0, 0, 0, 10, 50,
            100, 10, 10, 0, 0, 0).addMotion("C2", 6, 50, 50,
        30, 30, 0, 0, 255, 10, 50, 50, 50, 50, 0, 0,
        255).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidViewWithNullModel() {
    AnimatorView view = new TextualView(null, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidViewWithNegativeTickPerSec() {
    IAnimatorApp model = new AnimatorModel();
    AnimatorView view = new TextualView(model, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidViewWithNullAppendable() {
    IAnimatorApp model = new AnimatorModel();
    AnimatorView view = new TextualView(model, 10, null);
  }

  @Test
  public void testRender() {
    IAnimatorApp model = AnimatorModel.getBuilder().setBounds(0, 0, 500, 500).
        declareShape("R1", "rectangle").declareShape("C2", "circle").
        addMotion("R1", 0, 0, 0, 10, 10, 0, 0, 0, 10, 50,
            100, 10, 10, 0, 0, 0).addMotion("C2", 6, 50, 50,
        30, 30, 0, 0, 255, 10, 50, 50, 50, 50, 0, 0,
        255).build();

    Appendable stringBuilder = new StringBuilder();
    AnimatorView view = new TextualView(model, 2, stringBuilder);
    try {
      view.render();
    } catch (IOException ignored) {

    }

    assertEquals("Canvas 0 0 500 500\n"
        + "Shape R1 rectangle\n"
        + "motion R1 0.00 0 0 10 10 0 0 0     5.00 50 100 10 10 0 0 0\n"
        + "\n"
        + "Shape C2 circle\n"
        + "motion C2 3.00 50 50 30 30 0 0 255     5.00 50 50 50 50 0 0 255\n"
        + "\n", stringBuilder.toString());
  }

}