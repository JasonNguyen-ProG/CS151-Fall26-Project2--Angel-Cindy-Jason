package management;

import java.util.ArrayList;
import model.Order;

public class Cafe {
    // Cafe fields
    private String name;
    private Menu menu;
    private Inventory inventory;
    private ArrayList<Order> orders;

    // Cafe constructor
    public Cafe(String name, Menu menu, Inventory inventory) {
        this.name = name;
        this.menu = menu;
        this.inventory = inventory;
        this.orders = new ArrayList<>();
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
    public void submitOrder(Order order) {
        orders.add(order);
    }

    public void cancelOrder(Order order) {
        orders.remove(order);
    }
}
