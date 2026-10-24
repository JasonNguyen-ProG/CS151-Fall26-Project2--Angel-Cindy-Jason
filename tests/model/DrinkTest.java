package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.InvalidIngredientException;
import exceptions.InvalidNumericValueException;
import management.Inventory;
import model.Drink;
import model.Ingredient;

public class DrinkTest {
    private static Drink drink;
    private static Ingredient coffee;
    private static Ingredient boba;
    private static Ingredient whippedCream;
    private static Inventory inventory;

    @BeforeClass
    public static void createFixtures() {
        coffee = new Ingredient(1, "Coffee", 5, 1, 0.00);
        boba = new Ingredient(2, "Boba", 5, 1, 0.50);
        whippedCream = new Ingredient(3, "Whipped Cream", 5, 1, 0.75);

        drink = new Drink(
                10,
                "Iced Coffee",
                4.00,
                true,
                true,
                0.10,
                "Medium",
                0.50,
                0.50,
                "Cold",
                new ArrayList<>(Arrays.asList(coffee)),
                new ArrayList<>(Arrays.asList(boba)));

        inventory = new Inventory(new ArrayList<>(Arrays.asList(coffee, boba)), 3);
    }

    @Before
    public void resetFixtures() {
        coffee.setQty(5);
        boba.setQty(5);
        whippedCream.setQty(5);

        drink.setSize("Medium");
        drink.adjustSweetness(0.50);
        drink.setIceLvl(0.50);
        drink.setTemperature("Cold");

        drink.getBaseIngredients().clear();
        drink.getBaseIngredients().add(coffee);

        drink.getToppings().clear();
        drink.getToppings().add(boba);

        inventory.setIngredients(
                new ArrayList<>(Arrays.asList(coffee, boba)));
                inventory.setCapacity(3);
    }

    @Test
    public void constructorStoresDrinkDetails() {
        assertEquals("Medium", drink.getSize());
        assertEquals(0.50, drink.getSweetnessLvl(), 0.0001);
        assertEquals(0.50, drink.getIceLvl(), 0.0001);
        assertEquals("Cold", drink.getTemperature());
        assertEquals(1, drink.getBaseIngredients().size());
        assertEquals(1, drink.getToppings().size());
    }

    @Test
    public void settersUpdateSizeIceAndTemperature() {
        drink.setSize("Large");
        drink.setIceLvl(0.25);
        drink.setTemperature("Hot");

        assertEquals("Large", drink.getSize());
        assertEquals(0.25, drink.getIceLvl(), 0.0001);
        assertEquals("Hot", drink.getTemperature());
    }

    @Test
    public void addToppingAddsTopping() {
        drink.addTopping(whippedCream);

        assertTrue(drink.getToppings().contains(whippedCream));
        assertEquals(2, drink.getToppings().size());
    }

    @Test(expected = InvalidIngredientException.class)
    public void addNullToppingThrowsException() {
        drink.addTopping(null);
    }

    @Test
    public void removeToppingRemovesExistingTopping() {
        drink.removeTopping(boba);

        assertFalse(drink.getToppings().contains(boba));
        assertTrue(drink.getToppings().isEmpty());
    }

    @Test(expected = InvalidIngredientException.class)
    public void removeToppingThrowsWhenToppingIsNotPartOfDrink() {
        drink.removeTopping(whippedCream);
    }

    @Test(expected = InvalidIngredientException.class)
    public void removeNullToppingThrowsException() {
        drink.removeTopping(null);
    }

    @Test
    public void adjustSweetnessAcceptsAllowedLevel() {
        drink.adjustSweetness(0.75);

        assertEquals(0.75, drink.getSweetnessLvl(), 0.0001);
    }

    @Test
    public void adjustSweetnessAcceptsZero() {
        drink.adjustSweetness(0.0);

        assertEquals(0.0, drink.getSweetnessLvl(), 0.0001);
    }

    @Test(expected = InvalidNumericValueException.class)
    public void adjustSweetnessRejectsUnallowedLevel() {
        drink.adjustSweetness(0.60);
    }

    @Test
    public void calcPriceAddsMediumSizeAndToppingCosts() {
        // Base price 4.00 + medium size 1.00 + boba 0.50
        assertEquals(5.50, drink.calcPrice(), 0.0001);
    }

    @Test
    public void calcPriceAddsLargeSizeAndToppingCosts() {
        // Base price 4.00 + large size 2.00 + boba 0.50
        drink.setSize("Large");

        assertEquals(6.50, drink.calcPrice(), 0.0001);
    }

    @Test
    public void calcPriceAddsNoSizeChargeForSmallDrink() {
        // Base price 4.00 + no small-size charge + boba 0.50
        drink.setSize("Small");

        assertEquals(4.50, drink.calcPrice(), 0.0001);
    }

    @Test
    public void getCategoryReturnsDrink() {
        assertEquals("Drink", drink.getCategory());
    }

    @Test
    public void canServeReturnsTrueWhenAllIngredientsAreInStock() {
        assertTrue(drink.canServe(inventory));
    }

    @Test
    public void canServeReturnsFalseWhenInventoryIsNull() {
        assertFalse(drink.canServe(null));
    }

    @Test
    public void canServeReturnsFalseWhenAnIngredientIsMissing() {
        inventory.setIngredients(new ArrayList<>(Arrays.asList(coffee)));

        assertFalse(drink.canServe(inventory));
    }

    @Test
    public void customerCopyPreservesDrinkDetailsAndCopiesIngredientLists() {
        Drink copy = (Drink) drink.customerCopy();

        assertNotSame(drink, copy);
        assertEquals(drink.getName(), copy.getName());
        assertEquals(drink.getPrice(), copy.getPrice(), 0.0001);
        assertEquals(drink.getSize(), copy.getSize());
        assertEquals(drink.getSweetnessLvl(), copy.getSweetnessLvl(), 0.0001);
        assertEquals(drink.getIceLvl(), copy.getIceLvl(), 0.0001);
        assertEquals(drink.getTemperature(), copy.getTemperature());
        assertEquals(drink.getBaseIngredients(), copy.getBaseIngredients());
        assertEquals(drink.getToppings(), copy.getToppings());
        assertNotSame(drink.getBaseIngredients(), copy.getBaseIngredients());
        assertNotSame(drink.getToppings(), copy.getToppings());
    }
}