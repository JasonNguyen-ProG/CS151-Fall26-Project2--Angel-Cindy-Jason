package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import exceptions.MaximumInstanceException;
import management.SystemConstant;
import exceptions.InvalidNumericValueException;
import model.Ingredient;


public class IngredientTest {
    private static Ingredient ingredient;

    @BeforeClass
    public static void createIngredient() {
        // Create it once because Ingredient tracks its instance count.
        ingredient = new Ingredient(1, "Coffee", 5, 2, 0.50);
    }

    @Before
    public void resetIngredient() {
        ingredient.setIngredientID(1);
        ingredient.setName("Coffee");
        ingredient.setQty(5);
        ingredient.setMinStock(2);
        ingredient.setPriceAsCustomization(0.50);
    }

    @Test
    public void constructorStoresValues() {
        assertEquals(1, ingredient.getIngredientID());
        assertEquals("Coffee", ingredient.getName());
        assertEquals(5, ingredient.getQty());
        assertEquals(2, ingredient.getMinStock());
        assertEquals(0.50, ingredient.getPriceAsCustomization(), 0.0001);
    }

    @Test
    public void settersUpdateValues() {
        ingredient.setIngredientID(7);
        ingredient.setName("Tea");
        ingredient.setQty(10);
        ingredient.setMinStock(3);
        ingredient.setPriceAsCustomization(0.75);

        assertEquals(7, ingredient.getIngredientID());
        assertEquals("Tea", ingredient.getName());
        assertEquals(10, ingredient.getQty());
        assertEquals(3, ingredient.getMinStock());
        assertEquals(0.75, ingredient.getPriceAsCustomization(), 0.0001);
    }

    @Test
    public void consumeReducesQuantity() {
        ingredient.consume(2);

        assertEquals(3, ingredient.getQty());
    }

    @Test
    public void consumeExactAvailableQuantity() {
        ingredient.consume(5);

        assertEquals(0, ingredient.getQty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void consumeMoreThanAvailableThrowsException() {
        ingredient.consume(6);
    }

    @Test
    public void restockIncreasesQuantity() {
        ingredient.restock(4);

        assertEquals(9, ingredient.getQty());
    }

    @Test(expected = InvalidNumericValueException.class)
    public void restockZeroThrowsException() {
        ingredient.restock(0);
    }

    @Test(expected = InvalidNumericValueException.class)
    public void restockNegativeAmountThrowsException() {
        ingredient.restock(-2);
    }

    @Test
    public void hasEnoughStockWhenQuantityEqualsAmount() {
        assertTrue(ingredient.hasEnoughStock(5));
    }

    @Test
    public void hasEnoughStockReturnsFalseWhenQuantityIsTooLow() {
        assertFalse(ingredient.hasEnoughStock(6));
    }

    @Test
    public void isLowWhenQuantityEqualsMinimum() {
        ingredient.setQty(2);

        assertTrue(ingredient.isLow());
    }

    @Test
    public void isLowWhenQuantityIsBelowMinimum() {
        ingredient.setQty(1);

        assertTrue(ingredient.isLow());
    }

    @Test
    public void isNotLowWhenQuantityIsAboveMinimum() {
        ingredient.setQty(3);

        assertFalse(ingredient.isLow());
    }

    @Test
    public void isOutOfStockWhenQuantityIsZero() {
        ingredient.setQty(0);

        assertTrue(ingredient.isOutOfStock());
    }

    @Test
    public void isNotOutOfStockWhenQuantityIsPositive() {
        ingredient.setQty(1);

        assertFalse(ingredient.isOutOfStock());
    }

    // Checks that the Ingredient limit is enforced and restores the counter afterward.
    @Test
    public void testMaximumInstances() throws Exception {
        Field counter = Ingredient.class.getDeclaredField("instanceCount");
        counter.setAccessible(true);

        int originalCount = counter.getInt(null);
        counter.setInt(null, 0);

        try {
            for (int i = 0; i < SystemConstant.MAXIMUM_INSTANCES; i++) {
                new Ingredient(i, "Test Ingredient", 1, 0, 0.50);
            }

            assertThrows(
                    MaximumInstanceException.class,
                    () -> new Ingredient(999, "Extra Ingredient", 1, 0, 0.50));
        } finally {
            counter.setInt(null, originalCount);
        }
    }
}