package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The class represents the animator model.
 */
public class AnimatorModel implements IAnimatorApp {

  private final Map<String, IShape> currentShapes;
  private final ICanvas canvas;

  public AnimatorModel() {
    this.currentShapes = new HashMap<>();
    this.canvas = new Canvas(0, 0, 0, 0);
  }

  protected AnimatorModel(IAnimatorApp model, ICanvas canvas) {
    this.currentShapes = model.getShapes();
    this.canvas = canvas;
  }

  //Implement the Builder pattern
  public static Builder getBuilder() {
    return new Builder();
  }

  /**
   * The class represents a builder.
   */
  public static final class Builder implements AnimationBuilder<IAnimatorApp> {

    /**
     * The constructor of builder.
     */
    private final IAnimatorApp model;
    private ICanvas canvas;

    public Builder() {
      this.model = new AnimatorModel();
      this.canvas = new Canvas(0, 0, 0, 0);
    }

    @Override
    public IAnimatorApp build() {
      return new AnimatorModel(this.model, this.canvas);
    }

    @Override
    public AnimationBuilder<IAnimatorApp> setBounds(int x, int y, int width, int height) {
      this.canvas = new Canvas(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<IAnimatorApp> declareShape(String name, String type) {
      model.addShape(name, type);
      return this;
    }

    @Override
    public AnimationBuilder<IAnimatorApp> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {
      Color startColor = new Color(r1, g1, b1);
      Color endColor = new Color(r2, g2, b2);

      if ((x1 != x2 || y1 != y2) && w1 == w2 && h1 == h2 && startColor.equals(endColor)) {
        IMotion motionToAdd = new MoveMotion(t1, t2, x1, y1, w1, h1, startColor, x2, y2);
        model.addMotion(name, motionToAdd);
      } else if ((w1 != w2 || h1 != h2) && x1 == x2 && y1 == y2 && startColor.equals(endColor)) {
        IMotion motionToAdd = new ScaleMotion(t1, t2, x1, y1, w1, h1, startColor, w2, h2);
        model.addMotion(name, motionToAdd);
      } else if (!startColor.equals(endColor) && x1 == x2 && y1 == y2 && w1 == w2 && h1 == h2) {
        IMotion motionToAdd = new ChangeColorMotion(t1, t2, x1, y1, w1, h1, startColor, endColor);
        model.addMotion(name, motionToAdd);
      } else {
        IMotion motionToAdd = new Motion(t1, t2, x1, y1, w1, h1, startColor, x2, y2, w2, h2,
            endColor);
        model.addMotion(name, motionToAdd);
      }

      return this;
    }

  }


  @Override
  public void addShape(String shapeId, String type) {
    if (shapeId == null || type == null || shapeId.equals("") || type.equals("")) {
      throw new IllegalArgumentException("Invalid inputs name or type.");
    }

    ShapeFactory shapeFactory = new ShapeFactory();
    IShape newShape = shapeFactory.createShape(shapeId, type);

    if (newShape == null) {
      throw new IllegalArgumentException("Please enter a valid shape type.");
    }

    boolean validShapeId = true;
    for (String str : currentShapes.keySet()) {
      if (str.equals(shapeId)) {
        validShapeId = false;
        break;
      }
    }

    if (!validShapeId) {
      throw new IllegalArgumentException("The shape id is already existed.");
    }

    currentShapes.putIfAbsent(shapeId, newShape);

  }

  /**
   * Abstract method to check if the shapeId is not exist in the model.
   */
  private void findShapeId(String shapeId) {
    boolean validShapeId = false;
    for (String str : currentShapes.keySet()) {
      if (str.equals(shapeId)) {
        validShapeId = true;
        break;
      }
    }

    if (!validShapeId) {
      throw new IllegalArgumentException("Couldn't find a shape in the model.");
    }

  }

  @Override
  public void removeShape(String shapeId) {
    if (shapeId == null || shapeId.equals("")) {
      throw new IllegalArgumentException("Invalid shapeId.");
    }

    if (currentShapes.isEmpty()) {
      throw new IllegalStateException("Model has no shape to be removed.");
    }

    findShapeId(shapeId);

    currentShapes.remove(shapeId);

  }

  @Override
  public IShape shapeShowOnTop(IShape shape1, IShape shape2) {
    if (shape1 == null || shape2 == null) {
      throw new IllegalArgumentException("Invalid shape inputs.");
    }

    if (currentShapes.isEmpty() || currentShapes.size() < 2) {
      throw new IllegalStateException("There are less than 2 shapes in the model.");
    }

    if (!currentShapes.containsKey(shape1.getName()) || !currentShapes
        .containsKey(shape2.getName())) {
      throw new IllegalArgumentException("Couldn't find the shape in the model.");
    }

    IShape shapeOnTop = null;
    for (String str : currentShapes.keySet()) {

      if (currentShapes.get(str).equals(shape1)) {
        shapeOnTop = shape1;
        break;
      }

      if (currentShapes.get(str).equals(shape2)) {
        shapeOnTop = shape2;
        break;
      }

    }

    return shapeOnTop;

  }

  @Override
  public void addMotion(String shapeId, IMotion motion) {
    if (shapeId == null || motion == null) {
      throw new IllegalArgumentException("Shape id or motion can't be null.");
    }

    if (shapeId.equals("")) {
      throw new IllegalArgumentException("Shape id can't be an empty string.");
    }

    findShapeId(shapeId);

    try {
      currentShapes.get(shapeId).addMotion(motion);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException("Motion is already existed or conflict with other motions.");
    }

  }

  @Override
  public void removeMotion(String shapeId, IMotion motion) {
    if (shapeId == null || motion == null) {
      throw new IllegalArgumentException("Invalid inputs. Motion and shape id can't be null.");
    }

    if (shapeId.equals("")) {
      throw new IllegalArgumentException("Shape id can't be an empty string.");
    }

    if (currentShapes.isEmpty()) {
      throw new IllegalStateException("The model has no shape to remove.");
    }

    findShapeId(shapeId);

    try {
      currentShapes.get(shapeId).removeMotion(motion);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Motion can't be found in shape.");
    }

  }

  @Override
  public Map<String, IShape> getShapes() {
    if (this.currentShapes.isEmpty()) {
      throw new IllegalStateException("The model doesn't have any shape.");
    }

    Map<String, IShape> shapes = new HashMap<>(this.currentShapes);
    return shapes;
  }

  @Override
  public List<IReadOnlyShape> getShapesAtGivenTick(int tick) {
    if (tick < 0) {
      throw new IllegalArgumentException("Tick can't be negative.");
    }

    if (currentShapes.isEmpty()) {
      throw new IllegalStateException("The model has no shape to get.");
    }

    Set<IReadOnlyShape> shapesToRender = new HashSet<>();

    for (String str : currentShapes.keySet()) {

      List<IMotion> motionsOfShape = new ArrayList<>(this.getMotions(str));

      for (IMotion motion : motionsOfShape) {
        IReadOnlyShape transformedShape = motion.apply(tick, currentShapes.get(str).getType());
        if (transformedShape != null) {
          shapesToRender.add(transformedShape);
        }

      }

    }

    return new ArrayList<IReadOnlyShape>(shapesToRender);

  }

  @Override
  public List<IReadOnlyShape> getShapesAtDiscreteTime(int tick) {
    if (tick < 0) {
      throw new IllegalArgumentException("Tick can't be negative.");
    }

    if (currentShapes.isEmpty()) {
      throw new IllegalStateException("The model has no shape to get.");
    }

    Set<IReadOnlyShape> shapesToRender = new HashSet<>();

    for (String str : currentShapes.keySet()) {

      List<IMotion> motionsOfShape = new ArrayList<>(this.getMotions(str));

      for (IMotion motion : motionsOfShape) {
        IReadOnlyShape transformedShape = motion
            .applyDiscreteTime(tick, currentShapes.get(str).getType());
        if (transformedShape != null) {
          shapesToRender.add(transformedShape);
        }

      }

    }

    return new ArrayList<IReadOnlyShape>(shapesToRender);
  }

  @Override
  public List<IMotion> getMotions(String shapeId) {
    if (shapeId == null) {
      throw new IllegalArgumentException("Invalid inputs. Shape id can't be null.");
    }

    if (shapeId.equals("")) {
      throw new IllegalArgumentException("Shape id can't be an empty string.");
    }

    if (currentShapes.isEmpty()) {
      throw new IllegalStateException("The model has no shape to get motion.");
    }

    findShapeId(shapeId);

    IShape shape = new AbstractShape(shapeId);
    for (String str : currentShapes.keySet()) {
      if (str.equals(shapeId)) {
        shape = currentShapes.get(str);
        break;
      }
    }
    return new ArrayList<>(shape.getMotions());
  }

  @Override
  public List<List<IMotion>> getAllMotions() {
    if (currentShapes.isEmpty()) {
      throw new IllegalStateException("The model has no shape to get motion.");
    }

    List<List<IMotion>> allMotions = new ArrayList<>();

    for (String str : currentShapes.keySet()) {
      List<IMotion> thisShapeMotions = new ArrayList<>(currentShapes.get(str).getMotions());
      allMotions.add(thisShapeMotions);
    }

    return allMotions;
  }

  @Override
  public String renderInformation() {
    if (currentShapes.isEmpty()) {
      return "There is no shape in the model.";
    }

    String render = "";

    for (String str : currentShapes.keySet()) {
      render += currentShapes.get(str).renderMotionsInfo() + "\n";
    }

    return render;

  }

  @Override
  public ICanvas getCanvas() {
    ICanvas canvas = this.canvas;
    return canvas;
  }

}
