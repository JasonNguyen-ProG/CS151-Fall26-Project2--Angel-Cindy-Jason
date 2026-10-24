package model;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import exceptions.MaximumInstanceException;
import management.SystemConstant;
import org.junit.Test;

public class FoodTest {
    // Checks the base price so food without extras has no added charge
    @Test
    public void testBasePrice() {
        Food bread = new Food(1, "Bread", 5.0, true, false, 0,
                "Small", "None", new ArrayList<>(), new ArrayList<>(), "");

        assertEquals("Food", bread.getCategory());
        assertEquals(5.0, bread.calcPrice(), 0.001);
    }

    // Checks extra ingredients affect price so customizations are charged
    @Test
    public void testExtraIngredientPrice() {
        Food bread = new Food(1, "Bread", 5.0, true, false, 0,
                "Small", "None", new ArrayList<>(), new ArrayList<>(), "");
        bread.addExtraIngredient(new Ingredient(1, "Cheese", 10, 2, 0.75));

        assertEquals(5.75, bread.calcPrice(), 0.001);
    }

    // Checks null extras are rejected so invalid ingredients cannot be added
    @Test
    public void testInvalidExtraIngredient() {
        Food bread = new Food(1, "Bread", 5.0, true, false, 0,
                "Small", "None", new ArrayList<>(), new ArrayList<>(), "");

        assertThrows(exceptions.InvalidIngredientException.class,
                () -> bread.addExtraIngredient(null));
    }

    // Checks stock affects serving so unavailable food cannot be ordered
    @Test
    public void testServingAvailability() {
        Food bread = new Food(1, "Bread", 5.0, true, false, 0,
                "Small", "None", new ArrayList<>(), new ArrayList<>(), "");
        management.Inventory inventory = new management.Inventory(new ArrayList<>());

        assertTrue(bread.canServe(inventory));

        bread.setInStock(false);
        assertFalse(bread.canServe(inventory));
    }

    // Checks 100 foods can be created and the next is rejected to enforce the limit
    @Test
    public void testMaximumInstances() throws Exception {
        // Save and reset the counter so other tests do not affect this one
        Field counter = Food.class.getDeclaredField("instanceCount");
        counter.setAccessible(true);
        int originalCount = counter.getInt(null);
        counter.setInt(null, 0);

        try {
            for (int i = 0; i < SystemConstant.MAXIMUM_INSTANCES; i++) {
                new Food(i + 1, "Bread", 5.0, true, false, 0,
                        "Small", "None", new ArrayList<>(), new ArrayList<>(), "");
            }
            assertThrows(MaximumInstanceException.class,
                    () -> new Food(SystemConstant.MAXIMUM_INSTANCES + 1, "Bread", 5.0,
                            true, false, 0, "Small", "None", new ArrayList<>(), new ArrayList<>(), ""));
        } finally {
            counter.setInt(null, originalCount);
        }
    }
}
