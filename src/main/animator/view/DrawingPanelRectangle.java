package cs3500.animator.view;

import cs3500.animator.model.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;

/**
 * Class represents a drawing panel for rectangle.
 */
public class DrawingPanelRectangle extends Rectangle implements IDrawingPanelShape {

  /**
   * The constructor for rectangle panel.
   */
  public DrawingPanelRectangle(int x, int y, int width, int height, Color color) {
    super(x, y, width, height, color);
  }

  @Override
  public void draw(Graphics g) {
    Objects.requireNonNull(g);

    g.setColor(this.getColor());
    g.fillRect(this.getX(), this.getY(), this.getW(), this.getH());
  }

}
