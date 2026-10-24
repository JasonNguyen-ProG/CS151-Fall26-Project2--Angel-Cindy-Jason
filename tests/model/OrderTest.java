package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.lang.reflect.Field;
import exceptions.MaximumInstanceException;
import management.SystemConstant;
import org.junit.Test;

public class OrderTest {
    // Checks initial details so orders start empty and pending
    @Test
    public void testNewOrder() {
        Order order = new Order(1);

        assertEquals(1, order.getOrderID());
        assertEquals("Pending", order.getStatus());
        
        assertTrue(order.getItems().isEmpty());
    }

    // Checks item changes and prices so customers are charged correctly
    @Test
    public void testItemsAndPrices() {
        Order order = new Order(2);
        Food bread = new Food(1, "Bread", 5.0, true, false, 0,
                "Small", "None", new ArrayList<>(), new ArrayList<>(), "");
        order.addItem(bread);

        assertEquals(1, order.getItems().size());
        assertEquals(5.0, order.calcSubtotal(), 0.001);
        assertEquals(0.5, order.calcTax(), 0.001);
        assertEquals(5.5, order.calcTotal(), 0.001);

        order.removeItem(bread);
        assertTrue(order.getItems().isEmpty());
    }

    // Checks if discounts and cancellation so savings and status are correct
    @Test
    public void testDiscountAndCancelOrder() {
        Order order = new Order(3);
        Food bread = new Food(1, "Bread", 10.0, true, true, SystemConstant.ITEM_DISCOUNT_RATE,
                "Small", "None", new ArrayList<>(), new ArrayList<>(), "");
        order.addItem(bread);

        assertTrue(order.isDiscountable());
        assertEquals(2.25, order.calcDiscount(), 0.001);
        assertEquals(6.75, order.calcPriceAfterDiscount(), 0.001);

        order.cancelOrder();
        assertEquals("Cancelled", order.getStatus());
    }

    // Checks the empty prices so an order without items costs nothing
    @Test
    public void testEmptyPrices() {
        Order order = new Order(4);

        assertFalse(order.isDiscountable());

        assertEquals(0.0, order.calcDiscount(), 0.001);
        assertEquals(0.0, order.calcTotal(), 0.001);
    }

    // Checks recalculation so removed that items are no longer charged at all
    @Test
    public void testPriceAfterRemoval() {
        Order order = new Order(5);
        Food bread = new Food(1, "Bread", 5.0, true, false, 0,
                "Small", "None", new ArrayList<>(), new ArrayList<>(), "");
        order.addItem(bread);

        assertEquals(5.5, order.calcTotal(), 0.001);

        order.removeItem(bread);
        assertEquals(0.0, order.calcTotal(), 0.001);
    }

    // Checks that the  unavailable items are rejected so an unservable order stays pending
    @Test
    public void testUnavailableItem() {
        Order order = new Order(6);
        Food bread = new Food(1, "Bread", 5.0, false, false, 0,
                "Small", "None", new ArrayList<>(), new ArrayList<>(), "");
        order.addItem(bread);

        assertThrows(IllegalStateException.class,
                () -> order.submitOrder(new management.Inventory(new ArrayList<>())));
        assertEquals("Pending", order.getStatus());
    }

    // Checks 100 orders can be created and the next is rejected to enforce the limit
    @Test
    public void testMaximumInstances() throws Exception {
        // Save and reset the private counter so other tests do not affect this one
        Field counter = Order.class.getDeclaredField("instanceCount");
        counter.setAccessible(true);
        int originalCount = counter.getInt(null);
        counter.setInt(null, 0);

        try {
            for (int i = 0; i < SystemConstant.MAXIMUM_INSTANCES; i++) {
                new Order(i + 1);
            }
            assertThrows(MaximumInstanceException.class, () -> new Order(SystemConstant.MAXIMUM_INSTANCES + 1));
        } finally {
            counter.setInt(null, originalCount);
        }
    }
}
