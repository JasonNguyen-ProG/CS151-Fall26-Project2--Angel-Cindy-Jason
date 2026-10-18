package management;
import java.util.List;
import model.Ingredient;


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

    
}
