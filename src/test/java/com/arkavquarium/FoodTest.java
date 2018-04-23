import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import com.arkavquarium.models.Food;

public class FoodTest {
    private double tolerance = 0.001;

    @Test
    public void testAbsisContructor() {
        Food f = new Food(100, 200);
        assertEquals("constructor Food first argument set absis", 100, f.getPosition().getAbsis(), this.tolerance);
        assertEquals("constructor Food second argument set ordinate", 200, f.getPosition().getOrdinate(), this.tolerance);
    }

    @Test
    public void testPriceConstructor() {
        Food f = new Food(100, 200);
        assertEquals("constructor Food set Price to 10 by default", 10, f.getPrice());
    }

    @Test
    public void testMoveLessThanRange() {
        Food f = new Food(100, 200);
        f.move(225, 1.0);
        assertEquals("move not change Food absis", 100, f.getPosition().getAbsis(), this.tolerance);
        assertEquals("move change Food ordinate less than max velocity", 225, f.getPosition().getOrdinate(), this.tolerance);
    }

    @Test
    public void testMoveMoreThanRange() {
        Food f = new Food(100, 200);
        f.move(235, 1.0);
        assertEquals("move not change Food absis", 100, f.getPosition().getAbsis(), this.tolerance);
        assertEquals("move change Food ordinate more than max velocity", 230, f.getPosition().getOrdinate(), this.tolerance);
    }
}