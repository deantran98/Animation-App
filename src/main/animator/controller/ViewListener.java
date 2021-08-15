package cs3500.animator.controller;

/**
 * Listener that listen to events from the view and respond to these events.
 */
public interface ViewListener {

  /**
   * Start the animation or resume if it's stopped.
   */
  void getStartOrResume();

  /**
   * Stop the animation.
   */
  void pauseAnimation();

  /**
   * Restart the animation.
   */
  void restartAnimation();

  /**
   * Trigger the looping of the animation.
   */
  void triggerLooping();

  /**
   * Increase the speed of the animation.
   */
  void increaseSpeed();

  /**
   * Decrease the speed of the animation.
   */
  void decreaseSpeed();

}
