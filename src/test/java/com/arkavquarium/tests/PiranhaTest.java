package com.arkavquarium.tests;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.arkavquarium.models.Position;
import com.arkavquarium.models.Data;
import com.arkavquarium.models.Piranha;


public class PiranhaTest {
    @Before
    public void prepareMaxWidthHeight() {
        Data.setMaxWidth(10000);
        Data.setMaxHeight(10000);
    }

    @Test
    public void testPiranhaConstructor () {
        Piranha p = new Piranha();
        assertEquals(0, p.getStarvingTimer(), 0.001);
        assertEquals(0, p.getEatCounter(), 0.001);
        
    }

    @Test
    public void testPiranhaLeftAsset () {
        Piranha p = new Piranha();
        Position pos = new Position(p.getPosition().getAbsis() - 100, p.getPosition().getOrdinate());
        p.moveToDestination(pos, 0.1);
        assertEquals("asset should return left if orientation is left", "src/main/resources/img/piranha_left_big.png", p.getAssetPath());
    }

    @Test
    public void testPiranhaRightAsset() {
        Piranha p = new Piranha();
        Position pos = new Position(p.getPosition().getAbsis() + 100, p.getPosition().getOrdinate());
        p.moveToDestination(pos, 0.1);
        assertEquals("asset should return right if orientation is right", "src/main/resources/img/piranha_right_big.png", p.getAssetPath());
    }
    
    @Test
    public void testStarvingAsset() {
        Piranha p = new Piranha();
        p.setStarvingTimer(20);
        Position pos = new Position(p.getPosition().getAbsis() - 100, p.getPosition().getOrdinate());
        p.moveToDestination(pos, 0.1);
        assertEquals("asset should show starving asset when starving",
                "src/main/resources/img/piranha_left_big_hungry.png", p.getAssetPath());
    }

    @Test
    public void testDeath() {
        Piranha p = new Piranha();
        p.setStarvingTimer(25);
        assertTrue("Piranha should be dead", p.isDie());
    }
}