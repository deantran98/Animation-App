package cs3500.animator.util;

import cs3500.animator.model.IAnimatorApp;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.InteractiveView;
import cs3500.animator.view.SvgView;
import cs3500.animator.view.TextualView;
import cs3500.animator.view.VisualAnimationView;

/**
 * Class represents a factory of views.
 **/
public class ViewFactory {

  /**
   * Create an instance of correct type of model view.
   **/
  public AnimatorView createView(String viewType, IAnimatorApp model, int tickPerSec,
      Appendable out) {
    if (viewType.equals("text")) {
      return new TextualView(model, tickPerSec, out);
    } else if (viewType.equals("visual")) {
      return new VisualAnimationView(model, tickPerSec);
    } else if (viewType.equals("svg")) {
      return new SvgView(model, tickPerSec, out);
    } else if (viewType.equals("interactive")) {
      return new InteractiveView(model, tickPerSec);
    } else {
      return null;
    }

  }

}
