import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import cs3500.animator.model.MoveMotion;
import java.awt.Color;
import java.util.ArrayList;
import cs3500.animator.model.Circle;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Rectangle;
import org.junit.Test;

/**
 * Class to test constructor and all methods for shape.
 */
public class AbstractShapeTest {

  @Test(expected = IllegalArgumentException.class)
  public void createShapeWithNullName() {
    Circle circle = new Circle(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createShapeWithEmptyName() {
    Rectangle rectangle = new Rectangle("");
  }

  @Test
  public void testGetName() {
    Rectangle rectangle = new Rectangle("R1");
    Circle circle = new Circle("C1");
    assertEquals("R1", rectangle.getName());
    assertEquals("C1", circle.getName());
  }

  @Test
  public void newEquals() {
    Rectangle rectangle = new Rectangle("R1");
    Circle circle = new Circle("C1");
    Circle circle1 = new Circle("C2");
    assertEquals(rectangle, rectangle);
    assertFalse(circle.equals(circle1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidAddMotion() {
    Rectangle rectangle = new Rectangle("R1");
    rectangle.addMotion(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addExistedMotion() {
    Rectangle rectangle = new Rectangle("R1");
    rectangle.addMotion(new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    rectangle.addMotion(new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNotConnectedTickMotion() {
    Rectangle rectangle = new Rectangle("R1");
    rectangle.addMotion(new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    rectangle.addMotion(new Motion(3, 7, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNotConnectedMotion() {
    Rectangle rectangle = new Rectangle("R1");
    rectangle.addMotion(new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    rectangle.addMotion(new Motion(1, 7, 10, 10, 2, 2,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
  }

  @Test
  public void addValidMotion() {
    Rectangle rectangle = new Rectangle("R1");
    rectangle.addMotion(new MoveMotion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50));
    rectangle.addMotion(new Motion(1, 7, 50, 50, 10, 10,
        Color.BLACK, 100, 100, 10, 10, Color.BLACK));
    assertEquals(new Motion(1, 7, 50, 50, 10, 10, Color.BLACK,
        100, 100, 10, 10, Color.BLACK), rectangle.getMotions().get(1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeNullMotion() {
    Rectangle rectangle = new Rectangle("R1");
    rectangle.removeMotion(null);
  }

  @Test(expected = IllegalStateException.class)
  public void removeButHaveNoMotion() {
    Rectangle rectangle = new Rectangle("R1");
    rectangle.removeMotion(new Motion(0, 1, 0, 0, 1, 1,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeInvalidMotion() {
    Rectangle rectangle = new Rectangle("R1");
    rectangle.addMotion(new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    rectangle.removeMotion(new Motion(1, 7, 10, 10, 2, 2,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
  }

  @Test
  public void removeValidMotion() {
    Rectangle rectangle = new Rectangle("R1");
    rectangle.addMotion(new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    rectangle.addMotion(new Motion(1, 7, 50, 50, 10, 10,
        Color.BLACK, 100, 100, 10, 10, Color.BLACK));
    rectangle.addMotion(new Motion(7, 10, 100, 100, 10, 10,
        Color.BLACK, 150, 150, 10, 10, Color.BLACK));

    rectangle.removeMotion(new Motion(1, 7, 50, 50, 10, 10,
        Color.BLACK, 100, 100, 10, 10, Color.BLACK));
    assertEquals(new Motion(1, 7, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK), rectangle.getMotions().get(1));
  }

  @Test
  public void removeValidMotionAtHeadOrTail() {
    Rectangle rectangle = new Rectangle("R1");
    rectangle.addMotion(new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    rectangle.addMotion(new Motion(1, 7, 50, 50, 10, 10,
        Color.BLACK, 100, 100, 10, 10, Color.BLACK));
    rectangle.addMotion(new Motion(7, 10, 100, 100, 10, 10,
        Color.BLACK, 150, 150, 10, 10, Color.BLACK));

    rectangle.removeMotion(new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    assertEquals(2, rectangle.getMotions().size());

    rectangle.removeMotion(new Motion(7, 10, 100, 100, 10, 10,
        Color.BLACK, 150, 150, 10, 10, Color.BLACK));
    assertEquals(1, rectangle.getMotions().size());
  }

  @Test
  public void getMotions() {
    Rectangle rectangle = new Rectangle("R1");
    rectangle.addMotion(new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    rectangle.addMotion(new Motion(1, 7, 50, 50, 10, 10,
        Color.BLACK, 100, 100, 10, 10, Color.BLACK));
    ArrayList<IMotion> motions = new ArrayList<>();
    motions.add(new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    motions.add(new Motion(1, 7, 50, 50, 10, 10,
        Color.BLACK, 100, 100, 10, 10, Color.BLACK));
    assertEquals(motions, rectangle.getMotions());
  }

  @Test
  public void renderEmptyMotionsInfo() {
    Rectangle rectangle = new Rectangle("R1");

    assertEquals("Shape R1 rectangle has no motion to display.", rectangle.renderMotionsInfo());
  }

  @Test
  public void renderMotionsInfo() {
    Rectangle rectangle = new Rectangle("R1");
    rectangle.addMotion(new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK));
    rectangle.addMotion(new Motion(1, 7, 50, 50, 10, 10,
        Color.BLACK, 100, 100, 10, 10, Color.BLACK));
    assertEquals("shape R1 rectangle\n"
            + "motion R1 0 0 0 10 10 0 0 0     1 50 50 10 10 0 0 0\n"
            + "motion R1 1 50 50 10 10 0 0 0     7 100 100 10 10 0 0 0\n",
        rectangle.renderMotionsInfo());
  }

}