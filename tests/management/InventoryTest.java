package management;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.Arrays;

import exceptions.InvalidIngredientException;
import model.Ingredient;
import management.Inventory;

public class InventoryTest {
    private static Inventory inventory;
    private static Ingredient coffee;
    private static Ingredient milk;
    private static Ingredient sugar;
    private static Ingredient tea;

    @BeforeClass
    public static void createFixture() {
        coffee = new Ingredient(1, "Coffee", 5, 1, 0.50);
        milk = new Ingredient(2, "Milk", 3, 2, 0.25);
        sugar = new Ingredient(3, "Sugar", 0, 0, 0.10);
        tea = new Ingredient(4, "Tea", 4, 1, 0.30);

        inventory = new Inventory(new ArrayList<>(Arrays.asList(coffee, milk, sugar)));
    }

    @Before
    public void resetFixture() {
        coffee.setQty(5);
        milk.setQty(3);
        sugar.setQty(0);
        tea.setQty(4);

        inventory.setIngredients(new ArrayList<>(Arrays.asList(coffee, milk, sugar)));
        inventory.setCapacity(3);
    }

    @Test
    public void constructorStoresIngredientsAndCapacity() {
        assertEquals(3, inventory.getIngredients().size());
        assertEquals(3, inventory.getCapacity());
    }

    @Test
    public void addIngredientSucceedsWhenThereIsRoom() {
        inventory.setCapacity(4);

        inventory.addIngredient(tea);

        assertTrue(inventory.getIngredients().contains(tea));
        assertEquals(4, inventory.getIngredients().size());
    }

    @Test(expected = IllegalStateException.class)
    public void addIngredientThrowsWhenInventoryIsFull() {
        inventory.addIngredient(tea);
    }

    @Test
    public void removeIngredientRemovesAnExistingIngredient() {
        inventory.removeIngredient(coffee);

        assertFalse(inventory.getIngredients().contains(coffee));
        assertEquals(2, inventory.getIngredients().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeIngredientThrowsWhenIngredientIsMissing() {
        inventory.removeIngredient(tea);
    }

    @Test
    public void restockIncreasesQuantityOfMatchingIngredient() {
        inventory.restock("Coffee", 3);

        assertEquals(8, coffee.getQty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void restockThrowsWhenIngredientNameIsMissing() {
        inventory.restock("Tea", 2);
    }

    @Test
    public void hasIngredientsReturnsTrueWhenAllAreAvailable() {
        //ArrayList<Ingredient> requested = new ArrayList<>(Arrays.asList(coffee, milk));
        ArrayList<Ingredient> requested = new ArrayList<Ingredient>(Arrays.<Ingredient>asList(coffee, milk));
        assertTrue(inventory.hasIngredients(requested));
    }

    @Test
    public void hasIngredientsReturnsFalseWhenAnIngredientIsMissing() {
        ArrayList<Ingredient> requested = new ArrayList<>(Arrays.<Ingredient>asList(tea));

        assertFalse(inventory.hasIngredients(requested));
    }

    @Test
    public void hasIngredientsReturnsFalseWhenStockIsZero() {
        ArrayList<Ingredient> requested = new ArrayList<>(Arrays.asList(sugar));

        assertFalse(inventory.hasIngredients(requested));
    }

    @Test
    public void hasIngredientsReturnsTrueForAnEmptyRequest() {
        assertTrue(inventory.hasIngredients(new ArrayList<Ingredient>()));
    }

    @Test
    public void consumeIngredientsConsumesOneUnit() {
        ArrayList<Ingredient> requested = new ArrayList<>(Arrays.asList(coffee));

        inventory.consumeIngredients(requested);

        assertEquals(4, coffee.getQty());
    }

    @Test(expected = InvalidIngredientException.class)
    public void consumeIngredientsThrowsWhenIngredientIsMissing() {
        ArrayList<Ingredient> requested = new ArrayList<>(Arrays.asList(tea));

        inventory.consumeIngredients(requested);
    }

    @Test
    public void findLowInStockReturnsIngredientsAtOrBelowMinimum() {
        coffee.setQty(1); // minStock is 1

        ArrayList<Ingredient> lowStock = inventory.findLowInStock();

        assertTrue(lowStock.contains(coffee));
        assertTrue(lowStock.contains(sugar)); // qty 0, minStock 0
        assertEquals(2, lowStock.size());
    }

    @Test
    public void findOutOfStockReturnsOnlyIngredientsWithZeroQuantity() {
        ArrayList<Ingredient> outOfStock = inventory.findOutOfStock();

        assertEquals(1, outOfStock.size());
        assertTrue(outOfStock.contains(sugar));
    }

    @Test
    public void getIngredientsReturnsACopy() {
        inventory.getIngredients().clear();

        assertEquals(3, inventory.getIngredients().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addIngredientThrowsWhenIngredientIsAlreadyPresent() {
        inventory.setCapacity(4);

        inventory.addIngredient(coffee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addIngredientThrowsWhenIngredientIsNull() {
        inventory.addIngredient(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void restockThrowsWhenQuantityIsNotPositive() {
        inventory.restock("Coffee", 0);
    }

    @Test
    public void consumeIngredientsThrowsWhenIngredientIsOutOfStock() {
        ArrayList<Ingredient> requested = new ArrayList<>(Arrays.asList(sugar));

        try {
            inventory.consumeIngredients(requested);
            fail("Expected InvalidIngredientException");
        } catch (InvalidIngredientException e) {
            assertEquals(0, sugar.getQty());
        }
    }

    @Test
    public void consumeIngredientsChangesNothingWhenOneIngredientIsOutOfStock() {
        ArrayList<Ingredient> requested = new ArrayList<>(Arrays.asList(coffee, sugar));

        try {
            inventory.consumeIngredients(requested);
            fail("Expected InvalidIngredientException");
        } catch (InvalidIngredientException e) {
            assertEquals(5, coffee.getQty());
            assertEquals(0, sugar.getQty());
        }
    }
}