package management;

import java.util.ArrayList;

import exceptions.MaximumInstanceException;
import model.MenuItem;

public class Menu {
    
    // Menu fields
    private ArrayList<MenuItem> menuItems;
    
    private static int instanceCount = 0;

    // Menu constructor
    public Menu() {
        if (instanceCount >= SystemConstant.MAXIMUM_INSTANCES) {
            throw new MaximumInstanceException(
                    "Cannot have more than " + SystemConstant.MAXIMUM_INSTANCES + " Menu objects");
        }
        
        this.menuItems = new ArrayList<>();

        instanceCount++;
    }

    // Menu getters
    public ArrayList<MenuItem> getMenuItems() {
        return this.menuItems;
    }

    // Menu methods
    public void addItem(MenuItem item) {
        this.menuItems.add(item);
    }

    public void removeItem(MenuItem item) {
        this.menuItems.remove(item);
    }

    public MenuItem findItemByID(int itemID) {
        for (MenuItem item : this.menuItems) {
            if (item.getItemID() == itemID) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<MenuItem> findItemByCategory(String category) {
        ArrayList<MenuItem> matches = new ArrayList<>();
        for (MenuItem item : this.menuItems) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                matches.add(item);
            }
        }
        return matches;
    }

    public void displayAvailableItems() {
        System.out.println("        -----Food-----");
        for (MenuItem item : this.menuItems) {
            if (item.getInStock() && item.getCategory().equalsIgnoreCase("Food")) {
                System.out.println(item.getItemID() + " " + item.getName() + " - $" + item.calcPrice());
            }
        }

        System.out.println();
        System.out.println("        -----Drink-----");
        for (MenuItem item : this.menuItems) {
            if (item.getInStock() && item.getCategory().equalsIgnoreCase("Drink")) {
                System.out.println(item.getItemID() + " " + item.getName() + " - $" + item.calcPrice());
            }
        }
    }
}
