package management;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.lang.reflect.Field;
import exceptions.MaximumInstanceException;
import model.Drink;
import model.Order;
import org.junit.Test;

public class CafeTest {
    // Check the name and empty orders so the cafe starts correctly
    @Test
    public void testNewCafe() {
        Menu menu = new Menu();
        Inventory inventory = new Inventory(new ArrayList<>());
        Cafe cafe = new Cafe("JAC Boba Cafe", menu, inventory);
        assertEquals("JAC Boba Cafe", cafe.getName());
        assertTrue(cafe.getOrders().isEmpty());
    }

    // Checks that a boba order is stored so it may be found later
    @Test
    public void testCreateAndSubmitOrder() {
        Cafe cafe = new Cafe("JAC Boba Cafe", new Menu(),
                new Inventory(new ArrayList<>()));
        Order order = cafe.createOrder();
        Drink boba = new Drink(1, "Brown Sugar Boba", 5.0, true, false, 0,
                "Small", 1.0, 0.5, "Cold", new ArrayList<>(), new ArrayList<>());
        order.addItem(boba);
        cafe.submitOrder(order);

        assertEquals(1, cafe.getOrders().size());
        assertSame(order, cafe.findOrder(order.getOrderID()));
        assertEquals("Brown Sugar Boba", order.getItems().get(0).getName());
    }

    // Checks the cancellation so cancelled orders are no longer active
    @Test
    public void testCancelOrder() {
        Cafe cafe = new Cafe("JAC Boba Cafe", new Menu(),
                new Inventory(new ArrayList<>()));
        Order order = cafe.createOrder();
        cafe.submitOrder(order);
        cafe.cancelOrder(order.getOrderID());

        assertEquals("Cancelled", order.getStatus());
        assertTrue(cafe.getActiveOrders().isEmpty());
    }

    // Checks 50 more cafes reach the limit when 50 already exist
    @Test
    public void testMaximumInstances() throws Exception {
        Field counter = Cafe.class.getDeclaredField("instanceCount");
        counter.setAccessible(true);
        int originalCount = counter.getInt(null);

        // Sets limit to 50
        counter.setInt(null, 50);
        try {
            Menu menu = new Menu();
            Inventory inventory = new Inventory(new ArrayList<>());

            for (int i = 50; i < SystemConstant.MAXIMUM_INSTANCES; i++) {
                new Cafe("JAC Boba Cafe", menu, inventory);
            }
            // Checks that its actually throwing exception
            assertThrows(MaximumInstanceException.class, () -> new Cafe("JAC Boba Cafe", menu, inventory));
        } finally {
            counter.setInt(null, originalCount);
        }
    }
}
