package model;

public class Ingredient {

    // Fields for ingredient ID, name, quantity, unit, minimum stock, and price as customization.
    private int ingredientID;
    private String name;
    private int qty;
    private int unit;
    private int minStock;
    private double priceAsCustomization;

    // Constructor for initializing an ingredient with all its fields.
    public Ingredient(int ingredientID, String name, int qty, int unit, int minStock, double priceAsCustomization) {
        this.ingredientID = ingredientID;
        this.name = name;
        this.qty = qty;
        this.unit = unit;
        this.minStock = minStock;
        this.priceAsCustomization = priceAsCustomization;
    }

    // Getter and setter methods for each field.
    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public double getPriceAsCustomization() {
        return priceAsCustomization;
    }

    public void setPriceAsCustomization(double priceAsCustomization) {
        this.priceAsCustomization = priceAsCustomization;
    }

    // Methods for managing stock levels: consume, restock, check if enough stock, and check if low stock.

    public void consume(int amount) {
        if (amount <= qty) {
            qty -= amount;
        } else {
            throw new IllegalArgumentException("Not enough stock to consume the requested amount.");
        }
    }

    public void restock(int amount) {
        if (amount > 0) {
            qty += amount;
        } else {
            throw new IllegalArgumentException("Restock amount must be positive.");
        }
    }

    public boolean hasEnoughStock(int amount) {
        return qty >= amount;
    }

    public boolean isLow() {
        return qty <= minStock;
    }
    
}
