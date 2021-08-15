package cs3500.animator.model;

import java.awt.Color;

/**
 * The class represent a motion of shape can be changed x and y position only.
 */
public class MoveMotion extends AbstractMotion {

  /**
   * The constructor of move motion.
   */
  public MoveMotion(int start, int end, int xStart, int yStart, int wStart, int hStart,
      Color currentColor, int xEnd, int yEnd)
      throws IllegalArgumentException {
    super(start, end, xStart, yStart, wStart, hStart, currentColor, xEnd, yEnd, wStart, hStart,
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

      int x = tween(tick, this.getXStart(), this.getXEnd());
      int y = tween(tick, this.getYStart(), this.getYEnd());

      return shape.makeShape(x, y, this.getWStart(), this.getHStart(), this.getCurrentColor());

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
