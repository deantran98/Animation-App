package cs3500.animator;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimatorApp;
import cs3500.animator.model.IShape;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.CreateAnimationView;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A main class that construct our own animations.
 */
public class CreatingAnimations {

  /**
   * A main class that construct my own animations.
   */
  public static void main(String[] args) throws IOException {

    //Create the find red circle animation
    //Initialize the find red circle animation with motions at tick = 0
    IAnimatorApp findRedCircleAnimation = AnimatorModel.getBuilder().
        setBounds(0, 0, 800, 800).
        declareShape("C1", "circle").declareShape("C2", "circle").
        declareShape("C3", "circle").declareShape("C4", "circle").
        declareShape("C5", "circle").
        addMotion("C1", 0, 100, 400, 30, 30, 255, 0, 0, 0,
            100, 400, 30, 30, 255, 0, 0).
        addMotion("C2", 0, 150, 400, 30, 30, 0, 255, 0, 0,
            150, 400, 30, 30, 0, 255, 0).
        addMotion("C3", 0, 200, 400, 30, 30, 0, 0, 255, 0,
            200, 400, 30, 30, 0, 0, 255).
        addMotion("C4", 0, 250, 400, 30, 30, 255, 255, 0, 0,
            250, 400, 30, 30, 255, 255, 0).
        addMotion("C5", 0, 300, 400, 30, 30, 0, 255, 255, 0,
            300, 400, 30, 30, 0, 255, 255).build();

    Map<String, IShape> shapesInModel = findRedCircleAnimation.getShapes();
    Set<String> shapesID = shapesInModel.keySet();
    List<String> listID = new ArrayList<>(shapesID);

    //Use binary search to generate the motions that find the red circle and change that red circle
    // to black circle in the animation (C1 is the red circle in the animation)
    BinarySearchAlgorithm algorithm = new BinarySearchAlgorithm();
    algorithm.binarySearch(listID, 0, 4, "C1", findRedCircleAnimation);

    //Write the animation to the find writer (txt file) using the render method in the class
    // CreateAnimationView
    FileWriter fw = new FileWriter("find-red-animation.txt");
    AnimatorView viewForFindRedCircleAnimation = new CreateAnimationView(findRedCircleAnimation,
        fw);
    viewForFindRedCircleAnimation.render();
    fw.close();

    //Create the Tic Tac Toe Animation manually
    IAnimatorApp makeTicTacToeAnimation = AnimatorModel.getBuilder().
        setBounds(0, 0, 600, 600).
        declareShape("R1", "rectangle").declareShape("R2", "rectangle").
        declareShape("R3", "rectangle").declareShape("R4", "rectangle").
        declareShape("RR1", "rectangle").declareShape("RR2", "rectangle").
        declareShape("RR3", "rectangle").declareShape("RR4", "rectangle").
        declareShape("C1", "circle").declareShape("C2", "circle").
        declareShape("C3", "circle").
        addMotion("R1", 0, 0, 100, 300, 10, 0, 0, 0, 16,
            0, 100, 300, 10, 0, 0, 0).
        addMotion("R2", 0, 0, 200, 300, 10, 0, 0, 0, 16,
            0, 200, 300, 10, 0, 0, 0).
        addMotion("R3", 0, 100, 0, 10, 300, 0, 0, 0, 16,
            100, 0, 10, 300, 0, 0, 0).
        addMotion("R4", 0, 200, 0, 10, 300, 0, 0, 0, 16,
            200, 0, 10, 300, 0, 0, 0).
        addMotion("RR1", 2, 130, 130, 50, 50, 0, 0, 255, 14,
            130, 130, 50, 50, 0, 0, 255).
        addMotion("RR1", 14, 130, 130, 50, 50, 0, 0, 255, 16,
            130, 130, 50, 50, 0, 255, 0).
        addMotion("RR2", 6, 230, 130, 50, 50, 0, 0, 255, 16,
            230, 130, 50, 50, 0, 0, 255).
        addMotion("RR3", 10, 130, 30, 50, 50, 0, 0, 255, 14,
            130, 30, 50, 50, 0, 0, 255).
        addMotion("RR3", 14, 130, 30, 50, 50, 0, 0, 255, 16,
            130, 30, 50, 50, 0, 255, 0).
        addMotion("RR4", 14, 130, 230, 50, 50, 0, 0, 255, 16,
            130, 230, 50, 50, 0, 255, 0).
        addMotion("C1", 4, 230, 30, 50, 50, 255, 0, 0, 16,
            230, 30, 50, 50, 255, 0, 0).
        addMotion("C2", 8, 30, 130, 50, 50, 255, 0, 0, 16,
            30, 130, 50, 50, 255, 0, 0).
        addMotion("C3", 12, 30, 30, 50, 50, 255, 0, 0, 16,
            30, 30, 50, 50, 255, 0, 0).build();

    //Write the animation to the find writer (txt file) using the render method in the class
    // CreateAnimationView
    FileWriter fw1 = new FileWriter("tic-tac-toe.txt");
    AnimatorView viewForTicTacToeAnimation = new CreateAnimationView(makeTicTacToeAnimation, fw1);
    viewForTicTacToeAnimation.render();
    fw1.close();

  }

}
