package com.arkavquarium.models;

public class Guppy extends Fish {    
  private static final int MAX_VELOCITY = 50;
  /**
   * Period time for Guppy producing coin.
   */
  private static final double PRODUCE_COIN_PERIOD = 8 ;
  private static final int COIN_VALUE_STEP_ONE = 10;
  private static final int COIN_VALUE_STEP_TWO = 20;
  private static final int COIN_VALUE_STEP_THREE = 40;
  private static final int PRICE = 100;
  private static final String assetPath = "src/main/resources/img/guppy";
  /**
   * Timer for producing coin.
   * Updated every update method is called.
   */
  private double produceCoinTimer;
  private GrowthStep growthStep;

  /**
   * Construct Guppy, by calling parent constructor.
   * Set produceCoinTimer to 0.
   * Set growth step to step one.
   * Set velocity to guppy max velocity.
   */
  public Guppy() {
    super();
    this.produceCoinTimer = 0;
    this.growthStep = GrowthStep.STEP_ONE;
    this.maxVelocity = MAX_VELOCITY;
  }
    
  /**
   * Get growth step of the guppy.
   * @return growthStep
   */
  public GrowthStep getGrowthStep() {
    return this.growthStep;
  }
    
  /**
   * Get growth step of the guppy as integer.
   * Return 1 if step one.
   * Return 2 if step two.
   * Return 3 if step three.
   * @return growthStep in int
   */
  public int getGrowthStepInt() {
    if (this.growthStep == GrowthStep.STEP_ONE) {
      return 1;
    } else if (this.growthStep == GrowthStep.STEP_TWO) {
      return 2;
    } else {
      return 3;
    }
  }
    
  /**
   * Set growth step to growth step.
   * @param growthStep new growthStep
   */
  public void setGrowthStep(GrowthStep growthStep) {
    this.growthStep = growthStep;
  }
    
  /**
   * Reduce produceCoinTimer with PRODUCE_COIN_PERIOD.
   * @return 0 if PRODUCE_COIN_PERIOD == produceCoinTimer before edited
   *         this growthStep value if not
   */
  public int isProduceCoin() {
    if (this.produceCoinTimer < PRODUCE_COIN_PERIOD) {
      return 0;
    } else {
      this.produceCoinTimer -= PRODUCE_COIN_PERIOD;
      if (this.getGrowthStep() == GrowthStep.STEP_ONE) {
        return COIN_VALUE_STEP_ONE;
      } else if (this.getGrowthStep() == GrowthStep.STEP_TWO) {
        return COIN_VALUE_STEP_TWO;
      } else {
        return COIN_VALUE_STEP_THREE;
      }
    }
  }
    
  /**
   * Increment produceCoinTimer by elapsedSeconds.
   * @param elapsedSeconds elapsed seconds since previous loop
   */
  public void update(double elapsedSeconds) {
    this.produceCoinTimer += elapsedSeconds;
  }

  /**
   * Override parent eat(). After parent eat(),
   * if exceed minimum eatCounter, upgrade growthStep to next step.
   */
  public void eat() {
    super.eat();
    if (this.eatCounter == FIRST_GROWTH_EAT_COUNTER) {
      this.growthStep = GrowthStep.STEP_TWO;
    } else if (this.eatCounter == SECOND_GROWTH_EAT_COUNTER) {
      this.growthStep = GrowthStep.STEP_THREE;
    }
  }

  /**
   * Return asset path according to orientation (left or right),
   * growth step (big or small), and status (starving or replete).
   * @return asset path, depends on this orientation, growth step, and status
   */
  public String getAssetPath() {
    String path = assetPath;

    if (this.getOrientation() == Orientation.LEFT) {
      path += "_left";
    } else {
      path += "_right";
    }

    if (this.getGrowthStep() == GrowthStep.STEP_ONE) {
      path += "_small";
    } else if (this.getGrowthStep() == GrowthStep.STEP_TWO) {
      path += "_medium";
    } else {
      path += "_big";
    }

    if (this.isStarving()) {
      path += "_hungry";
    }

    path += ".png";
    return path;
  }

  /**
   * Return price for guppy.
   * @return guppy price
   */
  public static int getPrice() {
    return Guppy.PRICE;
  }
}