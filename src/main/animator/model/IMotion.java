package cs3500.animator.model;

import java.awt.Color;

/**
 * The interface of motion.
 */
public interface IMotion {

  /**
   * Getter method to get the start tick.
   *
   * @return the start tick as an integer
   */
  int getStartTick();

  /**
   * Getter method to get the end tick.
   *
   * @return the end tick as an integer
   */
  int getEndTick();

  /**
   * Getter method to get the x start.
   *
   * @return the x start as an integer
   */
  int getXStart();

  /**
   * Getter method to get the y start.
   *
   * @return the y start as an integer
   */
  int getYStart();

  /**
   * Getter method to get the w start.
   *
   * @return the w start as an integer
   */
  int getWStart();

  /**
   * Getter method to get the h start.
   *
   * @return the h start as an integer
   */
  int getHStart();

  /**
   * Getter method to get the current color.
   *
   * @return the current color
   */
  Color getCurrentColor();

  /**
   * Getter method to get the x end.
   *
   * @return the x end as an integer
   */
  int getXEnd();

  /**
   * Getter method to get the y end.
   *
   * @return the y end as an integer
   */
  int getYEnd();

  /**
   * Getter method to get the w end.
   *
   * @return the w end as an integer
   */
  int getWEnd();

  /**
   * Getter method to get the h end.
   *
   * @return the h end as an integer
   */
  int getHEnd();

  /**
   * Getter method to get the new color.
   *
   * @return the new color
   */
  Color getNewColor();

  /**
   * Display information of this motion in second tick.
   *
   * @param tickStart a start tick in sec
   * @param tickEnd an end tick in sec
   * @return the information of a motion as string
   */
  String toStringHandleSecTick(double tickStart, double tickEnd);

  /**
   * Display information of this motion in SVG style.
   *
   * @param tickStart a start tick in sec
   * @param duration a duration of this motion
   * @param attributeX an attribution of x coordinate in SGV style
   * @param attributeY an attribution of y coordinate in SGV style
   * @param attributeW an attribution of width in SGV style
   * @param attributeH an attribution of height in SGV style
   * @return the information of a motion as string
   */
  String toStringHandleSVG(double tickStart, double duration, String attributeX, String attributeY,
      String attributeW, String attributeH);

  /**
   * Getter a new motion that is the same with this motion but has new start tick and end tick.
   *
   * @param newStart a new start tick to replace
   * @param newEnd a new end tick to replace
   * @return a new motion
   */
  IMotion fillGapMotion(int newStart, int newEnd);

  /**
   * Transform a motion at given tick to IReadOnlyShape.
   *
   * @param tick a current tick to transform
   * @param type a type of shape to create
   * @return an IReadOnlyShape
   */
  IReadOnlyShape apply(int tick, ShapeType type);

  /**
   * Transform a motion at discrete time to IReadOnlyShape.
   *
   * @param tick a current tick to transform
   * @param type a type of shape to create
   * @return an IReadOnlyShape
   */
  IReadOnlyShape applyDiscreteTime(int tick, ShapeType type);

}
