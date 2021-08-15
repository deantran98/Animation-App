package cs3500.animator.model;

import java.awt.Color;

/**
 * The interface for shapes to read only (display) for visual view.
 */
public interface IReadOnlyShape extends IShape {

  /**
   * A factory method to create an instance of IReadOnlyShape.
   *
   * @param x a x coordinate
   * @param y a y coordinate
   * @param w a width
   * @param h a height
   * @param color a color
   * @return an IReadOnlyShape
   */
  IReadOnlyShape makeShape(int x, int y, int w, int h, Color color);
}
