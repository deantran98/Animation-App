package cs3500.animator.model;

/**
 * The class represents a canvas.
 */
public class Canvas implements ICanvas {
  private final int leftMostBound;
  private final int topMostBound;
  private final int widthBound;
  private final int heightBound;

  /**
   * The constructor of canvas.
   */
  public Canvas(int left, int top, int width, int height) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Canvas variables can't be negative.");
    }

    this.leftMostBound = left;
    this.topMostBound = top;
    this.widthBound = width;
    this.heightBound = height;
  }

  @Override
  public int getLeftMost() {
    int leftMost = this.leftMostBound;
    return leftMost;
  }

  @Override
  public int getTopMost() {
    int topMost = this.topMostBound;
    return topMost;
  }

  @Override
  public int getWidth() {
    int width = this.widthBound;
    return width;
  }

  @Override
  public int getHeight() {
    int height = this.heightBound;
    return height;
  }
}
