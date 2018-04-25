package com.arkavquarium.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.arkavquarium.models.Coin;
import com.arkavquarium.models.Data;
import com.arkavquarium.models.Food;
import com.arkavquarium.models.Guppy;
import com.arkavquarium.models.Piranha;
import com.arkavquarium.models.Position;
import org.junit.Before;
import org.junit.Test;

public class DataTest {
  @Before
  public void resetData() {
    Data.clear();
  }

  @Test
  public void testGuppiesData() {
    assertTrue(
        "guppies data should be empty first",
        Data.getGuppies().isEmpty()
    );
    Data.getGuppies().add(new Guppy());
    assertFalse(
        "add data to guppies",
        Data.getGuppies().isEmpty()
    );
  }

  @Test
  public void testPiranhasData() {
    assertTrue(
        "piranhas data should be empty first",
        Data.getPiranhas().isEmpty()
    );
    Data.getPiranhas().add(new Piranha());
    assertFalse(
        "add data to piranhas",
        Data.getPiranhas().isEmpty()
    );
  }

  @Test
  public void testFoodsData() {
    assertTrue(
        "foods data should be empty first",
        Data.getFoods().isEmpty()
    );
    Data.getFoods().add(new Food(new Position(10, 30)));
    assertFalse(
        "add data to foods",
        Data.getFoods().isEmpty()
    );
  }

  @Test
  public void testCoinsData() {
    assertTrue(
        "coins data should be empty first",
        Data.getCoins().isEmpty()
    );
    Data.getCoins().add(new Coin(50, new Position(20, 30)));
    assertFalse(
        "add data to coins",
        Data.getCoins().isEmpty()
    );
  }
}