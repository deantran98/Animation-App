package cs3500.animator.model;

import java.awt.Color;

/**
 * The class represent a motion of shape can be changed color only.
 */
public class ChangeColorMotion extends AbstractMotion {

  /**
   * The constructor of change color motion.
   */
  public ChangeColorMotion(int start, int end, int xStart, int yStart, int wStart, int hStart,
      Color currentColor, Color newColor)
      throws IllegalArgumentException {
    super(start, end, xStart, yStart, wStart, hStart, currentColor, xStart, yStart, wStart, hStart,
        newColor);
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

    if (tick > this.getStartTick() && tick < this.getEndTick() && this.getStartTick() != this
        .getEndTick()) {

      int r = tween(tick, this.getCurrentColor().getRed(), this.getNewColor().getRed());
      int g = tween(tick, this.getCurrentColor().getGreen(), this.getNewColor().getGreen());
      int b = tween(tick, this.getCurrentColor().getBlue(), this.getNewColor().getBlue());
      Color color = new Color(r, g, b);

      return shape.makeShape(this.getXStart(), this.getYStart(), this.getWStart(), this.getHStart(),
          color);

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
