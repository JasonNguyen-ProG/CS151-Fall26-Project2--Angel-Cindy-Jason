package model;

import exceptions.InvalidNumericValueException;
import exceptions.MaximumInstanceException;
import management.SystemConstant;

public class Ingredient {

    // Fields for ingredient ID, name, quantity, unit, minimum stock, and price as customization.
    private int ingredientID;
    private String name;
    private int qty;
    private int minStock;
    private double priceAsCustomization;

    private static int instanceCount = 0;

    // Constructor for initializing an ingredient with all its fields.
    public Ingredient(int ingredientID, String name, int qty, int minStock, double priceAsCustomization) {
        if (instanceCount >= SystemConstant.MAXIMUM_INSTANCES) {
            throw new MaximumInstanceException(
                    "Cannot have more than " + SystemConstant.MAXIMUM_INSTANCES + " Ingredient objects");
        }
        
        this.ingredientID = ingredientID;
        this.name = name;
        this.qty = qty;
        this.minStock = minStock;
        this.priceAsCustomization = priceAsCustomization;

        instanceCount++;
    }

    // Getter and setter methods for each field.
    public int getIngredientID() {
        return this.ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return this.qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getMinStock() {
        return this.minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public double getPriceAsCustomization() {
        return this.priceAsCustomization;
    }

    public void setPriceAsCustomization(double priceAsCustomization) {
        this.priceAsCustomization = priceAsCustomization;
    }

    // Methods for managing stock levels: consume, restock, check if enough stock, and check if low stock.

    public void consume(int amount) {
        if (amount <= this.qty) {
            this.qty -= amount;
        } else {
            throw new IllegalArgumentException("Not enough stock to consume the requested amount.");
        }
    }

    public void restock(int amount) {
        if (amount > 0) {
            this.qty += amount;
        } else {
            throw new InvalidNumericValueException("Restock amount must be positive.");
        }
    }

    public boolean hasEnoughStock(int amount) {
        return this.qty >= amount;
    }

    public boolean isLow() {
        return this.qty <= minStock;
    }
    
    public boolean isOutOfStock() {
        return this.qty == 0;
    }
}
