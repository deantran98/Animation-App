import static org.junit.Assert.assertEquals;

import java.awt.Color;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.Motion;
import org.junit.Test;

/**
 * Class to test constructor and all methods for motion.
 */
public class MotionTest {

  IMotion motion;

  private void initialize() {
    motion = new Motion(0, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidStartTickConstructor() {
    IMotion invalidMotion = new Motion(-1, 1, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidEndTickConstructor() {
    IMotion invalidMotion = new Motion(50, -100, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTicksStartGreaterThanEnd() {
    IMotion invalidMotion = new Motion(10, 0, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullColor() {
    IMotion invalidMotion = new Motion(10, 0, 0, 0, 10, 10,
        null, 50, 50, 10, 10, Color.BLACK);
  }

  @Test
  public void getStartTick() {
    this.initialize();
    assertEquals(0, motion.getStartTick());
  }

  @Test
  public void getEndTick() {
    this.initialize();
    assertEquals(1, motion.getEndTick());
  }

  @Test
  public void getStartX() {
    this.initialize();
    assertEquals(0, motion.getXStart());
  }

  @Test
  public void getStartY() {
    this.initialize();
    assertEquals(0, motion.getYStart());
  }

  @Test
  public void getStartW() {
    this.initialize();
    assertEquals(10, motion.getWStart());
  }

  @Test
  public void getStartH() {
    this.initialize();
    assertEquals(10, motion.getHStart());
  }

  @Test
  public void getCurrentColor() {
    this.initialize();
    assertEquals(Color.BLACK, motion.getCurrentColor());
  }

  @Test
  public void testFillGapMotion() {
    this.initialize();
    assertEquals(new Motion(10, 20, 0, 0, 10, 10,
            Color.BLACK, 50, 50, 10, 10, Color.BLACK),
        motion.fillGapMotion(10, 20));
  }

  @Test
  public void testToString() {
    this.initialize();
    assertEquals(
        "0 0 0 10 10 0 0 0     1 50 50 10 10 0 0 0\n",
        motion.toString());

    IMotion newMotion = new Motion(10, 20, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK);
    assertEquals(
        "10 0 0 10 10 0 0 0     20 50 50 10 10 0 0 0\n",
        newMotion.toString());
  }

  @Test
  public void testToStringSec() {
    this.initialize();
    assertEquals(
        "10.00 0 0 10 10 0 0 0     20.00 50 50 10 10 0 0 0\n",
        motion.toStringHandleSecTick(10,20));

    IMotion newMotion = new Motion(10, 20, 0, 0, 10, 10,
        Color.BLACK, 50, 50, 10, 10, Color.BLACK);
    assertEquals(
        "10.00 0 0 10 10 0 0 0     20.00 50 50 10 10 0 0 0\n",
        newMotion.toStringHandleSecTick(10, 20));
  }

}