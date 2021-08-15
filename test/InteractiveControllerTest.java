import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.InteractiveController;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorApp;
import cs3500.animator.view.MockInteractiveView;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * The class tests whether the controller responds to the events that are received from the
 * interactive view or not.
 */
public class InteractiveControllerTest {

  public MockInteractiveView view;
  public IAnimatorApp model;
  public InteractiveController controller;

  @Before
  public void setUp() {
    view = new MockInteractiveView();
    model = new AnimatorModel();
    controller = new InteractiveController(view, model);
  }

  @Test
  public void testControllerHandleStartOrResumeKey() throws IOException {
    view.addListener(controller);
    controller.startAnimation();
    view.list.get(0).getStartOrResume();
    assertEquals("Focus is reset after the controller handle the key or button event.",
        view.log.toString());
  }

  @Test
  public void testControllerHandlePause() throws IOException {
    view.addListener(controller);
    controller.startAnimation();
    view.list.get(0).pauseAnimation();
    assertEquals("Focus is reset after the controller handle the key or button event.",
        view.log.toString());
  }

  @Test
  public void testControllerHandleRestart() throws IOException {
    view.addListener(controller);
    controller.startAnimation();
    view.list.get(0).restartAnimation();
    assertEquals("Focus is reset after the controller handle the key or button event.",
        view.log.toString());
  }

  @Test
  public void testControllerHandleIncreaseSpeed() throws IOException {
    view.addListener(controller);
    controller.startAnimation();
    view.list.get(0).increaseSpeed();
    assertEquals("Focus is reset after the controller handle the key or button event.",
        view.log.toString());
  }

  @Test
  public void testControllerHandleDecreaseSpeed() throws IOException {
    view.addListener(controller);
    controller.startAnimation();
    view.list.get(0).decreaseSpeed();
    assertEquals("Focus is reset after the controller handle the key or button event.",
        view.log.toString());
  }

  @Test
  public void testControllerHandleTriggerLoop() throws IOException {
    view.addListener(controller);
    controller.startAnimation();
    view.list.get(0).triggerLooping();
    assertEquals(
        "The controller just handle the enable/disable loop event or discrete time animation.\n"
            + "Focus is reset after the controller handle the key or button event.",
        view.log.toString());
  }

  @Test
  public void testControllerHandleDiscreteTime() throws IOException {
    view.addListener(controller);
    controller.startAnimation();
    view.list.get(0).triggerDiscreteTime();
    assertEquals(
        "The controller just handle the enable/disable loop event or discrete time animation.\n"
            + "Focus is reset after the controller handle the key or button event.",
        view.log.toString());
  }

}