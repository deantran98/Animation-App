package cs3500.animator.view;

import cs3500.animator.controller.ViewListener;
import cs3500.animator.controller.ViewListenerAdvance;
import cs3500.animator.model.AnimatorModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Class represent an mock for interactive view. The view is used to test to see if the controller
 * actually respond to the events that are received from the view or not.
 */
public class MockInteractiveView extends InteractiveView {

  public List<ViewListenerAdvance> list;
  public StringBuilder log;

  /**
   * Constructor of the mock interactive view.
   */
  public MockInteractiveView() {
    super(new AnimatorModel(), 1);
    this.list = new ArrayList<>();
    log = new StringBuilder();
  }


  @Override
  public void addListener(ViewListenerAdvance listener) {
    list.add(listener);
  }

  @Override
  public void resetFocus() {
    log.append("Focus is reset after the controller handle the key or button event.");

  }

  @Override
  public void displayMessage(String message) {
    log.append(
        "The controller just handle the enable/disable loop event or discrete time animation.\n");

  }

}
