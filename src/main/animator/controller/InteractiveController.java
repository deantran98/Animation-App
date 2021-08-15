package cs3500.animator.controller;

import cs3500.animator.model.IAnimatorApp;
import cs3500.animator.model.IReadOnlyShape;
import cs3500.animator.model.ShapeType;
import cs3500.animator.view.InteractiveView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.Timer;

/**
 * The class represents a controller for interactive view. The controller will respond to the events
 * that are received from the view.
 */
public class InteractiveController implements IController, ViewListenerAdvance {

  private final InteractiveView view;
  private final IAnimatorApp model;
  private int tick = 1;
  private final int timerDelay;
  private Timer timer;
  private boolean isLooping = false;
  private boolean isPlayingDiscreteTime = false;

  /**
   * The constructor of the controller.
   */
  public InteractiveController(InteractiveView view, IAnimatorApp model) {
    this.view = Objects.requireNonNull(view);
    this.model = Objects.requireNonNull(model);
    this.view.addListener(this);
    this.timerDelay = 1000 / this.view.getTickPerSec();
  }

  @Override
  public void startAnimation() throws IOException {
    this.tick = 0;
    view.render();

    this.timer = new Timer(timerDelay, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //List<IReadOnlyShape> shapesToRender = model.getShapesAtGivenTick(tick);
        List<IReadOnlyShape> shapesToRender = new ArrayList<IReadOnlyShape>();
        if (isPlayingDiscreteTime) {
          shapesToRender = model.getShapesAtDiscreteTime(tick);
        }

        if (!isPlayingDiscreteTime) {
          shapesToRender = model.getShapesAtGivenTick(tick);
        }

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
        if (isLooping && shapesToRender.size() == 0) {
          tick = 0;
        }
        tick++;

      }
    });

  }

  @Override
  public void getStartOrResume() {
    timer.start();
    this.view.resetFocus();
  }

  @Override
  public void pauseAnimation() {
    timer.stop();
    this.view.resetFocus();
  }

  @Override
  public void restartAnimation() {
    this.tick = 0;
    timer.setDelay(this.timerDelay);
    timer.restart();
    this.view.resetFocus();
  }

  @Override
  public void triggerLooping() {
    isLooping = !isLooping;
    if (isLooping) {
      view.displayMessage("Looping is enable.");
    }
    if (!isLooping) {
      view.displayMessage("Looping is disable.");
    }

    this.view.resetFocus();
  }

  @Override
  public void increaseSpeed() {
    timer.setDelay(this.timerDelay / 2);
    this.view.resetFocus();
  }

  @Override
  public void decreaseSpeed() {
    timer.setDelay(this.timerDelay * 2);
    this.view.resetFocus();
  }

  @Override
  public void triggerDiscreteTime() {
    isPlayingDiscreteTime = !isPlayingDiscreteTime;
    if (isLooping) {
      view.displayMessage("Playing discrete time is enable.");
    }
    if (!isLooping) {
      view.displayMessage("Playing discrete time is disable.");
    }

    this.view.resetFocus();

  }

}
