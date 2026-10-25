package management;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.Arrays;

import exceptions.InvalidIngredientException;
import exceptions.MaximumInstanceException;
import java.lang.reflect.Field;
import model.Ingredient;
import management.Inventory;

public class InventoryTest {
    private static Inventory inventory;
    private static Ingredient coffee;
    private static Ingredient milk;
    private static Ingredient sugar;
    private static Ingredient tea;

    // run before to create sample ingredients and inventory
    @BeforeClass
    public static void createFixture() {
        // Reset the instance count for ingredients and inventory to ensure a clean test environment
        Ingredient.resetInstanceCount();
        Inventory.resetInstanceCount();

        // Create sample ingredients
        coffee = new Ingredient(1, "Coffee", 5, 1, 0.50);
        milk = new Ingredient(2, "Milk", 3, 2, 0.25);
        sugar = new Ingredient(3, "Sugar", 0, 0, 0.10);
        tea = new Ingredient(4, "Tea", 4, 1, 0.30);

        inventory = new Inventory(new ArrayList<>(Arrays.<Ingredient>asList(coffee, milk, sugar)));
    }

    // run before each test to reset the quantities and inventory
    @Before
    public void resetFixture() {
        coffee.setQty(5);
        milk.setQty(3);
        sugar.setQty(0);
        tea.setQty(4);

        inventory.setIngredients(new ArrayList<>(Arrays.<Ingredient>asList(coffee, milk, sugar)));
        inventory.setCapacity(3);
    }
    // Tests for the Inventory class methods
    @Test
    public void constructorStoresIngredientsAndCapacity() {
        assertEquals(3, inventory.getIngredients().size());
        assertEquals(3, inventory.getCapacity());
    }

    // Checks that the constructor correctly stores the initial ingredients and capacity
    @Test
    public void addIngredientSucceedsWhenThereIsRoom() {
        inventory.setCapacity(4);

        inventory.addIngredient(tea);

        assertTrue(inventory.getIngredients().contains(tea));
        assertEquals(4, inventory.getIngredients().size());
    }

    // Checks that adding an ingredient succeeds when there is room in the inventory
    @Test(expected = IllegalStateException.class)
    public void addIngredientThrowsWhenInventoryIsFull() {
        inventory.addIngredient(tea);
    }

    // Checks that adding an ingredient throws an exception when the inventory is full
    @Test
    public void removeIngredientRemovesAnExistingIngredient() {
        inventory.removeIngredient(coffee);

        assertFalse(inventory.getIngredients().contains(coffee));
        assertEquals(2, inventory.getIngredients().size());
    }

    // Checks that removing an ingredient throws an exception when the ingredient is not in the inventory
    @Test(expected = IllegalArgumentException.class)
    public void removeIngredientThrowsWhenIngredientIsMissing() {
        inventory.removeIngredient(tea);
    }

    // Checks that restocking an ingredient increases its quantity
    @Test
    public void restockIncreasesQuantityOfMatchingIngredient() {
        inventory.restock("Coffee", 3);

        assertEquals(8, coffee.getQty());
    }

    // Checks that restocking an ingredient throws an exception when the ingredient is not found
    @Test(expected = IllegalArgumentException.class)
    public void restockThrowsWhenIngredientNameIsMissing() {
        inventory.restock("Tea", 2);
    }

    // Checks that hasIngredients returns true when all requested ingredients are available
    @Test
    public void hasIngredientsReturnsTrueWhenAllAreAvailable() {
        //ArrayList<Ingredient> requested = new ArrayList<>(Arrays.asList(coffee, milk));
        ArrayList<Ingredient> requested = new ArrayList<Ingredient>(Arrays.<Ingredient>asList(coffee, milk));
        assertTrue(inventory.hasIngredients(requested));
    }

    // Checks that hasIngredients returns false when a requested ingredient is missing
    @Test
    public void hasIngredientsReturnsFalseWhenAnIngredientIsMissing() {
        ArrayList<Ingredient> requested = new ArrayList<Ingredient>(Arrays.<Ingredient>asList(tea));

        assertFalse(inventory.hasIngredients(requested));
    }

    // Checks that hasIngredients returns false when the stock of a requested ingredient is zero
    @Test
    public void hasIngredientsReturnsFalseWhenStockIsZero() {
        ArrayList<Ingredient> requested = new ArrayList<>(Arrays.<Ingredient>asList(sugar));

        assertFalse(inventory.hasIngredients(requested));
    }

