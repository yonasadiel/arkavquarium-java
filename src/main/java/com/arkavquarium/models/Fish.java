package com.arkavquarium.models;

public abstract class Fish implements Drawable {
  protected static final int MAX_VELOCITY = 35;
  /**
   * Time from Fish eat until starving.
   */
  protected static final int REPLETE_TIME = 12;
  /**
   * Time from Fish starving until die.
   */
  protected static final int STARVATION_TIME = 24;
  protected static final int FIRST_GROWTH_EAT_COUNTER = 7;
  protected static final int SECOND_GROWTH_EAT_COUNTER = 15;
  protected Status status;
  protected double maxVelocity;
  protected Position position;
  /**
   * Destination of Fish when Fish is idle.
   */
  protected Position destination;
  protected Orientation orientation;
  protected double starvingTimer;
  protected int eatCounter;

  /**
   * Construct Fish.
   * Set status to IDLE.
   * Set position to random new position.
   * Set destination to random new position.
   * Set growthStep to STEP_ONE.
   * Set starvingTimer to zero.
   * Set orientation according to destination.
   * Set eatCounter to zero.
   */
  public Fish() {
    this.status = Status.IDLE;
    this.position = new Position();
    this.destination = new Position();
    this.starvingTimer = 0;
    this.orientation =
      (this.destination.getAbsis() > this.position.getAbsis())
        ? Orientation.RIGHT : Orientation.LEFT;
    this.eatCounter = 0;
  }

  /**
   * Get fish status.
   * @return fish status
   */
  public final Status getStatus() {
    return this.status;
  }

  /**
   * Get fish position.
   * @return fish position
   */
  public final Position getPosition() {
    return this.position;
  }

  /**
   * Get fish destination.
   * @return fish status
   */
  public final Position getDestination() {
    return this.destination;
  }

  /**
   * Get fish starving timer.
   * @return fish starving timer
   */
  public final double getStarvingTimer() {
    return this.starvingTimer;
  }

  /**
   * Get fish orientation.
   * @return fish orientation
   */
  public final Orientation getOrientation() {
    return this.orientation;
  }

  /**
   * Get fish eat counter.
   * @return fish eat counter
   */
  public final int getEatCounter() {
    return this.eatCounter;
  }

  /**
   * Set status of the fish.
   * @param status new status
   */
  public void setStatus(Status status) {
    this.status = status;
  }

  /**
   * Set position of the fish.
   * @param position new position
   */
  public void setPosition(Position position) {
    this.position = position;
  }

  /**
   * Set destination of the fish.
   * @param destination new destination
   */
  public void setDestination(Position destination) {
    this.destination = destination;
  }

  /**
   * Set starving timer of the fish.
   * @param starvingTimer new starving timer
   */
  public void setStarvingTimer(double starvingTimer) {
    this.starvingTimer = starvingTimer;
  }

  /**
   * Set orientation of the fish.
   * @param orientation new orientation
   */
  public void setOrientation(Orientation orientation) {
    this.orientation = orientation ;
  }

  public abstract int isProduceCoin();

  /**
   * Return true if starving timer is less than or equal starving time,
   * but more than or equal replete time. 
   * @return REPLETE TIME <= starvingTimer <= STARVING TIME
   */
  public boolean isStarving() {
    return (REPLETE_TIME <= this.starvingTimer) && (this.starvingTimer <= STARVATION_TIME);
  }

  /**
   * Return true if starving timer greater than starving time.
   * @return starvingTimer > STARVING TIME
   */
  public boolean isDie() {
    return this.getStarvingTimer() > STARVATION_TIME;
  }

  /**
   * Increment eatCounter.
   * If exceed minimum eatCounter, upgrade growthStep to next step.
   */
  public void eat() {
    this.eatCounter++;
    this.starvingTimer = 0;
  }

  /**
   * Move Fish to the dest.
   * @param position is the nearest Food for Guppy
   *                 or nearest Guppy for Piranha
   * @param elapsedSeconds elapsedSecond for max velocity calc
   */
  public void moveToDestination(Position position, double elapsedSeconds) {
    this.position.move(position, elapsedSeconds * this.maxVelocity); 
    if (this.position.getAbsis() < position.getAbsis()) {
      this.orientation = Orientation.RIGHT;
    } else {
      this.orientation = Orientation.LEFT;
    }
  }

  /**
   * Move Fish to their default destination.
   * If Fish Position equal to default destination.
   * random new destination.
   * @param elapsedSeconds elapsed seconds since last invocation
   */
  public void moveToDestination(double elapsedSeconds) {
    if (this.position.equals(this.destination)) {
      this.destination = new Position();
    }
    this.position.move(this.destination, elapsedSeconds * this.maxVelocity);
    if (this.position.getAbsis() < this.destination.getAbsis()) {
      this.orientation = Orientation.RIGHT;
    } else {
      this.orientation = Orientation.LEFT;
    }
  }
}