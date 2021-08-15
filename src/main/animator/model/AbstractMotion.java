package cs3500.animator.model;

import java.awt.Color;
import java.util.Objects;

/**
 * The abstract class represent a motion of shape.
 */
public class AbstractMotion implements IMotion {

  private final int start; //tick start
  private final int end; //tick end
  private final int xStart;
  private final int yStart;
  private final int wStart;
  private final int hStart;
  private final Color currentColor;
  private final int xEnd;
  private final int yEnd;
  private final int wEnd;
  private final int hEnd;
  private final Color newColor;

  /**
   * The constructor of motion.
   */
  public AbstractMotion(int start, int end, int xStart, int yStart, int wStart, int hStart,
      Color currentColor, int xEnd, int yEnd, int wEnd,
      int hEnd, Color newColor) throws IllegalArgumentException {
    if (start < 0 || end < 0 || start > end) {
      throw new IllegalArgumentException("Invalid tick inputs.");
    }
    if (currentColor == null || newColor == null) {
      throw new IllegalArgumentException("Color can't be null.");
    }
    /*if (xStart < 0 || xEnd < 0 || yStart < 0 || yEnd < 0 || wStart < 0 || wEnd < 0 || hStart < 0
        || hEnd < 0) {
      throw new IllegalArgumentException("Number inputs can't be negative");
    }*/
    this.start = start;
    this.end = end;
    this.xStart = xStart;
    this.yStart = yStart;
    this.wStart = wStart;
    this.hStart = hStart;
    this.currentColor = currentColor;
    this.xEnd = xEnd;
    this.yEnd = yEnd;
    this.wEnd = wEnd;
    this.hEnd = hEnd;
    this.newColor = newColor;

  }

  @Override
  public int getStartTick() {
    int startTick = this.start;
    return startTick;
  }

  @Override
  public int getEndTick() {
    int endTick = this.end;
    return endTick;
  }

  @Override
  public int getXStart() {
    int x = this.xStart;
    return x;
  }

  @Override
  public int getYStart() {
    int y = this.yStart;
    return y;
  }

  @Override
  public int getWStart() {
    int w = this.wStart;
    return w;
  }

  @Override
  public int getHStart() {
    int h = this.hStart;
    return h;
  }

  @Override
  public Color getCurrentColor() {
    Color color = this.currentColor;
    return color;
  }

  @Override
  public int getXEnd() {
    int x = this.xEnd;
    return x;
  }

  @Override
  public int getYEnd() {
    int y = this.yEnd;
    return y;
  }

  @Override
  public int getWEnd() {
    int w = this.wEnd;
    return w;
  }

  @Override
  public int getHEnd() {
    int h = this.hEnd;
    return h;
  }

  @Override
  public Color getNewColor() {
    Color color = this.newColor;
    return color;
  }

  @Override
  public String toStringHandleSecTick(double tickStart, double tickEnd) {
    return String.format("%.2f", tickStart) + " " +
        this.xStart + " " +
        this.yStart + " " +
        this.wStart + " " +
        this.hStart + " " +
        this.currentColor.getRed() + " " +
        this.currentColor.getGreen() + " " +
        this.currentColor.getBlue() + "     " +
        String.format("%.2f", tickEnd) + " " +
        this.xEnd + " " +
        this.yEnd + " " +
        this.wEnd + " " +
        this.hEnd + " " +
        this.newColor.getRed() + " " +
        this.newColor.getGreen() + " " +
        this.newColor.getBlue() + "\n";
  }

