package cs3500.animator.view;

import cs3500.animator.model.Circle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;

/**
 * Class represents a drawing panel for ellipse.
 */
public class DrawingPanelEllipse extends Circle implements IDrawingPanelShape {

  /**
   * The constructor for ellipse panel.
   */
  public DrawingPanelEllipse(int x, int y, int width, int height, Color color) {
    super(x, y, width, height, color);
  }

  @Override
  public void draw(Graphics g) {
    Objects.requireNonNull(g);

    g.setColor(this.getColor());
    g.fillOval(this.getX(), this.getY(), this.getW(), this.getH());
  }

}
