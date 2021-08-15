package cs3500.animator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Class represents a drawing panel.
 */
public class DrawingPanel extends JPanel {

  private final List<IDrawingPanelShape> shapes;

  /**
   * The constructor for drawing panel.
   */
  public DrawingPanel() {
    super();
    setBackground(Color.white);
    shapes = new ArrayList<IDrawingPanelShape>();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    for (IDrawingPanelShape shape : shapes) {
      shape.draw(g);
    }

    shapes.clear();
  }

  void drawEllipse(int x, int y, int w, int h, Color color) {
    shapes.add(new DrawingPanelEllipse(x, y, w, h, color));
  }

  void drawRectangle(int x, int y, int w, int h, Color color) {
    shapes.add(new DrawingPanelRectangle(x, y, w, h, color));
  }

}
