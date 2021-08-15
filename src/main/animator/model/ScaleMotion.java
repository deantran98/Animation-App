package cs3500.animator.model;

import java.awt.Color;

/**
 * The class represent a motion of shape can be changed width and height only.
 */
public class ScaleMotion extends AbstractMotion {

  /**
   * The constructor of scale motion.
   */
  public ScaleMotion(int start, int end, int xStart, int yStart, int wStart, int hStart,
      Color currentColor, int wEnd, int hEnd)
      throws IllegalArgumentException {
    super(start, end, xStart, yStart, wStart, hStart, currentColor, xStart, yStart, wEnd, hEnd,
        currentColor);
  }

  @Override
  public IReadOnlyShape apply(int tick, ShapeType type) {

    AbstractShape shape = new AbstractShape(0, 0, 0, 0, Color.WHITE);
    if (type.equals(ShapeType.OVAL)) {
      shape = new Circle(0, 0, 0, 0, Color.WHITE);
    }
    if (type.equals(ShapeType.RECTANGLE)) {
      shape = new Rectangle(0, 0, 0, 0, Color.WHITE);
    }

    if (tick > this.getStartTick() && tick < this.getEndTick() && this.getStartTick() !=
        this.getEndTick()) {

      int w = tween(tick, this.getWStart(), this.getWEnd());
      int h = tween(tick, this.getHStart(), this.getHEnd());

      return shape.makeShape(this.getXStart(), this.getYStart(), w, h, this.getCurrentColor());

    } else if (tick == this.getStartTick()) {
      return shape.makeShape(this.getXStart(), this.getYStart(), this.getWStart(), this.getHStart(),
          this.getCurrentColor());
    } else if (tick == this.getEndTick()) {
      return shape.makeShape(this.getXEnd(), this.getYEnd(), this.getWEnd(), this.getHEnd(),
          this.getNewColor());
    } else {
      return null;
    }

  }

}
