package com.arkavquarium.models;

import java.lang.Math;

public class Position {
  /**
   * Absis.
   */
  private double absis;
  /**
   * Ordinate.
   */
  private double ordinate;
  private static final double tolerance = 15;
  
  /**
   * Constructor initiate the position with random coordinate.
   */
  public Position() {
    this.random();
  }

  /**
   * Constructor, initiate the position with absis and ordinate.
   * @param absis is the absis
   * @param ordinate is the coordinate
   */
  public Position(double absis, double ordinate) {
    this.absis = absis;
    this.ordinate = ordinate;
  }

  /**
   * Get absis of the position.
   * @return the absis of the positon
   */
  public double getAbsis() {
    return this.absis;
  }
  
  /**
   * Get ordinate of the position.
   * @return the ordinate of the position
   */
  public double getOrdinate() {
    return this.ordinate;
  }
  
  /**
   * Set the absis of the position.
   * @param absis new absis of the position
   */
  public void setAbsis(double absis) {
    this.absis = absis;
  }

  /** 
   * Set the ordinate of the position.
   * @param ordinate new ordinate of the positon
   */
  public void setOrdinate(double ordinate) {
    this.ordinate = ordinate;
  }

  public static double getTolerance() {
    return Position.tolerance;
  }

  /**
   * Set the position to random value with within maximal height and maximal width.
   */
  public void random() {
    int pad = Data.getMaxWidth() * 5 / 100;
    this.absis = Math.random() * (Data.getMaxWidth() - 2 * pad) + pad;
    this.ordinate = Math.random() * Data.getMaxHeight();
  }

  /**
   * Set absis and ordinate to the nearest coordinate to destination
   * but no more than pythagoran distance maxVelocity.
   * @param dest destination
   * @param maxVelocity pythagorean distace
   */
  public void move(Position dest, double maxVelocity) {
    double distanceAbsis = dest.getAbsis() - this.getAbsis();
    double distanceOrdinate = dest.getOrdinate() - this.getOrdinate();
    double distance = Math.sqrt(Math.pow(distanceAbsis,2) + Math.pow(distanceOrdinate,2));
    double deltaAbsis = (Math.min(maxVelocity, distance) / distance) * distanceAbsis;
    double deltaOrdinate = (Math.min(maxVelocity, distance) / distance) * distanceOrdinate;
    this.absis += deltaAbsis;
    this.ordinate += deltaOrdinate;
  }

  /**
   * Set absis to the nearest absis to destination
   * but no more than pythagoran distance maxVelocity.
   * @param dest destination
   * @param maxVelocity pythagorean distace
   */
  public void moveHorizontal(Position dest,double maxVelocity) {
    double deltaAbsis;
    deltaAbsis = dest.getAbsis() - this.absis;
    if (deltaAbsis < 0) {
      this.absis += Math.max(-maxVelocity, deltaAbsis);
    } else {
      this.absis += Math.min(maxVelocity, deltaAbsis);
    }
  }

  /**
   * Set ordinate to the nearest coordinate to destination
   * but no more than pythagoran distance maxVelocity.
   * @param dest destination
   * @param maxVelocity pythagorean distace
   */
  public void moveVertical(Position dest, double maxVelocity) {
    double deltaOrdinate = dest.getOrdinate() - this.ordinate;
    if (deltaOrdinate < 0) {
      this.ordinate += Math.max(-maxVelocity, deltaOrdinate);
    } else {
      this.ordinate += Math.min(maxVelocity, deltaOrdinate);
    }
  }
  
  /**
   * Compare two position is equal or not.
   * @param other comp the position to compare with
   * @return true if both positions have same coordinate with tolerance defined.
   */
  public boolean equals(Position other) {
    return this.magnitude(other) <= Position.tolerance;
  }

  /**
   * Get pythagorean distance between two Position.
   * @param comp the end position
   * @return the magnitude between to position comp
   */
  public double magnitude(Position comp) {
    double distanceAbsis = comp.getAbsis() - this.getAbsis();
    double distanceOrdinate = comp.getOrdinate() - this.getOrdinate();
    double distance = Math.sqrt(Math.pow(distanceAbsis, 2) + Math.pow(distanceOrdinate, 2));
    return distance;
  }
  
  
  /**
   * Return true if this position is
   * on top and right side of other position.
   * @param temp any position
   * @return true if this position greater than temp
   */
  public boolean isGreater(Position temp) {
    return this.getAbsis() > temp.getAbsis() && this.getOrdinate() > temp.getOrdinate();
  }
}
