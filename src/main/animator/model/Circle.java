package cs3500.animator.model;

import java.awt.Color;
import java.util.List;

/**
 * Class represents a circle.
 */
public class Circle extends AbstractShape {

  /**
   * The constructor of the circle shape.
   */
  public Circle(String name) throws IllegalArgumentException {
    super(name);
  }

  /**
   * The constructor of the rectangle shape to draw.
   */
  public Circle(int x, int y, int width, int height, Color color) {
    super(x, y, width, height, color);
  }

  @Override
  public ShapeType getType() {
    return ShapeType.OVAL;
  }

  @Override
  public String renderMotionsInfo() {
    if (this.getMotions().isEmpty()) {
      return "Shape " + this.getName() + " circle has no motion to display.";
    }

    String render = "";
    List<IMotion> motions = this.getMotions();
    for (IMotion motion : motions) {
      render += "motion " + this.getName() + " " + motion.toString();
    }
    return "shape " + this.getName() + " circle\n" + render;
  }

  @Override
  public String renderMotionsInfoInSecTick(double ticPerSec) {
    if (this.getMotions().isEmpty()) {
      return "Shape " + this.getName() + " circle has no motion to display.";
    }

    String render = "";
    List<IMotion> motions = this.getMotions();
    for (IMotion motion : motions) {
      int start = motion.getStartTick();
      int end = motion.getEndTick();
      render += "motion " + this.getName() + " " + motion
          .toStringHandleSecTick(start / ticPerSec, end / ticPerSec);
    }
    return "Shape " + this.getName() + " circle\n" + render;
  }

  @Override
  public String renderMotionsInfoSVG(double ticPerSec) {

    if (this.getMotions().isEmpty()) {
      int r = this.getColor().getRed();
      int g = this.getColor().getGreen();
      int b = this.getColor().getBlue();

      return "<ellipse id='" + this.getName() + "' cx='" + this.getX() + "' cy='" + this.getY() +
          "' rx='" + this.getW() + "' ry='" + this.getH() + "' fill='" + "rgb("
          + r + "," + g + "," + b + ")"
          + "' visibility='visible' >\n";

    } else {

      List<IMotion> motions = this.getMotions();
      IMotion motionStart = motions.get(0);
      int r1 = motionStart.getCurrentColor().getRed();
      int g1 = motionStart.getCurrentColor().getGreen();
      int b1 = motionStart.getCurrentColor().getBlue();

      String headerRender = "<ellipse id='" + this.getName() + "' cx='" + motionStart.getXStart() +
          "' cy='" + motionStart.getYStart() + "' rx='" + motionStart.getWStart() +
          "' ry='" + motionStart.getHStart() + "' fill='" + "rgb("
          + r1 + "," + g1 + "," + b1 + ")"
          + "' visibility='visible' >\n";

      String renderInfo = "";
      for (IMotion motion : motions) {
        int start = motion.getStartTick();
        int end = motion.getEndTick();
        renderInfo += motion.toStringHandleSVG(start / ticPerSec, (end / ticPerSec) -
            (start / ticPerSec), "cx", "cy", "rx", "ry");
      }
      return headerRender + renderInfo + "</ellipse>\n";

    }

  }

  @Override
  public IReadOnlyShape makeShape(int x, int y, int w, int h, Color color) {
    return new Circle(x, y, w, h, color);
  }
}
