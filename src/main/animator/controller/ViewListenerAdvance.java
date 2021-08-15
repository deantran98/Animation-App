package cs3500.animator.controller;

/**
 * Advanced listener that provide the new feature to switch to discrete time animation.
 */
public interface ViewListenerAdvance extends ViewListener {
  /**
   * Trigger the discrete time animation.
   */
  void triggerDiscreteTime();

}
