package model;

import java.util.ArrayList;

import management.Inventory;

public class Food extends MenuItem {
    private String size;
    private String temperature;
    private ArrayList<Ingredient> defaultIngredients;
    private ArrayList<Ingredient> customizations;
    private String specialRequest;

    public Food(int itemID, String name, double price, boolean inStock, String size, String temp,
            ArrayList<Ingredient> customizations, String specialRequest) {
        super(itemID, name, price, inStock);
        this.size = size;
        this.temperature = temp;
        this.customizations = customizations;
        this.specialRequest = specialRequest;
    }

    /**
     * Add Ingredient and adjust price of Food item
     * @param ingredient that customer wants extra of
     */
    public void addExtraIngredient(Ingredient ingredient) {

    }

    /**
     * Remove Ingredient and adjust price of Food item
     * @param ingredient that customer wants to remove from Food item
     */
    public void removeIngredient(Ingredient ingredient) {
        this.defaultIngredients.remove(ingredient);

        double adjustedPrice = this.getPrice() - ingredient.priceAsCustomization;
        this.setPrice(adjustedPrice);
    }

    /**
     * Substitute ingredient and adjust price of Food item
     * @param original the default Ingredient
     * @param subs     what the customer wants
     */
    public void substituteIngredient(Ingredient original, Ingredient subs) {

    }

    /**
     * Calculate price of a MenuItem with all
     * of the customizations if applicable
     * 
     * @return total - the total of this menu item
     */
    @Override
    public double calcPrice() {

        double basePrice = this.getPrice();
        // TODO: Loop through customizations
        // get priceAsCustomization and add it up
        double customizationPrice = 0;
        for (Ingredient customization : customizations) {
            customizationPrice += customization.priceAsCustomization;
        }

        return basePrice + customizationPrice;
    }

    /**
     * @return category of MenuItem
     */
    @Override
    public String getCategory() {
        return "Food";
    }

    /**
     * 
     * @param inventory checking if the actual Food item is still available to serve
     * @return boolean
     */
    @Override
    public boolean canServe(Inventory inventory) {
        return true;
    }
}
