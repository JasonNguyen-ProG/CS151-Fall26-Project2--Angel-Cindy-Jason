package model;

import java.util.ArrayList;

import management.Inventory;

public class Food extends MenuItem {
    private String size;
    private String temperature;
    private ArrayList<Ingredient> customizations;
    private ArrayList<String> specialRequests;

    public Food(int itemID, String name, double price, boolean inStock, String size, String temp,
            ArrayList<Ingredient> customizations, ArrayList<String> specialRequests) {
        super(itemID, name, price, inStock);
        this.size = size;
        this.temperature = temp;
        this.customizations = customizations;
        this.specialRequests = specialRequests;
    }

    /**
     * Adding cutomer's special requests to their food
     * @param request
     */
    public void addSpecialRequests(String request) {
        this.specialRequests.add(request);
    }



    /**
     * Calculate price of a MenuItem with all
     * of the customizations if applicable
     * @return total - the total of this menu item
     */
    @Override
    public double calcPrice() {
        double total = 0;

        double basePrice = this.getPrice();
        double customizationPrice = ;

        total = basePrice + customizationPrice;

        return total;
    }

    /**
     * @return category of MenuItem
     */
    @Override
    public String getCategory() {
        return "Food";
    }

    @Override
    public boolean canServe(Inventory inventory) {
        return true;
    }
}
