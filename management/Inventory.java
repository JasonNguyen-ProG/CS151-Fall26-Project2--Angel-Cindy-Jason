package management;
import java.util.ArrayList;
import java.util.List;
import model.Ingredient;
import model.MenuItem;


public class Inventory {

    // Fields for the list of ingredients and the inventory capacity.
    private List<Ingredient> ingredients;
    private int capacity;

    // Constructor for initializing the inventory with a list of ingredients and a capacity.
    public Inventory(List<Ingredient> ingredients, int capacity) {
        this.ingredients = ingredients;
        this.capacity = capacity;
    }

    // Getter and setter methods for ingredients and capacity.
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // METHODS

    // Method for adding an ingredient to the inventory.
    public void addIngredient(Ingredient ingredient) {
        if (ingredients.size() < capacity) {
            ingredients.add(ingredient);
        } else {
            throw new IllegalStateException("Inventory is full. Cannot add more ingredients.");
        }
    }
    // Method for removing an ingredient from the inventory.
    public void removeIngredient(Ingredient ingredient) {
        if (ingredients.contains(ingredient)) {
            ingredients.remove(ingredient);
        } else {
            throw new IllegalArgumentException("Ingredient not found in inventory.");
        }
    }

    // Method for restocking an ingredient in the inventory.
    void restock(String name, int qty) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(name)) {
                ingredient.restock(qty);
                return;
            }
        }
        throw new IllegalArgumentException("Ingredient not found in inventory.");
    }

    // Method to check if the inventory has all ingredients for a given menu item.
    boolean hasIngredients(MenuItem item) {
        for (Ingredient ingredient : item.getIngredients()) {
            if (!ingredients.contains(ingredient)) {
                return false;
            }
        }
        return true;
    }

    // Method for consuming ingredients from the inventory based on a menu item.
    void consumeIngredients(MenuItem item) {
        for (Ingredient ingredient : item.getIngredients()) {
            if (ingredients.contains(ingredient)) {
                ingredients.remove(ingredient);
            } else {
                throw new IllegalArgumentException("Ingredient not found in inventory.");
            }
        }
    }
    // Methods for finding low stock ingredients.
    ArrayList<Ingredient> findLowInStock() {
        ArrayList<Ingredient> lowInStock = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.isLow()) {
                lowInStock.add(ingredient);
            }
        }
        return lowInStock;
    }

    // Method for finding ingredients that are out of stock.
    ArrayList<Ingredient> findOutOfStock() {
        ArrayList<Ingredient> outOfStock = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.isOutOfStock()) {
                outOfStock.add(ingredient);
            }
        }
        return outOfStock;
    }

}
