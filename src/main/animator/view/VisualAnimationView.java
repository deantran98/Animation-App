package cs3500.animator.view;

import cs3500.animator.model.IAnimatorApp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Class represent a visual view.
 */
public class VisualAnimationView extends JFrame implements AnimatorViewForVisual {

  private final int tickPerSec;
  private final DrawingPanel drawingPanel;

  /**
   * Constructor of the visual view.
   */
  public VisualAnimationView(IAnimatorApp model, int tickPerSec) {
    super();
    this.tickPerSec = tickPerSec;
    setSize(new Dimension(model.getCanvas().getWidth(), model.getCanvas().getHeight()));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    drawingPanel = new DrawingPanel();
    setLayout(new BorderLayout());
    add(drawingPanel, BorderLayout.CENTER);
  }

  @Override
  public void render() {
    setVisible(true);
    setFocusable(true);
    requestFocus();
  }

  @Override
  public void drawEllipse(int x, int y, int w, int h, Color color) {
    this.drawingPanel.drawEllipse(x, y, w, h, color);
  }

  @Override
  public void drawRectangle(int x, int y, int w, int h, Color color) {
    this.drawingPanel.drawRectangle(x, y, w, h, color);
  }

  @Override
  public void refresh() {
    repaint();
  }

  @Override
  public int getTickPerSec() {
    int tickPerSec = this.tickPerSec;
    return tickPerSec;
  }
}
