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
}
