package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import java.io.IOException;

/**
 * Renders a {@link AnimatorModel} in some manner.
 */
public interface AnimatorView {

  /**
   * Renders an animation in the model.
   *
   * @throws IOException if the rendering fails for some reason
   */
  void render() throws IOException;

}
