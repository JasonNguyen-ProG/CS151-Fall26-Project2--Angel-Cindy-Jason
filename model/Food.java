package model;

import java.util.ArrayList;

import management.Inventory;

public class Food extends MenuItem {
    private String size;
    private String temperature;
    private ArrayList<Ingredient> sauces;
    private String specialNotes;

    public Food(int itemID, String name, double price, boolean inStock, String size, String temp,
            ArrayList<Ingredient> sauces, String specialNotes) {
        super(itemID, name, price, inStock);
        this.size = size;
        this.temperature = temp;
        this.sauces = sauces;
        this.specialNotes = specialNotes;
    }

    /**
     * Add sauce to Food item
     * @param sauce
     */
    public void addSauce(Ingredient sauce) {
        this.sauces.add(sauce);
    }

    /**
     * Remove sauce from Food item
     * @param sauce
     */
    public void removeSauce(Ingredient sauce) {
        this.sauces.remove(sauce);
    }

    /**
     * Calculate price of a MenuItem with all
     * of the customizations if applicable
     * @return total - the total of all items
     */
    @Override
    public double calcPrice() {
        double total = 0;

        double basePrice = this.getPrice();
        double customizationPrice = 

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
