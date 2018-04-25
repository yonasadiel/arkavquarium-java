package com.arkavquarium.tests;

import static org.junit.Assert.assertEquals;

import com.arkavquarium.models.Coin;
import com.arkavquarium.models.Data;
import com.arkavquarium.models.Position;
import org.junit.Test;

public class CoinTest {

  @Test
  public void testConstructorValue() {
    Position p = new Position(10, 20);
    Coin c = new Coin(25, p);
    assertEquals(
        "constructor set value from first parameter",
        25, c.getValue()
    );
  }

  @Test
  public void testConstructorPosition() {
    Position p = new Position(10, 20);
    Coin c = new Coin(25, p);
    assertEquals(
        "constructor set position froms second parameter",
        10, c.getPosition().getAbsis(),
        Position.getTolerance()
    );
    assertEquals(
        "constructor set position froms second parameter",
        20, c.getPosition().getOrdinate(),
        Position.getTolerance()
    );
  }

  @Test
  public void testConstructorPositionCopy() {
    Position p = new Position(10, 20);
    Coin c = new Coin(25, p);
    p.setAbsis(30);
    p.setOrdinate(30);
    assertEquals(
        "constructor set position froms second parameter by value not reference",
        10, c.getPosition().getAbsis(),
        Position.getTolerance()
    );
    assertEquals(
        "constructor set position froms second parameter by value not reference",
        20, c.getPosition().getOrdinate(),
        Position.getTolerance()
    );
  }

  @Test
  public void testMoveLessThanRange() {
    Position p = new Position(10, 20);
    Coin c = new Coin(25, p);
    Data.setMaxHeight(500);
    c.move(1);
    assertEquals(
        "move not change Coin absis",
        10, c.getPosition().getAbsis(),
        Position.getTolerance()
    );
    assertEquals(
        "move change Coin ordinate less than max velocity",
        60, c.getPosition().getOrdinate(),
        Position.getTolerance()
    );
  }

  @Test
  public void testMoveMoreThanRange() {
    Position p = new Position(10, 20);
    Coin c = new Coin(25, p);
    Data.setMaxHeight(50);
    c.move(1);
    assertEquals(
        "move not change Coin absis",
        10, c.getPosition().getAbsis(),
        Position.getTolerance()
    );
    assertEquals(
        "move change Coin ordinate less than max velocity",
        45, c.getPosition().getOrdinate(),
        Position.getTolerance()
    );
  }

  @Test
  public void testAssetLessThanBound() {
    Position p = new Position(10, 20);
    Coin c = new Coin(49, p);
    assertEquals(
        "getAssetPath return coin if less than 50",
        "src/main/resources/img/coin_shine.png", c.getAssetPath()
    );
  }

  @Test
  public void testAssetSameAsBound() {
    Position p = new Position(10, 20);
    Coin c = new Coin(50, p);
    assertEquals(
        "getAssetPath return coin if same as 50",
        "src/main/resources/img/ruby.png", c.getAssetPath()
    );
  }

  @Test
  public void testAssetMoreThanBound() {
    Position p = new Position(10, 20);
    Coin c = new Coin(51, p);
    assertEquals(
        "getAssetPath return coin if more than 50",
        "src/main/resources/img/ruby.png", c.getAssetPath()
    );
  }
}