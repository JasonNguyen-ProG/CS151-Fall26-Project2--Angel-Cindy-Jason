package model;

import java.util.ArrayList;

import exceptions.InvalidIngredientException;
import exceptions.MaximumInstanceException;
import management.Inventory;
import management.SystemConstant;

public class Food extends MenuItem {

    // Food fields
    private String size;
    private String spiceLvl;
    private ArrayList<Ingredient> currentIngredients;
    private ArrayList<Ingredient> customizations;
    private String specialRequest;

    private static int instanceCount = 0;

    public Food(int itemID, String name, double price, boolean inStock, boolean discountable, double rate, String size,
            String spice,
            ArrayList<Ingredient> currentIngredients, ArrayList<Ingredient> customizations, String specialRequest) {
        super(itemID, name, price, inStock, discountable, rate);
        
        if (instanceCount >= SystemConstant.MAXIMUM_INSTANCES) {
            throw new MaximumInstanceException("Cannot have more than " + SystemConstant.MAXIMUM_INSTANCES + " Food objects");
        }
        
        this.size = size;
        this.spiceLvl = spice;
        this.currentIngredients = currentIngredients;
        this.customizations = customizations;
        this.specialRequest = specialRequest;

        instanceCount++;
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
            throw new InvalidIngredientException(ingredient.getName() + " is not part of this Food item");
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
            throw new InvalidIngredientException(original.getName() + " is not a part of this Food item");
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
        if (!getInStock() || inventory == null) {
            return false;
        }

        return inventory.hasIngredients(currentIngredients) && inventory.hasIngredients(customizations);
    }

    /**
     * 
     * @return customerCopy of MenuItem
     */
    @Override
    public MenuItem customerCopy() {
        return new Food(this.getItemID(), this.getName(), this.getPrice(), this.getInStock(), this.isDiscountable(), this.getDiscountRate(),
                this.size, this.spiceLvl, new ArrayList<>(currentIngredients), new ArrayList<>(customizations), this.specialRequest);
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

    public ArrayList<Ingredient> getCurrentIngredients() {
        return this.currentIngredients;
    }

    public ArrayList<Ingredient> getCustomizations() {
        return this.customizations;
    }

    public String getSpecialRequest() {
        return this.specialRequest;
    }

    public void setSpecialRequest(String newRequest) {
        this.specialRequest = newRequest;
    }
}
