package management;

import java.util.ArrayList;

import exceptions.MaximumInstanceException;
import model.Order;

public class Cafe {
    // Cafe fields
    private String name;
    private Menu menu;
    private Inventory inventory;
    private ArrayList<Order> orders;
    private int nextOrderNumber;

    private static int instanceCount = 0;

    // Cafe constructor
    public Cafe(String name, Menu menu, Inventory inventory) {
        if (instanceCount >= SystemConstant.MAXIMUM_INSTANCES) {
            throw new MaximumInstanceException(
                    "Cannot have more than " + SystemConstant.MAXIMUM_INSTANCES + " Cafe objects");
        }

        this.name = name;
        this.menu = menu;
        this.inventory = inventory;
        this.orders = new ArrayList<>();
        this.nextOrderNumber = 1;
        instanceCount++;
    }

    // Cafe getters
    public String getName() {
        return this.name;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    // Cafe methods
    public Order createOrder() {
        return new Order(this.nextOrderNumber++);
    }

    public void submitOrder(Order order) {
        this.orders.add(order);
    }

    public void cancelOrder(Order order) {
        this.orders.remove(order);
    }

    public void cancelOrder(int orderId) {
        Order order = findOrder(orderId);
        if (order != null) {
            order.cancelOrder();
        }
    }

    public Order findOrder(int orderId) {
        for (Order order : this.orders) {
            if (order.getOrderID() == orderId) {
                return order;
            }
        }
        return null;
    }

    public ArrayList<Order> getActiveOrders() {
        ArrayList<Order> activeOrders = new ArrayList<>();
        for (Order order : this.orders) {
            if (!order.getStatus().equalsIgnoreCase("Cancelled")) {
                activeOrders.add(order);
            }
        }
        return activeOrders;
    }

    public double calcDayRevenue() {
        double revenue = 0.0;
        for (Order order : this.orders) {
            revenue += order.calcTotal();
        }
        return revenue;
    }

    public ArrayList<Order> getOrdersByStatus(String status) {
        ArrayList<Order> matches = new ArrayList<>();
        for (Order order : this.orders) {
            if (order.getStatus().equalsIgnoreCase(status)) {
                matches.add(order);
            }
        }
        return matches;
    }
}
