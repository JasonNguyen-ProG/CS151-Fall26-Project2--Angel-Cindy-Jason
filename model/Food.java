package model;

import java.util.ArrayList;

import exceptions.InvalidIngredientException;
import management.Inventory;

public class Food extends MenuItem {
    private String size;
    private String spiceLvl;
    private ArrayList<Ingredient> defaultIngredients;
    private ArrayList<Ingredient> currentIngredients;
    private ArrayList<Ingredient> customizations;
    private String specialRequest;

    public Food(int itemID, String name, double price, boolean inStock, boolean discountable, double rate, String size, String spice,
            ArrayList<Ingredient> defaultIngredients, ArrayList<Ingredient> customizations, String specialRequest) {
        super(itemID, name, price, inStock, discountable, rate);
        this.size = size;
        this.spiceLvl = spice;
        this.defaultIngredients = defaultIngredients;
        this.currentIngredients = new ArrayList<>(defaultIngredients);
        this.customizations = customizations;
        this.specialRequest = specialRequest;
    }

    /**
     * Add Ingredient
     * 
     * @param ingredient that customer wants extra of
     */
    public void addExtraIngredient(Ingredient ingredient) {
        if (ingredient == null) {
            throw new InvalidIngredientException("Invalid Ingredient");
        }
        
        this.customizations.add(ingredient);
    }

    /**
     * Remove Ingredient
     * 
     * @param ingredient that customer wants to remove from Food item
     */
    public void removeIngredient(Ingredient ingredient) {
        if (ingredient == null) {
            throw new InvalidIngredientException("Ingredient cannot be null");
        }

        if (!this.currentIngredients.contains(ingredient)) {
            throw new InvalidIngredientException("Ingredient is not part of this Food item");
        }

        this.currentIngredients.remove(ingredient);
    }

    /**
     * Substitute ingredient
     * 
     * @param original the default Ingredient
     * @param subs     what the customer wants
     */
    public void substituteIngredient(Ingredient original, Ingredient subs) {
        if (original == null || subs == null) {
            throw new InvalidIngredientException("Invalid Ingredients");
        }

        int originalIdx = this.currentIngredients.indexOf(original);

        if (originalIdx == -1) {
            throw new InvalidIngredientException("Original Ingredient is not a part of this Food item");
        }

        this.currentIngredients.set(originalIdx, subs);
    }

    /**
     * Calculate price of a MenuItem with all
     * of the customizations if applicable
     * 
     * @return the total of this menu item
     */
    @Override
    public double calcPrice() {
        double basePrice = this.getPrice();
        // Loop through customizations
        // get priceAsCustomization and add it up
        double customizationPrice = 0;
        for (Ingredient customization : customizations) {
            customizationPrice += customization.getPriceAsCustomization();
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

    /*-----Getters and Setters-----*/
    public String getSize() {
        return this.size;
    }

    public void setSize(String newSize) {
        this.size = newSize;
    }

    public String getSpiceLvl() {
        return this.spiceLvl;
    }

    public void setSpiceLvl(String newSpiceLvl) {
        this.spiceLvl = newSpiceLvl;
    }

    public String getSpecialRequest() {
        return this.specialRequest;
    }

    public void setSpecialRequest(String newRequest) {
        this.specialRequest = newRequest;
    }
}
