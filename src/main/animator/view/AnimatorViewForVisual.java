package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import java.awt.Color;

/**
 * Renders a {@link AnimatorModel} in some manner. Have some advanced methods that only need for
 * visual view.
 */
public interface AnimatorViewForVisual extends AnimatorView {

  /**
   * Draw an ellipse on canvas.
   *
   * @param x     a x coordinate
   * @param y     a y coordinate
   * @param w     a width
   * @param h     a height
   * @param color a color
   */
  void drawEllipse(int x, int y, int w, int h, Color color);

  /**
   * Draw a rectangle on canvas.
   *
   * @param x     a x coordinate
   * @param y     a y coordinate
   * @param w     a width
   * @param h     a height
   * @param color a color
   */
  void drawRectangle(int x, int y, int w, int h, Color color);

  /**
   * Refresh the painting on canvas.
   */
  void refresh();

  /**
   * Getter method to get tick per sec.
   */
  int getTickPerSec();
}
