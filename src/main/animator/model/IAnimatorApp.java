package cs3500.animator.model;

import java.util.List;
import java.util.Map;

/**
 * The interface for Animator Apps: uses to represent an animation.
 */
public interface IAnimatorApp {

  /**
   * Add a shape to the model.
   *
   * @param shapeId a specific name for the shape
   * @param type    a specific type for the shape (example: rectangle, oval, etc.)
   * @throws IllegalArgumentException if the shapeId and type are null or empty strings, or if the
   *                                  shapeId is already existed
   */
  void addShape(String shapeId, String type);

  /**
   * Remove a shape from the model.
   *
   * @param shapeId a specific name of the shape want to remove
   * @throws IllegalArgumentException if the shapeId is null or empty string, or if the shapeId
   *                                  isn't existed in the model
   * @throws IllegalStateException    if the model is empty (has no shape in the list of shapes)
   */
  void removeShape(String shapeId);

  /**
   * Define which shape appears on top when 2 shapes are collided (shape appears first in the map).
   *
   * @param shape1 a shape in the model
   * @param shape2 a shape in the model
   * @return a shape appears on top
   * @throws IllegalArgumentException if the shape1 or shape2 is null, or if one of the shapes isn't
   *                                  existed in the model
   * @throws IllegalStateException    if the model is empty or has less than 2 shapes
   */
  IShape shapeShowOnTop(IShape shape1, IShape shape2);

  /**
   * Add a motion to the shape in model. A new motion will be added at the end of the list, and it
   * has to be connected with the previous motion (tick end of previous motion = tick start of this
   * motion).
   *
   * @param shapeId a shape's name to add motion
   * @param motion  a motion to be added
   * @throws IllegalArgumentException if the shapeId or motion is null, or if the shapeId doesn't
   *                                  match with any shapeId in the model
   * @throws IllegalStateException    if the motion is already existed or conflicts with other
   *                                  ticks
   */
  void addMotion(String shapeId, IMotion motion);

  /**
   * Remove a motion from the shape in model. If the motion isn't at head or tail of the list,
   * replace it with a new motion to fill a gap.
   *
   * @param shapeId a shape's name to remove motion
   * @param motion  a motion to be removed
   * @throws IllegalArgumentException if the shapeId or motion is null, or if the shapeId/motion
   *                                  couldn't be found in the model
   * @throws IllegalStateException    if the model has no shapes (empty list of shapes)
   */
  void removeMotion(String shapeId, IMotion motion);

  /**
   * Getter method to get all shapes in this model.
   *
   * @return a map of shapes
   *
   * @throws IllegalStateException    if the model has no shapes (empty list of shapes)
   */
  Map<String, IShape> getShapes();

  /**
   * Getter method to get the shapes at the given tick.
   *
   * @param tick  a tick where users want to get all shapes at that tick
   * @return a list of shapes
   *
   * @throws IllegalArgumentException if the tick is negative, or if the tick couldn't be find in
   *                                  the model
   * @throws IllegalStateException    if the model has no shapes (empty list of shapes)
   */
  List<IReadOnlyShape> getShapesAtGivenTick(int tick);

  /**
   * Getter method to get the shapes at the discrete time.
   *
   * @param tick  a tick where users want to get all shapes at that tick
   * @return a list of shapes
   *
   * @throws IllegalArgumentException if the tick is negative, or if the tick couldn't be find in
   *                                  the model
   * @throws IllegalStateException    if the model has no shapes (empty list of shapes)
   */
  List<IReadOnlyShape> getShapesAtDiscreteTime(int tick);

  /**
   * Getter method to get a list of motions from the given name of shape in model.
   *
   * @param shapeId  a shape's name to get all motions of that shape
   * @return a list of motions
   *
   * @throws IllegalArgumentException if the shapeId is null or empty string, or if the shapeId
   *                                  couldn't be found in the model
   * @throws IllegalStateException    if the model has no shapes (empty list of shapes)
   */
  List<IMotion> getMotions(String shapeId);

  /**
   * Getter method to get all motions from all the shapes in model.
   *
   * @return a list of list of motions
   *
   * @throws IllegalStateException    if the model has no shapes (empty list of shapes)
   */
  List<List<IMotion>> getAllMotions();

  /**
   * Display all information of motions of each shape in the model.
   *
   * @return an information as a string
   */
  String renderInformation();

  /**
   * Getter method to get a canvas for this model.
   *
   * @return a canvas
   */
  ICanvas getCanvas();

}
