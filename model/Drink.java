package model;

import java.util.ArrayList;

import exceptions.InvalidIngredientException;
import exceptions.InvalidNumericValueException;
import exceptions.MaximumInstanceException;
import management.Inventory;
import management.SystemConstant;

public class Drink extends MenuItem {

    // Drink fields
    private String size;
    private double sweetnessLvl;
    private double iceLvl;
    private String temperature;
    private ArrayList<Ingredient> baseIngredients;
    private ArrayList<Ingredient> toppings;

    private static int instanceCount = 0;

    public Drink(int itemID, String name, double price, boolean inStock, boolean discountable, double rate, String size,
            double sweetness, double ice, String temp, ArrayList<Ingredient> base, ArrayList<Ingredient> toppings) {
        super(itemID, name, price, inStock, discountable, rate);
        
        if (instanceCount >= SystemConstant.MAXIMUM_INSTANCES) {
            throw new MaximumInstanceException(
                    "Cannot have more than " + SystemConstant.MAXIMUM_INSTANCES + " Drink objects");
        }
        
        this.size = size;
        this.sweetnessLvl = sweetness;
        this.iceLvl = ice;
        this.temperature = temp;
        this.baseIngredients = base;
        this.toppings = toppings;

        instanceCount++;
    }

    /**
     * Add more topping to Drink item
     * 
     * @param topping
     */
    public void addTopping(Ingredient topping) {
        if (topping == null) {
            throw new InvalidIngredientException("Topping cannot be null");
        }
        this.toppings.add(topping);
    }

    /**
     * Remove topping from Drink item
     * 
     * @param topping
     */
    public void removeTopping(Ingredient topping) {
        if (topping == null) {
            throw new InvalidIngredientException("Topping cannot be null");
        }

        if (!toppings.contains(topping)) {
            throw new InvalidIngredientException(topping.getName() + " is not a part of this drink");
        }

        this.toppings.remove(topping);
    }

    /**
     * Adjust sweetness level
     * Possible values (0.25, 0.5, 0.75, 1)
     * 
     * @param newSweetness
     */
    public void adjustSweetness(double newSweetness) {
        if (newSweetness != 0 && newSweetness != 0.25 && newSweetness != 0.5 && newSweetness != 0.75
                && newSweetness != 1.0) {
            throw new InvalidNumericValueException("Sweetness must be 0% | 25% | 50% | 75% | 100%");
        }

        this.sweetnessLvl = newSweetness;
    }

    /**
     * 
     * @return total
     */
    @Override
    public double calcPrice() {
        double total = this.getPrice();

        if (size.equalsIgnoreCase("Large")) {
            total += 2.0;
        } else if (size.equalsIgnoreCase("Medium")) {
            total += 1.0;
        }

        for (Ingredient topping : toppings) {
            total += topping.getPriceAsCustomization();
        }

        return total;
    }

    @Override
    public String getCategory() {
        return "Drink";
    }

    @Override
    public boolean canServe(Inventory inventory) {
        if (!getInStock() || inventory == null) {
            return false;
        }

        return inventory.hasIngredients(baseIngredients) && inventory.hasIngredients(toppings);
    }

    /*-----Getters and Setters-----*/
    public String getSize() {
        return this.size;
    }

    public void setSize(String newSize) {
        this.size = newSize;
    }

    public double getSweetnessLvl() {
        return this.sweetnessLvl;
    }

    public double getIceLvl() {
        return this.iceLvl;
    }

    public void setIceLvl(double newIce) {
        this.iceLvl = newIce;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public void setTemperature(String newTemp) {
        this.temperature = newTemp;
    }
}
