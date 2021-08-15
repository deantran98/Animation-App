package cs3500.animator.model;

import java.awt.Color;
import java.util.List;

/**
 * The interface for shapes.
 */
public interface IShape {

  /**
   * Get the name id for this shape.
   *
   * @return the name as a string
   */
  String getName();

  /**
   * Add a motion to this shape. A new motion will be added at the end of the list, and it
   * has to be connected with the previous motion (tick end of previous motion = tick start of this
   * motion).
   * @param motion a motion to be added
   * @throws IllegalArgumentException if the motion is null or the motion is already existed in this
   *                                  shape or its ticks conflict with others
   */
  void addMotion(IMotion motion);

  /**
   * Remove a motion to this shape. If the motion isn't at head or tail of the list, replace it
   * with a new motion to fill a gap.
   *
   * @param motion a motion to be removed
   * @throws IllegalArgumentException if the motion is null or couldn't find the motion
   */
  void removeMotion(IMotion motion);

  /**
   * Getter method to get a list of motions in this shape.
   *
   * @return a list of motions
   */
  List<IMotion> getMotions();

  /**
   * Display all information of motions of this shape in tick.
   *
   * @return an information as a string
   */
  String renderMotionsInfo();

  /**
   * Display all information of motions of this shape in seconds.
   *
   * @param ticPerSec a rate of tick
   * @return an information as a string
   */
  String renderMotionsInfoInSecTick(double ticPerSec);

  /**
   * Display all information of motions of this shape in SVG style.
   *
   * @param ticPerSec a rate of tick
   * @return an information as a string
   */
  String renderMotionsInfoSVG(double ticPerSec);

  /**
   * Getter method to get a x coordinate.
   *
   * @return a x coordinate as integer
   */
  int getX();

  /**
   * Getter method to get a y coordinate.
   *
   * @return a y coordinate as integer
   */
  int getY();

  /**
   * Getter method to get a width.
   *
   * @return a width as integer
   */
  int getW();

  /**
   * Getter method to get a height.
   *
   * @return a height as integer
   */
  int getH();

  /**
   * Getter method to get a color.
   *
   * @return a color
   */
  Color getColor();

  /**
   * Getter method to get a type of shape.
   *
   * @return a shape type
   */
  ShapeType getType();

}
