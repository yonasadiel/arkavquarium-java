package com.arkavquarium.models;

public class Coin implements Drawable {
  private static final String assetPathCoin = "src/main/resources/img/coin_shine.png";
  private static final String assetPathRuby = "src/main/resources/img/ruby.png";
  private static final int MAX_VELOCITY = 40;
  private Position position;
  private int value;

  /**
   * Constuctor, initiate position and value.
   * @param value coin value
   * @param position coin position
   */
  public Coin(int value, Position position) {
    this.position = new Position(position.getAbsis(), position.getOrdinate());
    this.value = value;
  }

  /**
   * Decrease ordinate with velocity less than maxVelocity.
   * @param elapsedSeconds elapsed Seconds
   */
  public void move(double elapsedSeconds) {
    Position bottom = new Position(0, Data.getMaxHeight() - (Data.getMaxHeight() / 10));
    this.getPosition().moveVertical(bottom, elapsedSeconds * Coin.MAX_VELOCITY);
  }

  /**
   * Get Position.
   * @return the coin's position
   */
  public Position getPosition() {
    return this.position;
  }
  
  /**
   * Get Value.
   * @return the coin's value
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Get asset path according to coin value.
   * @return the coin's asset path 
   */
  public String getAssetPath() {
    if (this.getValue() < 50) {
      return Coin.assetPathCoin;
    } else {
      return Coin.assetPathRuby;
    }
  }

  public static String getCoinAssetPath() {
    return Coin.assetPathCoin;
  }
}
