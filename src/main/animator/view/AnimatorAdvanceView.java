package cs3500.animator.view;

import cs3500.animator.controller.ViewListenerAdvance;
import cs3500.animator.model.AnimatorModel;

/**
 * Renders a {@link AnimatorModel} in some manner. Have some advanced methods that only need for
 * interactive view.
 */
public interface AnimatorAdvanceView {

  /**
   * Add a view listener to this view.
   *
   * @param listener a listener to add
   */
  void addListener(ViewListenerAdvance listener);

  /**
   * Reset the focus of this view.
   */
  void resetFocus();

  /**
   * Display a given message on this view.
   *
   * @param message a message to display
   */
  void displayMessage(String message);

}
