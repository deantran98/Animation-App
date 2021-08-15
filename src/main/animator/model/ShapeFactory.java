package cs3500.animator.model;

/**
 * The class represents a factory pattern to get shape.
 */
public class ShapeFactory {

  /**
   * create an object of shape type.
   **/
  public IShape createShape(String name, String shapeType) {
    if (shapeType == null) {
      return null;
    } else if (shapeType.equalsIgnoreCase("CIRCLE") ||
        shapeType.equalsIgnoreCase("ELLIPSE") || shapeType.equalsIgnoreCase("OVAL")) {
      return new Circle(name);

    } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
      return new Rectangle(name);

    } else {
      return null;
    }

  }

}
