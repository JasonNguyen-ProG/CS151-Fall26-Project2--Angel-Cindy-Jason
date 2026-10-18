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

    
}
