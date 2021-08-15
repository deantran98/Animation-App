package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class for shapes.
 */
public class AbstractShape implements IShape, IReadOnlyShape {

  private final String name;
  private final int tick;
  private final int x;
  private final int y;
  private final int w;
  private final int h;
  private final Color color;
  private final List<IMotion> motions;

  /**
   * The constructor of the abstract shape.
   */
  public AbstractShape(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("Name can't be null.");
    }

    if (name.equals("")) {
      throw new IllegalArgumentException("Name can't be empty string.");
    }

    this.name = name;
    this.tick = 0;
    this.x = 0;
    this.y = 0;
    this.w = 0;
    this.h = 0;
    this.color = Color.WHITE;
    this.motions = new ArrayList<>();

  }

  /**
   * The constructor of the abstract shape to draw.
   */
  public AbstractShape(int x, int y, int width, int height, Color color) {
    this.name = "";
    this.tick = 0;
    this.x = x;
    this.y = y;
    this.w = width;
    this.h = height;
    this.color = color;
    this.motions = new ArrayList<>();

  }

  @Override
  public String getName() {
    String name = "";
    name += this.name;
    return name;
  }

  @Override
  public void addMotion(IMotion motion) {
    if (motion == null) {
      throw new IllegalArgumentException("Motion can't be null.");
    }

    if (motions.contains(motion)) {
      throw new IllegalArgumentException("Motion is already existed.");
    }

    if (!motions.isEmpty()) {
      if (motions.get(motions.size() - 1).getEndTick() != motion.getStartTick()) {
        throw new IllegalArgumentException("Motion need to be connected.");
      }

      IMotion previousMotion = motions.get(motions.size() - 1);

      if (previousMotion.getXEnd() != motion.getXStart() || previousMotion.getYEnd() != motion
          .getYStart() ||
          previousMotion.getWEnd() != motion.getWStart() || previousMotion.getHEnd() != motion
          .getHStart() || !previousMotion.getNewColor().equals(motion.getCurrentColor()) ||
          previousMotion.getEndTick() != motion.getStartTick()) {
        throw new IllegalArgumentException("Motion need to be connected.");
      }

    }

    if ((motion.getXStart() != motion.getXEnd() || motion.getYStart() != motion.getYEnd()) &&
        motion.getWStart() == motion.getWEnd() && motion.getHStart() == motion.getHEnd() &&
        motion.getCurrentColor().equals(motion.getNewColor())) {
      IMotion move = new MoveMotion(motion.getStartTick(), motion.getEndTick(), motion.getXStart(),
          motion.getYStart(), motion.getWStart(), motion.getHStart(), motion.getCurrentColor(),
          motion.getXEnd(), motion.getYEnd());
      motions.add(move);
    } else if ((motion.getWStart() != motion.getWEnd() || motion.getHStart() != motion.getHEnd()) &&
        motion.getXStart() == motion.getXEnd() && motion.getYStart() == motion.getYEnd() &&
        motion.getCurrentColor().equals(motion.getNewColor())) {
      IMotion scale = new ScaleMotion(motion.getStartTick(), motion.getEndTick(),
          motion.getXStart(),
          motion.getYStart(), motion.getWStart(), motion.getHStart(), motion.getCurrentColor(),
          motion.getWEnd(), motion.getHEnd());
      motions.add(scale);
    } else if (!motion.getCurrentColor().equals(motion.getNewColor()) &&
        motion.getXStart() == motion.getXEnd() && motion.getYStart() == motion.getYEnd() &&
        motion.getWStart() == motion.getWEnd() && motion.getHStart() == motion.getHEnd()) {
      IMotion changeColor = new ChangeColorMotion(motion.getStartTick(), motion.getEndTick(),
          motion.getXStart(),
          motion.getYStart(), motion.getWStart(), motion.getHStart(), motion.getCurrentColor(),
          motion.getNewColor());
      motions.add(changeColor);
    } else {
      IMotion multipleMotion = new Motion(motion.getStartTick(), motion.getEndTick(),
          motion.getXStart(),
          motion.getYStart(), motion.getWStart(), motion.getHStart(), motion.getCurrentColor(),
          motion.getXEnd(), motion.getYEnd(), motion.getWEnd(), motion.getHEnd(),
          motion.getNewColor());
      motions.add(multipleMotion);
    }

  }

  @Override
  public void removeMotion(IMotion motion) {
    if (motion == null) {
      throw new IllegalArgumentException("Motion can't be null.");
    }

    if (motions.isEmpty()) {
      throw new IllegalStateException("No motion to be removed. The list is empty.");
    }

    if (!motions.contains(motion)) {
      throw new IllegalArgumentException("Motion can't be found.");
    }

    for (int i = 0; i < motions.size(); i++) {
      if (motions.get(i).equals(motion) && (i == 0 || i == motions.size() - 1)) {
        motions.remove(motion);
        break;
      }

      if (motions.get(i).equals(motion)) {
        int newStartTick = motion.getStartTick();
        int newEndTick = motion.getEndTick();
        IMotion fillGapMotion = motions.get(i - 1).fillGapMotion(newStartTick, newEndTick);

        motions.set(i, fillGapMotion);
        break;
      }
    }

  }

  @Override
  public List<IMotion> getMotions() {
    ArrayList<IMotion> motions = new ArrayList<IMotion>(this.motions);
    return motions;
  }

  @Override
  public String renderMotionsInfo() {
    return "";
  }

  @Override
  public String renderMotionsInfoInSecTick(double ticPerSec) {
    return "";
  }

  @Override
  public String renderMotionsInfoSVG(double ticPerSec) {
    return "";
  }

  @Override
  public int getX() {
    int x = this.x;
    return x;
  }

  @Override
  public int getY() {
    int y = this.y;
    return y;
  }

  @Override
  public int getW() {
    int w = this.w;
    return w;
  }

  @Override
  public int getH() {
    int h = this.h;
    return h;
  }

  @Override
  public Color getColor() {
    Color color = this.color;
    return color;
  }

  @Override
  public ShapeType getType() {
    return null;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof IMotion)) {
      return false;
    }

    return ((AbstractShape) that).tick == this.tick &&
        ((AbstractShape) that).x == this.x &&
        ((AbstractShape) that).y == this.y &&
        ((AbstractShape) that).w == this.w &&
        ((AbstractShape) that).h == this.h &&
        ((AbstractShape) that).color == this.color &&
        ((AbstractShape) that).name.equals(this.name) &&
        ((AbstractShape) that).motions.containsAll(this.motions);
  }

  @Override
  public int hashCode() {
    //return Objects.hashCode(this);
    //override hashCode cause the crashed of visual view
    return 0;
  }

  @Override
  public IReadOnlyShape makeShape(int x, int y, int w, int h, Color color) {
    return null;
  }

}
