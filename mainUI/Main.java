package mainUI;

import java.util.Scanner;
import java.util.ArrayList;

import management.Cafe;
import management.Menu;
import management.Inventory;

import model.Order;
import model.Food;
import model.Drink;
import model.Ingredient;

public class Main {
    
    private static final Scanner scanner = new Scanner(System.in);
    
    private static Cafe myCafe;

    private static void displayMainUI() {
        System.out.println("===============================");
        System.out.println("     Cafe Order Management");
        System.out.println("===============================");
        System.out.println("1. Create Order");
        System.out.println("2. Manage Order");
        System.out.println("3. View Menu");
        System.out.println("4. Manage Menu");
        System.out.println("5. Manage Inventory");
        System.out.println("6. Cafe Summary");
        System.out.println("7. Exit");
    }

    private static void initializeCafe(String name, Menu menu, Inventory inventory) {

        ArrayList<Ingredient> ingredientsList = initializeIngredients();

        myCafe = new Cafe(name, menu, inventory);
    }
    
    private static ArrayList<Ingredient> initializeIngredients() {
        ArrayList<Ingredient> finalList = new ArrayList<>();

        // All ingredients
        finalList.add(new Ingredient(1, "Black Tea", 50, 10, 0.00));
        finalList.add(new Ingredient(2, "Green Tea", 50, 10, 0.00));
        finalList.add(new Ingredient(3, "Thai Tea", 40, 10, 0.00));
        finalList.add(new Ingredient(4, "Matcha", 30, 5, 0.75));
        finalList.add(new Ingredient(5, "Milk", 50, 10, 0.50));
        finalList.add(new Ingredient(6, "Condensed Milk", 30, 5, 0.50));
        finalList.add(new Ingredient(7, "Brown Sugar", 40, 5, 0.00));
        finalList.add(new Ingredient(8, "Strawberry Syrup", 30, 5, 0.50));
        finalList.add(new Ingredient(9, "Mango Syrup", 30, 5, 0.50));
        finalList.add(new Ingredient(10, "Espresso", 40, 10, 1.00));
        finalList.add(new Ingredient(11, "Chocolate", 30, 5, 0.50));
        finalList.add(new Ingredient(12, "Water", 100, 10, 0.00));
        finalList.add(new Ingredient(13, "Boba", 40, 10, 0.75));
        finalList.add(new Ingredient(14, "Grass Jelly", 30, 5, 0.75));
        finalList.add(new Ingredient(15, "Pudding", 30, 5, 0.75));
        finalList.add(new Ingredient(16, "Whipped Cream", 30, 5, 0.50));
        finalList.add(new Ingredient(17, "English Muffin", 30, 5, 0.00));
        finalList.add(new Ingredient(18, "Egg", 40, 10, 1.25));
        finalList.add(new Ingredient(19, "Cheddar Cheese", 30, 5, 0.75));
        finalList.add(new Ingredient(20, "Bacon", 30, 5, 1.50));
        finalList.add(new Ingredient(21, "Ciabatta Bread", 30, 5, 0.00));
        finalList.add(new Ingredient(22, "Chicken", 40, 10, 2.00));
        finalList.add(new Ingredient(23, "Mozzarella", 30, 5, 0.75));
        finalList.add(new Ingredient(24, "Pesto", 30, 5, 0.50));
        finalList.add(new Ingredient(25, "Tomato", 30, 5, 0.50));
        finalList.add(new Ingredient(26, "Brioche Bun", 30, 5, 0.00));
        finalList.add(new Ingredient(27, "Lettuce", 30, 5, 0.25));
        finalList.add(new Ingredient(28, "Pickles", 30, 5, 0.25));
        finalList.add(new Ingredient(29, "Spicy Mayo", 30, 5, 0.50));
        finalList.add(new Ingredient(30, "Sourdough", 30, 5, 0.00));
        finalList.add(new Ingredient(31, "Avocado", 30, 5, 1.50));
        finalList.add(new Ingredient(32, "Chili Flakes", 30, 5, 0.25));
        finalList.add(new Ingredient(33, "Rice", 50, 10, 1.00));
        finalList.add(new Ingredient(34, "Carrot", 30, 5, 0.50));
        finalList.add(new Ingredient(35, "Cucumber", 30, 5, 0.50));
        finalList.add(new Ingredient(36, "Teriyaki Sauce", 30, 5, 0.50));
        finalList.add(new Ingredient(37, "Butter Croissant", 20, 5, 1.50));
        finalList.add(new Ingredient(38, "Chocolate Chip Cookie", 20, 5, 1.00));
        finalList.add(new Ingredient(39, "Blueberry Muffin", 20, 5, 1.25));

        return finalList;
    }

    private static void createOrder() {
        System.out.println("Create Order");
    }

    private static void manageOrder() {
        System.out.println("Manage Order");
    }

    private static void viewMenu() {
        System.out.println("View Menu");
    }

    private static void manageMenu() {
        System.out.println("Manage Menu");
    }

    private static void manageInventory() {
        System.out.println("Manage Inventory");
    }

    private static void cafeSummary() {
        System.out.println("Cafe Summary");
    }

    public static void main(String[] args) {
        boolean running = true;
        String userEntry;

        while (running) {
            
            displayMainUI();

            System.out.print("\nSelect an option (1 - 7): ");
            userEntry = scanner.nextLine().trim();

            if (userEntry.equalsIgnoreCase("EXIT")) {
                System.out.println("Program exited...");
                running = false;
                break;
            }
            
            switch (userEntry) {
                case "1":
                    createOrder();
                    break;

                case "2":
                    manageOrder();
                    break;

                case "3":
                    viewMenu();
                    break;

                case "4":
                    manageMenu();
                    break;

                case "5":
                    manageInventory();
                    break;

                case "6":
                    cafeSummary();
                    break;

                case "7":
                    System.out.println("Program exited...");
                    running = false;
                    break;
            
                default:
                    System.out.println("Invalid Option. Please choose an option from 1 to 7");
            }
        }
    }
}
