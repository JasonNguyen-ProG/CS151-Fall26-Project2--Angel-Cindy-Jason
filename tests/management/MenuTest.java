package management;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.lang.reflect.Field;
import exceptions.MaximumInstanceException;
import model.Food;
import model.Drink;
import org.junit.Test;

public class MenuTest {
    // Checks the menu starts empty so no items appear before being added
    @Test
    public void testNewMenu() {
        Menu menu = new Menu();

        assertTrue(menu.getMenuItems().isEmpty());
    }

    // Checks that the added croissant can be found so menu searches return the right item
    @Test
    public void testAddAndFindItem() {
        Menu menu = new Menu();
        Food croissant = new Food(1, "croissant", 5.0, true, false, 0,
                "Small", "None", new ArrayList<>(), new ArrayList<>(), "");
        menu.addItem(croissant);

        assertEquals(1, menu.getMenuItems().size());
        assertSame(croissant, menu.findItemByID(1));
        assertSame(croissant, menu.findItemByName("croissant"));
    }

    // Checks the milk tea can be found so drink searches return the right item
    @Test
    public void testFindBrownSugarMilkTea() {
        Menu menu = new Menu();
        Drink milkTea = new Drink(2, "Brown Sugar Milk Tea", 5.0, true, false, 0,
                "Small", 1.0, 0.5, "Cold", new ArrayList<>(), new ArrayList<>());
        menu.addItem(milkTea);

        assertSame(milkTea, menu.findItemByName("Brown Sugar Milk Tea"));
        assertSame(milkTea, menu.findItemByID(2));
    }

    // Checks if "croissant" is removed so it no longer appears on the menu
    @Test
    public void testRemoveItem() {
        Menu menu = new Menu();
        Food croissant = new Food(1, "croissant", 5.0, true, false, 0,
                "Small", "None", new ArrayList<>(), new ArrayList<>(), "");
        menu.addItem(croissant);
        menu.removeItem(croissant);

        assertTrue(menu.getMenuItems().isEmpty());
    }

    // Check missing items so unsuccessful searches return no match
    @Test
    public void testMissingItem() {
        Menu menu = new Menu();

        assertNull(menu.findItemByID(999));
        assertNull(menu.findItemByName("Missing"));
        assertTrue(menu.findItemByCategory("Missing").isEmpty());
    }

    // Checks the category searches return every match so no foods are skipped
    @Test
    public void testFindByCategory() {
        Menu menu = new Menu();
        Food bread = new Food(1, "Bread", 5.0, true, false, 0,
                "Small", "None", new ArrayList<>(), new ArrayList<>(), "");
        Food croissant = new Food(2, "Croissant", 6.0, true, false, 0,
                "Small", "None", new ArrayList<>(), new ArrayList<>(), "");
        menu.addItem(bread);
        menu.addItem(croissant);

        assertEquals(2, menu.findItemByCategory("food").size());
        assertTrue(menu.findItemByCategory("food").contains(bread));
        assertTrue(menu.findItemByCategory("food").contains(croissant));
    }

    // Checks the removal preserves other items so only the selected food disappears
    @Test
    public void testRemoveKeepsOtherItems() {
        Menu menu = new Menu();
        Food bread = new Food(1, "Bread", 5.0, true, false, 0,
                "Small", "None", new ArrayList<>(), new ArrayList<>(), "");
        Food croissant = new Food(2, "Croissant", 6.0, true, false, 0,
                "Small", "None", new ArrayList<>(), new ArrayList<>(), "");
        menu.addItem(bread);
        menu.addItem(croissant);
        menu.removeItem(bread);

        assertNull(menu.findItemByID(1));
        assertSame(croissant, menu.findItemByID(2));
    }

    // Checks the 101st menu is rejected to enforce the object limit
    @Test
    public void testMaximumInstances() throws Exception {
        // Save and reset the private counter so other tests do not affect this one
        Field counter = Menu.class.getDeclaredField("instanceCount");
        counter.setAccessible(true);
        int originalCount = counter.getInt(null);
        counter.setInt(null, 0);

        try {
            for (int i = 0; i < SystemConstant.MAXIMUM_INSTANCES; i++) {
                new Menu();
            }
            assertThrows(MaximumInstanceException.class, () -> new Menu());
        } finally {
            counter.setInt(null, originalCount);
        }
    }
}
