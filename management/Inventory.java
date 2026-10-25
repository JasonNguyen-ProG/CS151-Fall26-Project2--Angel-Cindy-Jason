package management;

import exceptions.InvalidIngredientException;
import exceptions.MaximumInstanceException;
import exceptions.InventoryException;

import java.util.ArrayList;
import model.Ingredient;

public class Inventory {

    // Fields for the list of ingredients and the inventory capacity.
    private ArrayList<Ingredient> ingredients;
    private int capacity;

    private static final int DEFAULT_REQUIRED_QTY = 1;
    private static int instanceCount = 0;

    // Constructor for initializing the inventory with a list of ingredients and a
    // capacity.
    public Inventory(ArrayList<Ingredient> ingredients) {
        if (ingredients == null) {
            throw new IllegalArgumentException("Ingredients list cannot be null.");
        }
        if (instanceCount >= SystemConstant.MAXIMUM_INSTANCES) {
            throw new MaximumInstanceException(
                    "Cannot have more than " + SystemConstant.MAXIMUM_INSTANCES + " Inventory objects");
        }
        if (ingredients.size() > SystemConstant.MAXIMUM_INGREDIENT_TYPES) {
            throw new IllegalArgumentException(
                    "Ingredients list cannot exceed " + SystemConstant.MAXIMUM_INGREDIENT_TYPES + " items.");
        }

        this.capacity = SystemConstant.MAXIMUM_INGREDIENT_TYPES;
        this.ingredients = ingredients;

        instanceCount++;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // Getter and setter methods for ingredients and capacity.
    public ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    // METHODS

    // Method for adding an ingredient to the inventory.
    public void addIngredient(Ingredient ingredient) {
        if (this.ingredients.size() < SystemConstant.MAXIMUM_INGREDIENT_TYPES) {
            this.ingredients.add(ingredient);
        } else {
            throw new InventoryException("Inventory is full. Cannot add more ingredients.");
        }
    }

    // Method for removing an ingredient from the inventory.
    public void removeIngredient(Ingredient ingredient) {
        if (this.ingredients.contains(ingredient)) {
            this.ingredients.remove(ingredient);
        } else {
            throw new InventoryException("Ingredient not found in inventory.");
        }
    }

    // Method for restocking an ingredient in the inventory.
    public void restock(String name, int qty) {
        for (Ingredient ingredient : this.ingredients) {
            if (ingredient.getName().equals(name)) {
                ingredient.restock(qty);
                return;
            }
        }
        throw new InventoryException("Ingredient not found in inventory.");
    }

    // Method to check if the inventory has all ingredients for a given menu item.
    public boolean hasIngredients(ArrayList<Ingredient> menuItemIngredients) {
        for (Ingredient ing : menuItemIngredients) {
            int idx = ingredients.indexOf(ing);

            if (idx == -1) {
                return false;
            }

            Ingredient inventoryIngredient = ingredients.get(idx);

            if (!inventoryIngredient.hasEnoughStock(DEFAULT_REQUIRED_QTY)) {
                return false;
            }
        }
        return true;
    }

    // Method for consuming ingredients from the inventory based on a menu item.
    public void consumeIngredients(ArrayList<Ingredient> menuItemIngredients) {
        for (Ingredient ing : menuItemIngredients) {
            int idx = ingredients.indexOf(ing);

            if (idx == -1) {
                throw new InvalidIngredientException(
                        ing.getName() + " not found");
            }

            Ingredient inventoryIngredient = ingredients.get(idx);

            inventoryIngredient.consume(DEFAULT_REQUIRED_QTY);
        }
    }

    // Methods for finding low stock ingredients.
    public ArrayList<Ingredient> findLowInStock() {
        ArrayList<Ingredient> lowInStock = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.isLow()) {
                lowInStock.add(ingredient);
            }
        }
        return lowInStock;
    }

    // Method for finding ingredients that are out of stock.
    public ArrayList<Ingredient> findOutOfStock() {
        ArrayList<Ingredient> outOfStock = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.isOutOfStock()) {
                outOfStock.add(ingredient);
            }
        }
        return outOfStock;
    }

}