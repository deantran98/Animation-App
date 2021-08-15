package cs3500.animator.controller;

import cs3500.animator.model.IAnimatorApp;
import cs3500.animator.model.IReadOnlyShape;
import cs3500.animator.model.ShapeType;
import cs3500.animator.view.AnimatorViewForVisual;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.swing.Timer;

/**
 * The class represents a controller for visual view.
 */
public class Controller implements IController, ActionListener {

  private final AnimatorViewForVisual view;
  private final IAnimatorApp model;
  private int tick = 1;

  /**
   * The constructor of the controller.
   */
  public Controller(AnimatorViewForVisual view, IAnimatorApp model) {
    this.view = Objects.requireNonNull(view);
    this.model = Objects.requireNonNull(model);
  }

  @Override
  public void startAnimation() throws IOException {
    this.tick = 0;

    view.render();

    int tickPerSec = this.view.getTickPerSec();
    Timer timer = new Timer(1000 / tickPerSec, this);

    timer.start();

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    List<IReadOnlyShape> shapesToRender = model.getShapesAtGivenTick(tick);

    for (IReadOnlyShape shape : shapesToRender) {

      if (shape.getType().equals(ShapeType.RECTANGLE)) {
        view.drawRectangle(shape.getX(), shape.getY(), shape.getW(), shape.getH(),
            shape.getColor());
      } else {
        view.drawEllipse(shape.getX(), shape.getY(), shape.getW(), shape.getH(),
            shape.getColor());
      }
    }

    view.refresh();
    tick++;
  }

}
