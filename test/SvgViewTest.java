import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorApp;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.SvgView;
import cs3500.animator.view.TextualView;
import java.io.IOException;
import org.junit.Test;

/**
 * Class to test SVG view.
 */
public class SvgViewTest {

  @Test
  public void testToString() {
    IAnimatorApp app = AnimatorModel.getBuilder().setBounds(0, 0, 500, 500).
        declareShape("R1", "rectangle").declareShape("C2", "circle").
        addMotion("R1", 0, 0, 0, 10, 10, 0, 0, 0, 10, 50,
            100, 10, 10, 0, 0, 0).addMotion("C2", 6, 50, 50,
        30, 30, 0, 0, 255, 10, 50, 50, 50, 50, 0, 0,
        255).build();

    AnimatorView view = new SvgView(app, 2);

    assertEquals("<svg width='500' height='500' version='1.1'\n"
        + "     xmlns='http://www.w3.org/2000/svg'>\n"
        + "\n"
        + "<rect id='R1' x='0' y='0' width='10' height='10' fill='rgb(0,0,0)' "
        + "visibility='visible' >\n"
        + "    <animate attributeType='xml' begin='0.00s' dur='5.00s' attributeName='x' from='0' "
        + "to='50' fill='freeze' />\n"
        + "    <animate attributeType='xml' begin='0.00s' dur='5.00s' attributeName='y' from='0' "
        + "to='100' fill='freeze' />\n"
        + "</rect>\n"
        + "\n"
        + "<ellipse id='C2' cx='50' cy='50' rx='30' ry='30' fill='rgb(0,0,255)' "
        + "visibility='visible' >\n"
        + "    <animate attributeType='xml' begin='3.00s' dur='2.00s' attributeName='rx' from='30' "
        + "to='50' fill='freeze' />\n"
        + "    <animate attributeType='xml' begin='3.00s' dur='2.00s' attributeName='ry' from='30' "
        + "to='50' fill='freeze' />\n"
        + "</ellipse>\n"
        + "\n"
        + "</svg>", view.toString());

  }

  @Test
  public void testToStringButModelHasNoShape() {
    IAnimatorApp noShapeModel = new AnimatorModel();

    AnimatorView view = new SvgView(noShapeModel, 2);

    assertEquals("Model has no shape to display.", view.toString());
  }

  @Test
  public void testToStringButModelHasNoMotion() {
    IAnimatorApp noMotionModel = new AnimatorModel();
    noMotionModel.addShape("R1", "rectangle");

    AnimatorView view = new SvgView(noMotionModel, 2);

    assertEquals("<svg width='0' height='0' version='1.1'\n"
        + "     xmlns='http://www.w3.org/2000/svg'>\n"
        + "\n"
        + "<rect id='R1' x='0' y='0' width='0' height='0' fill='rgb(255,255,255)' "
        + "visibility='hide' >\n"
        + "\n"
        + "</svg>", view.toString());
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
    IAnimatorApp app = AnimatorModel.getBuilder().setBounds(0, 0, 500, 500).
        declareShape("R1", "rectangle").declareShape("C2", "circle").
        addMotion("R1", 0, 0, 0, 10, 10, 0, 0, 0, 10, 50,
            100, 10, 10, 0, 0, 0).addMotion("C2", 6, 50, 50,
        30, 30, 0, 0, 255, 10, 50, 50, 50, 50, 0, 0,
        255).addMotion("C2", 10, 50, 50, 50, 50, 0, 0, 255,
        50, 50, 50, 50, 50, 150, 120, 200).build();

    Appendable stringBuilder = new StringBuilder();
    AnimatorView view = new SvgView(app, 2, stringBuilder);

    try {
      view.render();
    } catch (IOException ignored) {

    }

    assertEquals("<svg width='500' height='500' version='1.1'\n"
        + "     xmlns='http://www.w3.org/2000/svg'>\n"
        + "\n"
        + "<rect id='R1' x='0' y='0' width='10' height='10' fill='rgb(0,0,0)' "
        + "visibility='visible' >\n"
        + "    <animate attributeType='xml' begin='0.00s' dur='5.00s' attributeName='x' from='0' "
        + "to='50' fill='freeze' />\n"
        + "    <animate attributeType='xml' begin='0.00s' dur='5.00s' attributeName='y' from='0' "
        + "to='100' fill='freeze' />\n"
        + "</rect>\n"
        + "\n"
        + "<ellipse id='C2' cx='50' cy='50' rx='30' ry='30' fill='rgb(0,0,255)' "
        + "visibility='visible' >\n"
        + "    <animate attributeType='xml' begin='3.00s' dur='2.00s' attributeName='rx' from='30' "
        + "to='50' fill='freeze' />\n"
        + "    <animate attributeType='xml' begin='3.00s' dur='2.00s' attributeName='ry' from='30' "
        + "to='50' fill='freeze' />\n"
        + "    <animate attributeType='xml' begin='5.00s' dur='20.00s' attributeName='fill' "
        + "from='rgb(0,0,255)' to='rgb(150,120,200)' fill='freeze' />\n"
        + "</ellipse>\n"
        + "\n"
        + "</svg>", stringBuilder.toString());
  }

}