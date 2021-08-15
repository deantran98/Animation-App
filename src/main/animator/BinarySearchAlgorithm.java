package cs3500.animator;

import cs3500.animator.model.ChangeColorMotion;
import cs3500.animator.model.IAnimatorApp;
import cs3500.animator.model.IMotion;
import java.awt.Color;
import java.util.List;

/**
 * The class represents binary search algorithm.
 */
public class BinarySearchAlgorithm {

  /**
   * The method using binary search to add motions to find red circle and change it to black.
   */
  public void binarySearch(List<String> shapeIDs, int left, int right, String shapeToFind,
      IAnimatorApp model) {
    if (right >= left) {
      int mid = left + (right - left) / 2;

      //Found the shape and change its color to black
      if (shapeIDs.get(mid).equals(shapeToFind)) {
        List<IMotion> motionsOfShapeToFind = model.getMotions(shapeToFind);
        IMotion lastMotionOfFind = motionsOfShapeToFind.get(motionsOfShapeToFind.size() - 1);
        model.addMotion(shapeToFind, new ChangeColorMotion(lastMotionOfFind.getEndTick(),
            lastMotionOfFind.getEndTick() + 8, lastMotionOfFind.getXEnd(),
            lastMotionOfFind.getYEnd(),
            lastMotionOfFind.getWEnd(), lastMotionOfFind.getHEnd(), lastMotionOfFind.getNewColor(),
            Color.BLACK));
      }

      //Couldn't find the shape. Narrow down the search to the left side and change color of all
      //the right side shapes to white.
      else if (Integer.parseInt(shapeIDs.get(mid).substring(1)) >
          Integer.parseInt(shapeToFind.substring(1))) {
        //add motion that do nothing for shapes on the left side
        for (int i = mid + 1; i < shapeIDs.size(); i++) {
          List<IMotion> motions = model.getMotions(shapeIDs.get(i));
          IMotion lastMotion = motions.get(motions.size() - 1);
          model.addMotion(shapeIDs.get(i), new ChangeColorMotion(lastMotion.getEndTick(),
              lastMotion.getEndTick() + 4, lastMotion.getXEnd(), lastMotion.getYEnd(),
              lastMotion.getWEnd(), lastMotion.getHEnd(), lastMotion.getNewColor(),
              lastMotion.getNewColor()));
        }

        //add motion that change color to white for shapes on the right side
        for (int i = 0; i < mid + 1; i++) {
          List<IMotion> motions = model.getMotions(shapeIDs.get(i));
          IMotion lastMotion = motions.get(motions.size() - 1);
          model.addMotion(shapeIDs.get(i), new ChangeColorMotion(lastMotion.getEndTick(),
              lastMotion.getEndTick() + 4, lastMotion.getXEnd(), lastMotion.getYEnd(),
              lastMotion.getWEnd(), lastMotion.getHEnd(), lastMotion.getNewColor(), Color.WHITE));
        }
        binarySearch(shapeIDs, mid + 1, right, shapeToFind, model);
      }
      //Couldn't find the shape. Narrow down the search to the right side and change color of all
      //the left side shapes to white.
      else {
        //add motion that change color to white for shapes on the left side
        for (int i = mid + 1; i < shapeIDs.size(); i++) {
          List<IMotion> motions = model.getMotions(shapeIDs.get(i));
          IMotion lastMotion = motions.get(motions.size() - 1);
          model.addMotion(shapeIDs.get(i), new ChangeColorMotion(lastMotion.getEndTick(),
              lastMotion.getEndTick() + 4, lastMotion.getXEnd(), lastMotion.getYEnd(),
              lastMotion.getWEnd(), lastMotion.getHEnd(), lastMotion.getNewColor(), Color.WHITE));
        }

        //add motion that do nothing for shapes on the right side
        for (int i = 0; i < mid + 1; i++) {
          List<IMotion> motions = model.getMotions(shapeIDs.get(i));
          IMotion lastMotion = motions.get(motions.size() - 1);
          model.addMotion(shapeIDs.get(i), new ChangeColorMotion(lastMotion.getEndTick(),
              lastMotion.getEndTick() + 4, lastMotion.getXEnd(), lastMotion.getYEnd(),
              lastMotion.getWEnd(), lastMotion.getHEnd(), lastMotion.getNewColor(),
              lastMotion.getNewColor()));
        }
        binarySearch(shapeIDs, left, mid - 1, shapeToFind, model);
      }

    }

  }

}
