package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class OnlineOrderTest {

    @Test
    public void testOnlineOrderDetails() {
        OnlineOrder order = new OnlineOrder(
                1,
                "customer@example.com",
                "123 Main Street",
                "Credit Card");

        assertEquals(1, order.getOrderID());
        assertEquals("customer@example.com", order.getCustomerEmail());
        assertEquals("123 Main Street", order.getDeliveryAddress());
        assertEquals("Credit Card", order.getPaymentMethod());
        assertTrue(order.isDeliveryOrder());
        assertEquals("Pending", order.getStatus());
    }

    @Test
    public void testOnlineOrderInheritsOrderBehavior() {
        OnlineOrder order = new OnlineOrder(
                2,
                "customer@example.com",
                "456 Oak Avenue",
                "PayPal");

        Food sandwich = new Food(
                10,
                "Sandwich",
                8.00,
                true,
                false,
                0.0,
                "Regular",
                "None",
                new ArrayList<>(),
                new ArrayList<>(),
                "");

        order.addItem(sandwich);

        assertEquals(1, order.getItems().size());
        assertEquals(8.00, order.calcSubtotal(), 0.001);
        assertEquals(8.80, order.calcTotal(), 0.001);
    }

    @Test
    public void testCustomerSummary() {
        OnlineOrder order = new OnlineOrder(
                3,
                "bhoomika@example.com",
                "789 University Avenue",
                "Debit Card");

        assertEquals(
                "Online order for bhoomika@example.com delivered to 789 University Avenue",
                order.getCustomerSummary());
    }

    @Test
    public void testInvalidOnlineOrderDetails() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new OnlineOrder(
                        4,
                        "invalid-email",
                        "123 Main Street",
                        "Credit Card"));

        assertThrows(
                IllegalArgumentException.class,
                () -> new OnlineOrder(
                        5,
                        "customer@example.com",
                        "",
                        "Credit Card"));

        assertThrows(
                IllegalArgumentException.class,
                () -> new OnlineOrder(
                        6,
                        "customer@example.com",
                        "123 Main Street",
                        ""));
    }
}