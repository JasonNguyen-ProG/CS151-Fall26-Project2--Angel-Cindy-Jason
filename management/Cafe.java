package management;

import java.util.ArrayList;
import model.Order;

public class Cafe {
    // Cafe fields
    private String name;
    private Menu menu;
    private Inventory inventory;
    private ArrayList<Order> orders;
    private int nextOrderNumber;

    // Cafe constructor
    public Cafe(String name, Menu menu, Inventory inventory) {
        this.name = name;
        this.menu = menu;
        this.inventory = inventory;
        this.orders = new ArrayList<>();
        this.nextOrderNumber = 1;
    }

    // Cafe getters
    public String getName() {
        return name;
    }

    public Menu getMenu() {
        return menu;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    // Cafe methods
    public Order createOrder() {
        return new Order(nextOrderNumber++);
    }

    public void submitOrder(Order order) {
        orders.add(order);
    }

    public void cancelOrder(int orderId) {
        Order order = findOrder(orderId);
        if (order != null) {
            order.cancelOrder();
        }
    }

    public Order findOrder(int orderId) {
        for (Order order : orders) {
            if (order.getOrderID() == orderId) {
                return order;
            }
        }
        return null;
    }

    public ArrayList<Order> getActiveOrders() {
        ArrayList<Order> activeOrders = new ArrayList<>();
        for (Order order : orders) {
            if (!order.getStatus().equalsIgnoreCase("Cancelled")) {
                activeOrders.add(order);
            }
        }
        return activeOrders;
    }

    public double calcDayRevenue() {
        double revenue = 0.0;
        for (Order order : orders) {
            revenue += order.calcTotal();
        }
        return revenue;
    }

    public ArrayList<Order> getOrdersByStatus(String status) {
        ArrayList<Order> matches = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus().equalsIgnoreCase(status)) {
                matches.add(order);
            }
        }
        return matches;
    }
}
