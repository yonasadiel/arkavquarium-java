package com.arkavquarium.models;

public class Piranha extends Fish {
  private static final int PRICE = 1000;
  private static final int MAX_VELOCITY = 50;
  private static final String assetPath = "src/main/resources/img/piranha";
  /**
   * x+1 if Piranha just eat guppy with growth step x.
   * Piranha has just eat a guppy.
   * Otherwise, return 0.
   */
  private int isJustEatGuppy;

  /**
   * Construct Piranha with isJustEatGuppy as 0.
   * Set max velocity to piranha max velocity.
   */
  public Piranha() {
    super();
    this.isJustEatGuppy = 0;
    this.maxVelocity = MAX_VELOCITY; 
  }

  /**
   * Set isJustEatGuppy to 0.
   * @return isJustEatGuppy
   */
  public int isProduceCoin() {
    int temp = this.isJustEatGuppy;
    this.isJustEatGuppy = 0;
    return temp;
  }

  /**
   * Set isJustEatGuppy to True.
   * @param x growth step of fish eaten
   */
  public void eat(int x) {
    super.eat();
    this.isJustEatGuppy = x + 1 ;
  }

  /**
   * Get path to asset according to orientation (left or right)
   * and status (replete or starving).
   * @return asset path, depends on orientation and status
   */
  public String getAssetPath() {
    String path = assetPath;

    if (this.getOrientation() == Orientation.LEFT) {
      path += "_left_big";
    } else {
      path += "_right_big";
    }

    if (this.isStarving()) {
      path += "_hungry";
    }

    path += ".png";
    return path;
  }

  public static int getPrice() {
    return PRICE;
  }

}