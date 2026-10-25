package management;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.lang.reflect.Field;
import exceptions.MaximumInstanceException;
import model.Drink;
import model.Food;
import model.Order;
import org.junit.Test;

public class CafeTest {
    // Checks the name and empty orders so the cafe starts correctly
    @Test
    public void testNewCafe() {
        Menu menu = new Menu();
        Inventory inventory = new Inventory(new ArrayList<>());
        Cafe cafe = new Cafe("JAC Boba Cafe", menu, inventory);

        assertEquals("JAC Boba Cafe", cafe.getName());
        assertTrue(cafe.getOrders().isEmpty());
    }

    // Checks a boba order is stored so it can be found later
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

    // Checks food and drinks stay together so a mixed order keeps both items
    @Test
    public void testDrinkAndFoodOrder() {
        Cafe cafe = new Cafe("JAC Boba Cafe", new Menu(), new Inventory(new ArrayList<>()));
        Order order = cafe.createOrder();
        Drink boba = new Drink(1, "Brown Sugar Boba", 5.0, true, false, 0,
                "Small", 1.0, 0.5, "Cold", new ArrayList<>(), new ArrayList<>());
        Food croissant = new Food(2, "Croissant", 5.0, true, false, 0,
                "Small", "None", new ArrayList<>(), new ArrayList<>(), "");
        order.addItem(boba);
        order.addItem(croissant);
        cafe.submitOrder(order);
        Order savedOrder = cafe.findOrder(order.getOrderID());

        assertNotNull(savedOrder);
        assertEquals(2, savedOrder.getItems().size());
        assertTrue(savedOrder.getItems().contains(boba));
        assertTrue(savedOrder.getItems().contains(croissant));
        assertEquals(11.0, savedOrder.calcTotal(), 0.001);
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

    // Check sequential IDs so each new order has its own number
    @Test
    public void testOrderNumbers() {
        Cafe cafe = new Cafe("JAC Boba Cafe", new Menu(), new Inventory(new ArrayList<>()));

        assertEquals(1, cafe.createOrder().getOrderID());
        assertEquals(2, cafe.createOrder().getOrderID());
    }

    // Checks the missing IDs so searching or cancelling one leaves orders unchanged
    @Test
    public void testMissingOrder() {
        Cafe cafe = new Cafe("JAC Boba Cafe", new Menu(), new Inventory(new ArrayList<>()));

        assertNull(cafe.findOrder(999));
        cafe.cancelOrder(999);
        assertTrue(cafe.getOrders().isEmpty());
    }

    // Checks status searches ignore case so matching orders are easy to find
    @Test
    public void testFindOrdersByStatus() {
        Cafe cafe = new Cafe("JAC Boba Cafe", new Menu(), new Inventory(new ArrayList<>()));
        Order first = cafe.createOrder();
        Order second = cafe.createOrder();
        cafe.submitOrder(first);
        cafe.submitOrder(second);
        second.cancelOrder();

        assertEquals(1, cafe.getOrdersByStatus("cancelled").size());
        assertSame(second, cafe.getOrdersByStatus("cancelled").get(0));
        assertTrue(cafe.getOrdersByStatus("Missing").isEmpty());
    }

    // Checks 100 cafes can be created and the next is rejected to enforce the limit
    @Test
    public void testMaximumInstances() throws Exception {
        // Save and reset the counter so other tests do not affect this one
        Field counter = Cafe.class.getDeclaredField("instanceCount");
        counter.setAccessible(true);
        int originalCount = counter.getInt(null);
        counter.setInt(null, 0);

        try {
            Menu menu = new Menu();
            Inventory inventory = new Inventory(new ArrayList<>());
            for (int i = 0; i < SystemConstant.MAXIMUM_INSTANCES; i++) {
                new Cafe("JAC Boba Cafe", menu, inventory);
            }
            assertThrows(MaximumInstanceException.class,
                    () -> new Cafe("JAC Boba Cafe", menu, inventory));
        } finally {
            counter.setInt(null, originalCount);
        }
    }
}