    // Checks that hasIngredients returns true for an empty request
    @Test
    public void hasIngredientsReturnsTrueForAnEmptyRequest() {
        assertTrue(inventory.hasIngredients(new ArrayList<Ingredient>()));
    }

    // Checks that consumeIngredients decreases the quantity of the requested ingredient by one unit
    @Test
    public void consumeIngredientsConsumesOneUnit() {
        ArrayList<Ingredient> requested = new ArrayList<>(Arrays.<Ingredient>asList(coffee));

        inventory.consumeIngredients(requested);

        assertEquals(4, coffee.getQty());
    }

    // Checks that consumeIngredients throws an exception when a requested ingredient is missing
    @Test(expected = InvalidIngredientException.class)
    public void consumeIngredientsThrowsWhenIngredientIsMissing() {
        ArrayList<Ingredient> requested = new ArrayList<>(Arrays.<Ingredient>asList(tea));

        inventory.consumeIngredients(requested);
    }

    // Checks that findLowInStock returns ingredients that are at or below their minimum stock level
    @Test
    public void findLowInStockReturnsIngredientsAtOrBelowMinimum() {
        coffee.setQty(1); // minStock is 1

        ArrayList<Ingredient> lowStock = inventory.findLowInStock();

        assertTrue(lowStock.contains(coffee));
        assertTrue(lowStock.contains(sugar)); // qty 0, minStock 0
        assertEquals(2, lowStock.size());
    }

    // Checks that findOutOfStock returns ingredients that have zero quantity
    @Test
    public void findOutOfStockReturnsOnlyIngredientsWithZeroQuantity() {
        ArrayList<Ingredient> outOfStock = inventory.findOutOfStock();

        assertEquals(1, outOfStock.size());
        assertTrue(outOfStock.contains(sugar));
    }

    // Checks that getIngredients returns a copy of the ingredients list so modifications do not affect the inventory
    @Test
    public void getIngredientsReturnsACopy() {
        inventory.getIngredients().clear();

        assertEquals(3, inventory.getIngredients().size());
    }

    // Checks that addIngredient throws an exception when the ingredient is already present
    @Test(expected = IllegalArgumentException.class)
    public void addIngredientThrowsWhenIngredientIsAlreadyPresent() {
        inventory.setCapacity(4);

        inventory.addIngredient(coffee);
    }

    // Checks that addIngredient throws an exception when the ingredient is null
    @Test(expected = IllegalArgumentException.class)
    public void addIngredientThrowsWhenIngredientIsNull() {
        inventory.addIngredient(null);
    }

    // Checks that restock throws an exception when the quantity is not positive
    @Test(expected = IllegalArgumentException.class)
    public void restockThrowsWhenQuantityIsNotPositive() {
        inventory.restock("Coffee", 0);
    }

    // Checks that consumeIngredients throws an exception when a requested ingredient is out of stock
    @Test
    public void consumeIngredientsThrowsWhenIngredientIsOutOfStock() {
    ArrayList<Ingredient> requested = new ArrayList<>(Arrays.<Ingredient>asList(sugar));

    assertThrows(InvalidIngredientException.class,
            () -> inventory.consumeIngredients(requested));

    assertEquals(0, sugar.getQty());
    }

    // Checks that consumeIngredients does not change the quantities of other ingredients when one requested ingredient is out of stock
    @Test
    public void consumeIngredientsChangesNothingWhenOneIngredientIsOutOfStock() {
    ArrayList<Ingredient> requested = new ArrayList<>(Arrays.<Ingredient>asList(coffee, sugar));

    assertThrows(InvalidIngredientException.class,
            () -> inventory.consumeIngredients(requested));

    assertEquals(5, coffee.getQty());
    assertEquals(0, sugar.getQty());
    }

    // Checks that creating more than the maximum allowed instances of Inventory throws a MaximumInstanceException
    @Test
    public void testMaximumInstances() throws Exception {
    Field counter = Inventory.class.getDeclaredField("instanceCount");
    counter.setAccessible(true);

    int originalCount = counter.getInt(null);
    counter.setInt(null, 0);

    try {
        for (int i = 0; i < SystemConstant.MAXIMUM_INSTANCES; i++) {
            new Inventory(new ArrayList<>());
        }

        assertThrows(
                MaximumInstanceException.class,
                () -> new Inventory(new ArrayList<>()));
    } finally {
        counter.setInt(null, originalCount);
    }
    }
}   
