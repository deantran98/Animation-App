import static org.junit.Assert.assertEquals;

import cs3500.animator.model.IReadOnlyShape;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.ChangeColorMotion;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.MoveMotion;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ScaleMotion;
import org.junit.Test;

/**
 * Class to test constructor and all methods for model.
 */
public class AnimatorModelTest {

  AnimatorModel model;

  private void initialize() {
    model = new AnimatorModel();
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullInputAddShape() {
    this.initialize();
    model.addShape(null, "rectangle");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidInputsAddShape() {
    this.initialize();
    model.addShape("rectangle", "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTypeAddShape() {
    this.initialize();
    model.addShape("rectangle", "hello");
  }

  @Test(expected = IllegalArgumentException.class)
  public void sameIdAddShape() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addShape("R1", "rectangle");
  }

  @Test
  public void validAddShape() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addShape("R2", "rectangle");
    assertEquals(2, model.getShapes().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidRemoveShape() {
    this.initialize();
    model.removeShape("");
    model.removeShape(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void cantFindShapeToRemoveShape() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.removeShape("R2");
  }

  @Test(expected = IllegalStateException.class)
  public void noShapeToRemove() {
    this.initialize();
    model.removeShape("R1");
  }

  @Test
  public void validRemoveShape() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addShape("R2", "rectangle");
    assertEquals(2, model.getShapes().size());

    model.removeShape("R1");
    assertEquals(1, model.getShapes().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullInputShapeShowOnTop() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addShape("R2", "rectangle");
    model.shapeShowOnTop(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void noShapeFoundShapeShowOnTop() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addShape("R2", "rectangle");
    IShape shape1 = model.getShapes().get(0);

    model.shapeShowOnTop(shape1, new Rectangle("R3"));
  }

  @Test(expected = IllegalStateException.class)
  public void noShapeInModelShapeShowOnTop() {
    this.initialize();
    model.shapeShowOnTop(new Rectangle("R1"), new Rectangle("R3"));
  }

  @Test
  public void validShapeShowOnTop() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addShape("R2", "rectangle");
    IShape shape1 = model.getShapes().get("R1");
    IShape shape2 = model.getShapes().get("R2");

    assertEquals(shape2.getName(), model.shapeShowOnTop(shape1, shape2).getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullInputAddMotion() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addShape("R2", "rectangle");

    model.addMotion(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptyStringAddMotion() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addShape("R2", "rectangle");

    model.addMotion("", new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
  }

  @Test(expected = IllegalArgumentException.class)
  public void cantFindShapeToAddMotion() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addShape("R2", "rectangle");

    model.addMotion("R3", new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
  }

  @Test(expected = IllegalStateException.class)
  public void addExistedMotion() {
    this.initialize();
    model.addShape("R1", "rectangle");

    model.addMotion("R1", new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    model.addMotion("R1", new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
  }

  @Test(expected = IllegalStateException.class)
  public void addGapMotion() {
    this.initialize();
    model.addShape("R1", "rectangle");

    model.addMotion("R1", new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    model.addMotion("R1", new Motion(10, 50, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
  }

  @Test
  public void validAddMotion() {
    this.initialize();
    model.addShape("R1", "rectangle");
    /*model.addMotion("R1", new Motion(0, 10, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    assertEquals(1, model.getShapes().get("R1").getMotions().size());

    model.addMotion("R1", new Motion(10, 50, 50, 50, 10, 10,
        Color.BLACK, 100, 100, 20, 20, Color.BLACK));
    assertEquals(2, model.getShapes().get("R1").getMotions().size());*/
    model.addMotion("R1", new MoveMotion(1, 100, 0, 0, 10, 10,
        Color.BLACK, 400, 400));
    assertEquals(1, model.getShapes().get("R1").getMotions().size());

    model.addMotion("R1", new ChangeColorMotion(100, 200, 400, 400, 10, 10,
        Color.BLACK, Color.BLUE));

    model.addMotion("R1", new ScaleMotion(200, 300, 400, 400, 10, 10,
        Color.BLUE, 100, 100));

    assertEquals(3, model.getShapes().get("R1").getMotions().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullInputRemoveMotion() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.removeMotion(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptyStringRemoveMotion() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.removeMotion("", new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
  }

  @Test(expected = IllegalArgumentException.class)
  public void cantFindMotionToRemove() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addMotion("R1", new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    model.removeMotion("R1", new Motion(10, 50, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
  }

  @Test(expected = IllegalStateException.class)
  public void modelHasNoShapeToRemoveMotion() {
    this.initialize();
    model.removeMotion("R1", new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
  }

  @Test
  public void validRemoveMotion() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addMotion("R1", new Motion(0, 10, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    model.addMotion("R1", new Motion(10, 50, 50, 50, 10, 10,
        Color.BLACK, 70, 70, 15, 15, Color.BLACK));
    model.addMotion("R1", new Motion(50, 100, 70, 70, 15, 15,
        Color.BLACK, 100, 100, 20, 20, Color.BLACK));

    //remove the middle motion, it then gets replaced by a new motion to fill the gap from 10-50
    model.removeMotion("R1", new Motion(10, 50, 50, 50, 10, 10,
        Color.BLACK, 70, 70, 15, 15, Color.BLACK));
    assertEquals(new Motion(10, 50, 0, 0, 10, 10,
            Color.BLACK, 50, 50, 10, 10, Color.BLACK),
        model.getShapes().get("R1").getMotions().get(1));
  }

  @Test
  public void validRemoveHeadOrTailMotion() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addMotion("R1", new Motion(0, 10, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    model.addMotion("R1", new Motion(10, 50, 50, 50, 10, 10,
        Color.BLACK, 70, 70, 15, 15, Color.BLACK));
    model.addMotion("R1", new Motion(50, 100, 70, 70, 15, 15,
        Color.BLACK, 100, 100, 20, 20, Color.BLACK));

    model.removeMotion("R1", new Motion(0, 10, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    assertEquals(2, model.getShapes().get("R1").getMotions().size());

    model.removeMotion("R1", new Motion(50, 100, 70, 70, 15, 15,
        Color.BLACK, 100, 100, 20, 20, Color.BLACK));
    assertEquals(1, model.getShapes().get("R1").getMotions().size());
  }

  @Test(expected = IllegalStateException.class)
  public void modelHasNoShapeToGet() {
    this.initialize();
    model.getShapes();
  }

  @Test
  public void getShapesInModel() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addShape("C2", "circle");

    Map<String, IShape> shapesInModel = model.getShapes();
    assertEquals(2, shapesInModel.size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullShapeIdToGetMotion() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addMotion("R1", new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    model.getMotions(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptyStringShapeIdToGetMotion() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addMotion("R1", new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    model.getMotions("");
  }

  @Test(expected = IllegalStateException.class)
  public void modelHasNoShapeToGetMotion() {
    this.initialize();
    model.getMotions("C2");
  }

  @Test
  public void getMotions() {
    this.initialize();
    model.addShape("C1", "circle");
    model.addMotion("C1", new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));

    assertEquals(new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK), model.getMotions("C1").get(0));
  }

  @Test(expected = IllegalStateException.class)
  public void modelHasNoShapeToAllGetMotion() {
    this.initialize();
    model.getAllMotions();
  }

  @Test
  public void getAllMotions() {
    this.initialize();
    model.addShape("C1", "circle");
    model.addShape("R1", "rectangle");

    model.addMotion("C1", new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    model.addMotion("R1", new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    model.addMotion("R1", new Motion(1, 10, 50, 50, 10, 10,
        Color.BLACK, 100, 100, 10, 10, Color.BLACK));

    List<IMotion> c1Motions = new ArrayList<>();
    c1Motions.add(new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    List<IMotion> r1Motions = new ArrayList<>();
    r1Motions.add(new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    r1Motions.add(new Motion(1, 10, 50, 50, 10, 10,
        Color.BLACK, 100, 100, 10, 10, Color.BLACK));

    assertEquals(c1Motions, model.getAllMotions().get(0));
    assertEquals(r1Motions, model.getAllMotions().get(1));
  }

  @Test
  public void noShapeToRenderInformation() {
    this.initialize();

    assertEquals("There is no shape in the model.",
        model.renderInformation());
  }

  @Test
  public void renderInformation() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addShape("C2", "circle");

    model.addMotion("R1", new Motion(0, 10, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    model.addMotion("R1", new Motion(10, 50, 50, 50, 10, 10,
        Color.BLACK, 70, 70, 15, 15, Color.BLUE));
    model.addMotion("C2", new Motion(6, 10, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    model.addMotion("C2", new Motion(10, 50, 50, 50, 10, 10,
        Color.BLACK, 70, 70, 15, 15, Color.BLACK));

    assertEquals("shape R1 rectangle\n"
            + "motion R1 0 0 0 10 10 0 0 0     10 50 50 10 10 0 0 0\n"
            + "motion R1 10 50 50 10 10 0 0 0     50 70 70 15 15 0 0 255\n"
            + "\n"
            + "shape C2 circle\n"
            + "motion C2 6 0 0 10 10 0 0 0     10 50 50 10 10 0 0 0\n"
            + "motion C2 10 50 50 10 10 0 0 0     50 70 70 15 15 0 0 0\n"
            + "\n",
        model.renderInformation());
  }

  @Test
  public void moreRenderInformationWithDifferentMotions() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addShape("C2", "circle");

    model.addMotion("R1", new MoveMotion(0, 50, 0, 0, 10, 10,
        Color.BLACK, 100, 100));
    model.addMotion("R1", new ScaleMotion(50, 60, 100, 100, 10, 10,
        Color.BLACK, 50, 50));

    model.addMotion("C2", new MoveMotion(0, 10, 0, 0, 10, 10,
        Color.BLACK, 70, 70));
    model.addMotion("C2", new ChangeColorMotion(10, 50, 70, 70, 10, 10,
        Color.BLACK, Color.RED));

    assertEquals("shape R1 rectangle\n"
            + "motion R1 0 0 0 10 10 0 0 0     50 100 100 10 10 0 0 0\n"
            + "motion R1 50 100 100 10 10 0 0 0     60 100 100 50 50 0 0 0\n"
            + "\n"
            + "shape C2 circle\n"
            + "motion C2 0 0 0 10 10 0 0 0     10 70 70 10 10 0 0 0\n"
            + "motion C2 10 70 70 10 10 0 0 0     50 70 70 10 10 255 0 0\n"
            + "\n",
        model.renderInformation());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidGetShapesAtNegativeTick() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addMotion("R1", new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    model.getShapesAtGivenTick(-1);
  }

  @Test(expected = IllegalStateException.class)
  public void invalidGetShapesAtGivenTickEmptyModel() {
    this.initialize();
    model.getShapesAtGivenTick(1);
  }

  @Test
  public void validGetShapesAtGivenTick() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addShape("C2", "circle");
    model.addMotion("R1", new Motion(1, 5, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    model.addMotion("C2", new Motion(1, 5, 10, 10, 20, 20,
        Color.BLACK, 50, 50, 20, 20, Color.BLACK));
    List<IReadOnlyShape> shapes = model.getShapesAtGivenTick(1);

    assertEquals(2, shapes.size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidGetShapesAtNegativeDiscreteTime() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addMotion("R1", new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    model.getShapesAtDiscreteTime(-14);
  }

  @Test(expected = IllegalStateException.class)
  public void invalidGetShapesAtDiscreteTimeEmptyModel() {
    this.initialize();
    model.getShapesAtDiscreteTime(4);
  }

  @Test
  public void validGetShapesAtDiscreteTime() {
    this.initialize();
    model.addShape("R1", "rectangle");
    model.addShape("C2", "circle");
    model.addMotion("R1", new Motion(1, 5, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    model.addMotion("C2", new Motion(1, 5, 10, 10, 20, 20,
        Color.BLACK, 50, 50, 20, 20, Color.BLACK));
    List<IReadOnlyShape> shapes = model.getShapesAtDiscreteTime(1);

    assertEquals(2, shapes.size());
  }

}