  @Override
  public String toStringHandleSVG(double tickStart, double duration, String attributeX,
      String attributeY, String attributeW, String attributeH) {
    String render = "";

    if (this.xStart != this.xEnd) {
      render += "    <animate attributeType='xml' begin='" + String.format("%.2f", tickStart)
          + "s' dur='" + String.format("%.2f", duration) + "s' attributeName='" + attributeX
          + "' from='" + this.xStart + "' to='" + this.xEnd + "' fill='freeze' />\n";
    }
    if (this.yStart != this.yEnd) {
      render += "    <animate attributeType='xml' begin='" + String.format("%.2f", tickStart)
          + "s' dur='" + String.format("%.2f", duration) + "s' attributeName='" + attributeY
          + "' from='" + this.yStart + "' to='" + this.yEnd + "' fill='freeze' />\n";
    }
    if (this.wStart != this.wEnd) {
      render += "    <animate attributeType='xml' begin='" + String.format("%.2f", tickStart)
          + "s' dur='" + String.format("%.2f", duration) + "s' attributeName='" + attributeW
          + "' from='" + this.wStart + "' to='" + this.wEnd + "' fill='freeze' />\n";
    }
    if (this.hStart != this.hEnd) {
      render +=
          "    <animate attributeType='xml' begin='" + String.format("%.2f", tickStart) + "s' dur='"
              + String.format("%.2f", duration) + "s' attributeName='" + attributeH
              + "' from='" + this.hStart + "' to='" + this.hEnd + "' fill='freeze' />\n";
    }
    if (!this.currentColor.equals(this.newColor)) {
      int r1 = this.currentColor.getRed();
      int g1 = this.currentColor.getGreen();
      int b1 = this.currentColor.getBlue();
      int r2 = this.newColor.getRed();
      int g2 = this.newColor.getGreen();
      int b2 = this.newColor.getBlue();

      render +=
          "    <animate attributeType='xml' begin='" + String.format("%.2f", tickStart) + "s' dur='"
              + String.format("%.2f", duration) + "s' attributeName='fill' from='rgb("
              + r1 + "," + g1 + "," + b1 + ")' to='" + "rgb(" + r2 + "," + g2 + "," + b2 + ")"
              + "' fill='freeze' />\n";
    }
    return render;
  }

  @Override
  public IMotion fillGapMotion(int newStart, int newEnd) {
    IMotion newMotion = new AbstractMotion(newStart, newEnd, this.xStart, this.yStart, this.wStart,
        this.hStart, this.currentColor, this.xEnd, this.yEnd, this.wEnd, this.hEnd, this.newColor);
    return newMotion;
  }

  @Override
  public IReadOnlyShape apply(int tick, ShapeType type) {
    return null;
  }

  @Override
  public IReadOnlyShape applyDiscreteTime(int tick, ShapeType type) {
    AbstractShape shape = new AbstractShape(0, 0, 0, 0, Color.WHITE);
    if (type.equals(ShapeType.OVAL)) {
      shape = new Circle(0, 0, 0, 0, Color.WHITE);
    }
    if (type.equals(ShapeType.RECTANGLE)) {
      shape = new Rectangle(0, 0, 0, 0, Color.WHITE);
    }

    if (tick == this.getStartTick()) {
      return shape.makeShape(this.getXStart(), this.getYStart(), this.getWStart(), this.getHStart(),
          this.getCurrentColor());
    } else if (tick == this.getEndTick()) {
      return shape.makeShape(this.getXEnd(), this.getYEnd(), this.getWEnd(), this.getHEnd(),
          this.getNewColor());
    } else if (tick > this.getStartTick() && tick < this.getEndTick()) {
      return shape.makeShape(this.getXStart(), this.getYStart(), this.getWStart(), this.getHStart(),
          this.getCurrentColor());
    } else {
      return null;
    }

  }

  protected int tween(int tick, int startParam, int endParam) {
    return (startParam * (this.end - tick) / (this.end - this.start)) +
        (endParam * (tick - this.start) / (this.end - this.start));
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof IMotion)) {
      return false;
    }

    return ((AbstractMotion) that).start == this.start &&
        ((AbstractMotion) that).end == this.end &&
        ((AbstractMotion) that).xStart == this.xStart &&
        ((AbstractMotion) that).xEnd == this.xEnd &&
        ((AbstractMotion) that).yStart == this.yStart &&
        ((AbstractMotion) that).yEnd == this.yEnd &&
        ((AbstractMotion) that).wStart == this.wStart &&
        ((AbstractMotion) that).wEnd == this.wEnd &&
        ((AbstractMotion) that).hStart == this.hStart &&
        ((AbstractMotion) that).hEnd == this.hEnd &&
        ((AbstractMotion) that).currentColor == this.currentColor &&
        ((AbstractMotion) that).newColor == this.newColor;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return this.start + " " +
        this.xStart + " " +
        this.yStart + " " +
        this.wStart + " " +
        this.hStart + " " +
        this.currentColor.getRed() + " " +
        this.currentColor.getGreen() + " " +
        this.currentColor.getBlue() + "     " +
        this.end + " " +
        this.xEnd + " " +
        this.yEnd + " " +
        this.wEnd + " " +
        this.hEnd + " " +
        this.newColor.getRed() + " " +
        this.newColor.getGreen() + " " +
        this.newColor.getBlue() + "\n";
  }

}
