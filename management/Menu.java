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

    public MenuItem findItemByName(String name) {
        for (MenuItem item : menuItems) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<MenuItem> findItemByCategory(String category) {
        ArrayList<MenuItem> matches = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                matches.add(item);
            }
        }
        return matches;
    }

    public void displayAvailableItems() {
        for (MenuItem item : menuItems) {
            if (item.getInStock()) {
                System.out.println(item.getName() + " - $" + item.calcPrice());
            }
        }
    }
}
