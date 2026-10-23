package management;

import java.util.ArrayList;
import model.MenuItem;

public class Menu {
    // Menu fields
    private ArrayList<MenuItem> menuItems;

    // Menu constructor
    public Menu() {
        menuItems = new ArrayList<>();
    }

    // Menu getters
    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    // Menu methods
    public void addItem(MenuItem item) {
        menuItems.add(item);
    }

    public void removeItem(MenuItem item) {
        menuItems.remove(item);
    }

    public MenuItem findItemByID(int itemID) {
        for (MenuItem item : menuItems) {
            if (item.getItemID() == itemID) {
                return item;
            }
        }
        return null;
    }

    public MenuItem findItem(String name) {
        for (MenuItem item : menuItems) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public MenuItem findByCategory(String category) {
        for (MenuItem item : menuItems) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                return item;
            }
        }
        return null;
    }

    public void setAvailability(int id, boolean available) {
        for (MenuItem item : menuItems) {
            if (item.getItemID() == id) {
                item.setInStock(available);
                return;
            }
        }
    }

    public ArrayList<MenuItem> displayAvailableItems() {
        ArrayList<MenuItem> availableItems = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getInStock()) {
                availableItems.add(item);
            }
        }
        return availableItems;
    }
}
