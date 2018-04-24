package com.arkavquarium.tests;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import com.arkavquarium.models.Position;
import com.arkavquarium.models.Data;

public class PositionTest {
    private double tolerance = 10;

    @Before
    public void prepareMaxWidthHeight() {
        Data.setMaxWidth(10000);
        Data.setMaxHeight(10000);
    }

    @Test
    public void testAbsisContructor() {
        Position p = new Position(2.5, 3.5);
        assertEquals("absis contructed equals to passed argument", 2.5, p.getAbsis(), this.tolerance);
    }

    @Test
    public void testOrdinateContructor() {
        Position p = new Position(2.5, 3.5);
        assertEquals("absis contructed equals to passed argument", 3.5, p.getOrdinate(), this.tolerance);
    }

    @Test
    public void testRandomAbsisContructor() {
        Data.setMaxWidth(10);
        Position p = new Position();
        assertTrue("absis constructed randomized less than width", p.getAbsis() <= 10);
    }

    @Test
    public void testRandomOrdinateContructor() {
        Data.setMaxHeight(10);
        Position p = new Position();
        assertTrue("ordinate constructed randomized less than height", p.getOrdinate() <= 10);
    }

    @Test
    public void testAbsisSetter() {
        Position p = new Position();
        p.setAbsis(5.5);
        assertEquals("absis set to passed argument", 5.5, p.getAbsis(), this.tolerance);
    }

    @Test
    public void testOrdinateSetter() {
        Position p = new Position();
        p.setOrdinate(5.5);
        assertEquals("ordinate set to passed argument", 5.5, p.getOrdinate(), this.tolerance);
    }

    @Test
    public void testMoveLessThanRange() {
        Position source = new Position(100, 100);
        Position dest = new Position(40, 180);
        source.move(dest, 100);
        assertEquals("absis moved to destination less than max velocity", 40.0, source.getAbsis(), this.tolerance);
        assertEquals("ordinate moved to destination less than max velocity", 180.0, source.getOrdinate(), this.tolerance);
    }

    @Test
    public void testMoveMoreThanRange() {
        Position source = new Position(100, 100);
        Position dest = new Position(160, 20);
        source.move(dest, 50);
        assertEquals("absis moved to destination more than max velocity", 130.0, source.getAbsis(), this.tolerance);
        assertEquals("ordinate moved to destination more than max velocity", 60.0, source.getOrdinate(), this.tolerance);
    }

    @Test
    public void testMoveHorizontallyLessThanRange() {
        Position source = new Position(100, 100);
        Position dest = new Position(40, 180);
        source.moveHorizontal(dest, 100);
        assertEquals("absis moved horizontally to destination less than max velocity", 40.0, source.getAbsis(), this.tolerance);
        assertEquals("ordinate moved horizontally to destination less than max velocity", 100.0, source.getOrdinate(), this.tolerance);
    }

    @Test
    public void testMoveHorizontallyMoreThanRange() {
        Position source = new Position(100, 100);
        Position dest = new Position(40, 180);
        source.moveHorizontal(dest, 50);
        assertEquals("absis moved horizontally to destination more than max velocity", 50.0, source.getAbsis(), this.tolerance);
        assertEquals("ordinate moved horizontally to destination more than max velocity", 100.0, source.getOrdinate(), this.tolerance);
    }

    @Test
    public void testMoveVerticallyLessThanRange() {
        Position source = new Position(100, 100);
        Position dest = new Position(40, 180);
        source.moveVertical(dest, 100);
        assertEquals("absis moved vertically to destination less than max velocity", 100.0, source.getAbsis(), this.tolerance);
        assertEquals("ordinate moved vertically to destination less than max velocity", 180.0, source.getOrdinate(), this.tolerance);
    }

    @Test
    public void testMoveVerticallyMoreThanRange() {
        Position source = new Position(100, 100);
        Position dest = new Position(40, 180);
        source.moveVertical(dest, 50);
        assertEquals("absis moved vertically to destination more than max velocity", 100.0, source.getAbsis(), this.tolerance);
        assertEquals("ordinate moved vertically to destination more than max velocity", 150.0, source.getOrdinate(), this.tolerance);
    }

    @Test
    public void testExactEquals() {
        Position p1 = new Position(2.5, 3.5);
        Position p2 = new Position(2.500, 3.500);
        assertTrue("p1 should be equals to exact p2", p1.equals(p2));
        assertTrue("p2 should be equals to exact p1", p2.equals(p1));
    }

    @Test
    public void testToleranceEquals() {
        Position p1 = new Position(2.5, 3.5);
        Position p2 = new Position(2.5 + this.tolerance / 2, 3.5 + this.tolerance / 2);
        assertTrue("p1 should be equals to in tolerance p2", p1.equals(p2));
        assertTrue("p2 should be equals to in tolerance p1", p2.equals(p1));
    }

    @Test
    public void testIntolerantEquals() {
        Position p1 = new Position(2.5, 3.5);
        Position p2 = new Position(2.5 + this.tolerance + 0.001, 3.5 + this.tolerance + 0.001);
        assertFalse("p1 should not be equals to out of tolerance p2", p1.equals(p2));
        assertFalse("p2 should not be equals to out of tolerance p1", p2.equals(p1));
    }

    @Test
    public void testRandomAbsisBound() {
        Data.setMaxWidth(1);
        Position p = new Position();
        assertTrue("absis randomized should less than bound", p.getAbsis() < 1);
    }

    @Test
    public void testRandomOrdinateBound() {
        Data.setMaxHeight(1);
        Position p = new Position();
        assertTrue("ordinate randomized should less than bound", p.getOrdinate() < 1);
    }

    @Test
    public void testRandom() {
        Position p1 = new Position();
        Position p2 = new Position();
        assertTrue("new absis should be different with previous absis", Math.abs(p1.getAbsis() - p2.getAbsis()) > this.tolerance);
        assertTrue("new ordinate should be different with previous ordinate", Math.abs(p1.getOrdinate() - p2.getOrdinate()) > this.tolerance);
    }
}