package cs3500.animator.model;

import java.awt.Color;

/**
 * The class represent a motion of shape can be changed on different variables.
 */
public class Motion extends AbstractMotion {

  /**
   * The constructor of motion.
   */
  public Motion(int start, int end, int xStart, int yStart, int wStart, int hStart,
      Color currentColor, int xEnd, int yEnd, int wEnd, int hEnd, Color newColor)
      throws IllegalArgumentException {
    super(start, end, xStart, yStart, wStart, hStart, currentColor, xEnd, yEnd, wEnd, hEnd,
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

    if (tick > this.getStartTick() && tick < this.getEndTick() && this.getStartTick() !=
        this.getEndTick()) {

      int x = this.getXStart();
      int y = this.getYStart();
      int w = this.getWStart();
      int h = this.getHStart();
      Color color = this.getCurrentColor();

      if (this.getXStart() != this.getXEnd()) {
        x = tween(tick, this.getXStart(), this.getXEnd());
      }

      if (this.getYStart() != this.getYEnd()) {
        y = tween(tick, this.getYStart(), this.getYEnd());
      }

      if (this.getWStart() != this.getWEnd()) {
        w = tween(tick, this.getWStart(), this.getWEnd());
      }

      if (this.getHStart() != this.getHEnd()) {
        h = tween(tick, this.getHStart(), this.getHEnd());
      }

      if (!this.getCurrentColor().equals(this.getNewColor())) {
        int r = tween(tick, this.getCurrentColor().getRed(), this.getNewColor().getRed());
        int g = tween(tick, this.getCurrentColor().getGreen(), this.getNewColor().getGreen());
        int b = tween(tick, this.getCurrentColor().getBlue(), this.getNewColor().getBlue());
        color = new Color(r, g, b);
      }

      return shape.makeShape(x, y, w, h, color);

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
