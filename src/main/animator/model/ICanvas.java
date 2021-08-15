package cs3500.animator.model;

/**
 * The interface for canvas.
 */
public interface ICanvas {

  /**
   * Getter method to get a left most of this canvas.
   *
   * @return a left most as an integer
   */
  int getLeftMost();

  /**
   * Getter method to get a top most of this canvas.
   *
   * @return a top most as an integer
   */
  int getTopMost();

  /**
   * Getter method to get a width of this canvas.
   *
   * @return a width as an integer
   */
  int getWidth();

  /**
   * Getter method to get a height of this canvas.
   *
   * @return a height as an integer
   */
  int getHeight();
}
