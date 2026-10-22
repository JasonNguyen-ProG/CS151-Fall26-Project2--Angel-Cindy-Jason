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

    private static int instanceCount = 0;

    // Cafe constructor
    public Cafe(String name, Menu menu, Inventory inventory) {
        if (instanceCount > SystemConstant.MAXIMUM_INSTANCES) {
            throw new MaximumInstanceException(
                    "Cannot have more than " + SystemConstant.MAXIMUM_INSTANCES + " Cafe objects");
        } 
        
        this.name = name;
        this.menu = menu;
        this.inventory = inventory;
        this.orders = new ArrayList<>();

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
    public void submitOrder(Order order) {
        this.orders.add(order);
    }

    public void cancelOrder(Order order) {
        this.orders.remove(order);
    }
}
