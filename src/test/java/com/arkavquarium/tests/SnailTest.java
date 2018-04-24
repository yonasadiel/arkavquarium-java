package com.arkavquarium.tests;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import com.arkavquarium.models.Position;
import com.arkavquarium.models.Snail;
import com.arkavquarium.models.Data;

public class SnailTest {
    private double tolerance = 3;

    @Before
    public void prepareMaxWidthHeight() {
        Data.setMaxWidth(10000);
        Data.setMaxHeight(10000);
    }

    @Test
    public void testPositionConstructor() {
        Snail s = new Snail();
        assertEquals("ordinate constructed equals to 9/10 max height", 9000, s.getPosition().getOrdinate(), this.tolerance);
        Data.setMaxHeight(1000);
        s = new Snail();
        assertEquals("ordinate constructed equals to 9/10 max height", 900, s.getPosition().getOrdinate(), this.tolerance);
    }

    @Test
    public void testSnailLeftAsset() {
        Snail s = new Snail();
        Position p = new Position(s.getPosition().getAbsis() - 100, s.getPosition().getOrdinate());
        s.moveToDestination(p, 0.1);
        assertEquals("asset should return left if orientation is left", "src/main/resources/img/snail_left.png", s.getAssetPath());
    }

    @Test
    public void testSnailRightAsset() {
        Snail s = new Snail();
        Position p = new Position(s.getPosition().getAbsis() + 100, s.getPosition().getOrdinate());
        s.moveToDestination(p, 0.1);
        assertEquals("asset should return right if orientation is right", "src/main/resources/img/snail_right.png", s.getAssetPath());
    }
}