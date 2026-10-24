package management;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.lang.reflect.Field;
import exceptions.MaximumInstanceException;
import model.Food;
import org.junit.Test;

public class MenuTest {
    // Checks the menu starts empty so no items appear before being added
    @Test
    public void testNewMenu() {
        Menu menu = new Menu();
        assertTrue(menu.getMenuItems().isEmpty());
    }

    // Checks added croissant can be found so menu searches return the right item
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
