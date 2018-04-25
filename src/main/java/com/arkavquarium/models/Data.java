package com.arkavquarium.models;

public class Data {
  private static LinkedList<Guppy> guppies = new LinkedList<Guppy>();
  private static LinkedList<Piranha> piranhas = new LinkedList<Piranha>();
  private static LinkedList<Food> foods = new LinkedList<Food>();
  private static LinkedList<Coin> coins = new LinkedList<Coin>();
  private static Snail snail = null;
  private static int money = 200;
  private static int egg = 0;
  private static int eggPrice = 1000;
  private static int maxHeight = 768;
  private static int maxWidth = 1366;

  /**
   * Do nothing.
   */
  public Data() {}

  /**
   * Get linkedlist of guppies.
   * @return LinkedList of guppies
   */
  public static LinkedList<Guppy> getGuppies() {
    return Data.guppies;
  }
  
  /**
   * Get linkedlist of piranhas.
   * @return LinkedList of piranhas
   */
  public static LinkedList<Piranha> getPiranhas() {
    return Data.piranhas;
  }

  /**
   * Get linkedlist of foods.
   * @return LinkedList of foods
   */
  public static LinkedList<Food> getFoods() {
    return Data.foods;
  }

  /**
   * Get linkedlist of coins.
   * @return LinkedList of coins
   */
  public static LinkedList<Coin> getCoins() {
    return Data.coins;
  }

  /**
   * Get snail.
   * @return Snail
   */
  public static Snail getSnail() {
    return Data.snail;
  }
  
  /**
   * Get number of egg.
   * @return current number of egg
   */
  public static int getEgg() {
    return Data.egg;
  }
  
  /**
   * Get price of egg.
   * @return cuurent egg price
   */
  public static int getEggPrice() {
    return Data.eggPrice;
  }

  /**
   * Get current money.
   * @return current amount of money
   */
  public static int getMoney() {
    return Data.money;
  }

  /**
   * Set new egg price.
   * @param eggPrice new egg price
   */
  public static void setEggPrice(int eggPrice) {
    Data.eggPrice = eggPrice;
  }

  /**
   * Set new amount of money.
   * @param money new amount of money
   */
  public static void setMoney(int money) {
    Data.money = money;
  }

  /**
   * Set new amount of egg.
   * @param egg new amount of egg
   */
  public static void setEgg(int egg) {
    Data.egg = egg;
  }

  /**
   * Set snail to new Snail.
   * @param snail new snail
   */
  public static void setSnail(Snail snail) {
    Data.snail = snail;
  }

  /**
   * Destruct all data and reinitiate.
   */
  public static void clear() {
    guppies = new LinkedList<Guppy>();
    piranhas = new LinkedList<Piranha>();
    foods = new LinkedList<Food>();
    coins = new LinkedList<Coin>();
    snail = new Snail();
  }
  
  /**
   * Get max height of program.
   * @return Max height of the screen
   */
  public static int getMaxHeight() {
    return Data.maxHeight;
  }
  
  /**
   * Get max width of program.
   * @return Max width of the screen
   */
  public static int getMaxWidth() {
    return Data.maxWidth;
  }
  
  /**
   * Set new maxHeight.
   * @param maxHeight new maxHeigt
   */
  public static void setMaxHeight(int maxHeight) {
    Data.maxHeight = maxHeight;
  }
  
  /**
   * Set new maxWidth.
   * @param maxWidth new maxWidth
   */
  public static void setMaxWidth(int maxWidth) {
    Data.maxWidth = maxWidth;
  }
}
