package cs3500.animator.view;

import java.awt.Graphics;

/**
 * Panel to draw shapes on canvas.
 */
public interface IDrawingPanelShape {

  /**
   * Draw a shape on canvas.
   *
   * @param g a graphic to draw
   */
  void draw(Graphics g);
}
