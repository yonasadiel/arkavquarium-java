package com.arkavquarium.models;

public class Food implements Drawable {
  private static final String assetPath = "src/main/resources/img/food.png";
  private static final int PRICE = 10;
  /**
  * Max velocity of the food movement.
  */
  private static int MAX_VELOCITY = 30;
  private Position position;

  /**
   * Construct Food at destined position.
   * @param position position of food
   */
  public Food(Position position) {
    this.position = new Position(position.getAbsis(), position.getOrdinate());
  }

  /**
   * Move Food to bottom.
   * @param y minimum height
   * @param elapsedSeconds elapsed seconds
   */
  public void move(int y, double elapsedSeconds) {
    Position bottom = new Position(0, y);
    this.position.moveVertical(bottom, elapsedSeconds * MAX_VELOCITY);
  }

  /**
   * Get food position.
   * @return food position 
   */
  public Position getPosition() {
    return this.position;
  }

  /**
   * Get asset path.
   * @return asset path
   */
  public String getAssetPath() {
    return assetPath;
  }

  /**
   * Get food price.
   * @return food price
   */
  public static final int getPrice() {
    return PRICE;
  }
}