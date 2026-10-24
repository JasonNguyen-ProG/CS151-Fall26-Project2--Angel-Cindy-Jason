package mainUI;

import java.util.Scanner;
import java.util.ArrayList;

import exceptions.InvalidIngredientException;

import management.Cafe;
import management.Menu;
import management.SystemConstant;
import management.Inventory;

import model.Order;
import model.Food;
import model.Drink;
import model.Ingredient;
import model.MenuItem;

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

    private static void initializeCafe() {

        ArrayList<Ingredient> ingredientsList = initializeIngredients();
        Inventory inventory = new Inventory(ingredientsList);
        Menu menu = new Menu();

        initializeFoods(menu, ingredientsList);
        initializeDrinks(menu, ingredientsList);

        myCafe = new Cafe("JAC Cafe", menu, inventory);
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

    private static void initializeFoods(Menu menu, ArrayList<Ingredient> ingredientsList) {
        // Food 1
        ArrayList<Ingredient> breakfastSandwichIngredients = new ArrayList<>();
        breakfastSandwichIngredients.add(findIngredientByName(ingredientsList, "English Muffin"));
        breakfastSandwichIngredients.add(findIngredientByName(ingredientsList, "Egg"));
        breakfastSandwichIngredients.add(findIngredientByName(ingredientsList, "Cheddar Cheese"));
        breakfastSandwichIngredients.add(findIngredientByName(ingredientsList, "Bacon"));

        Food breakfastSandwich = new Food(201, "Breakfast Sandwich", 8.50, true, false, 0.0, "Regular", "None",
                breakfastSandwichIngredients, new ArrayList<>(), "");

        // Food 2
        ArrayList<Ingredient> chickenPestoSandwichIngredients = new ArrayList<>();
        chickenPestoSandwichIngredients.add(findIngredientByName(ingredientsList, "Ciabatta Bread"));
        chickenPestoSandwichIngredients.add(findIngredientByName(ingredientsList, "Chicken"));
        chickenPestoSandwichIngredients.add(findIngredientByName(ingredientsList, "Mozzarella"));
        chickenPestoSandwichIngredients.add(findIngredientByName(ingredientsList, "Pesto"));
        chickenPestoSandwichIngredients.add(findIngredientByName(ingredientsList, "Tomato"));

        Food chickenPestoSandwich = new Food(202, "Chicken Pesto Sandwich", 10.50, true, false, 0.0, "Regular", "None",
                chickenPestoSandwichIngredients, new ArrayList<>(), "");

        // Food 3
        ArrayList<Ingredient> spicyChickenSandwichIngredients = new ArrayList<>();
        spicyChickenSandwichIngredients.add(findIngredientByName(ingredientsList, "Brioche Bun"));
        spicyChickenSandwichIngredients.add(findIngredientByName(ingredientsList, "Chicken"));
        spicyChickenSandwichIngredients.add(findIngredientByName(ingredientsList, "Lettuce"));
        spicyChickenSandwichIngredients.add(findIngredientByName(ingredientsList, "Pickles"));
        spicyChickenSandwichIngredients.add(findIngredientByName(ingredientsList, "Spicy Mayo"));

        Food spicyChickenSandwich = new Food(203, "Spicy Chicken Sandwich", 10.00, true, true,
                SystemConstant.ITEM_DISCOUNT_RATE, "Regular", "Hot",
                spicyChickenSandwichIngredients, new ArrayList<>(), "");

        // Food 4
        ArrayList<Ingredient> avocadoToastIngredients = new ArrayList<>();
        avocadoToastIngredients.add(findIngredientByName(ingredientsList, "Sourdough"));
        avocadoToastIngredients.add(findIngredientByName(ingredientsList, "Avocado"));
        avocadoToastIngredients.add(findIngredientByName(ingredientsList, "Egg"));
        avocadoToastIngredients.add(findIngredientByName(ingredientsList, "Chili Flakes"));

        Food avocadoToast = new Food(204, "Avocado Toast", 8.00, true, false, 0.0, "Regular", "Mild",
                avocadoToastIngredients, new ArrayList<>(), "");

        // Food 5
        ArrayList<Ingredient> chickenTeriyakiIngredients = new ArrayList<>();
        chickenTeriyakiIngredients.add(findIngredientByName(ingredientsList, "Rice"));
        chickenTeriyakiIngredients.add(findIngredientByName(ingredientsList, "Chicken"));
        chickenTeriyakiIngredients.add(findIngredientByName(ingredientsList, "Carrot"));
        chickenTeriyakiIngredients.add(findIngredientByName(ingredientsList, "Cucumber"));
        chickenTeriyakiIngredients.add(findIngredientByName(ingredientsList, "Teriyaki Sauce"));

        Food chickenTeriyaki = new Food(205, "Chicken Teriyaki Rice Bowl", 11.50, true, true,
                SystemConstant.ITEM_DISCOUNT_RATE, "Regular", "None",
                chickenTeriyakiIngredients, new ArrayList<>(), "");

        // Food 6
        ArrayList<Ingredient> butterCroissantIngredients = new ArrayList<>();
        butterCroissantIngredients.add(findIngredientByName(ingredientsList, "Butter Croissant"));

        Food butterCroissant = new Food(206, "Butter Croissant", 4.00, true, false,
                0.0, "Individual", "None",
                butterCroissantIngredients, new ArrayList<>(), "");

        // Food 7
        ArrayList<Ingredient> chocolateChipCookieIngredients = new ArrayList<>();
        chocolateChipCookieIngredients.add(findIngredientByName(ingredientsList, "Chocolate Chip Cookie"));

        Food chocolateChipCookie = new Food(207, "Chocolate Chip Cookie", 3.00, true, false,
                0.0, "Individual", "None",
                chocolateChipCookieIngredients, new ArrayList<>(), "");

        // Food 8
        ArrayList<Ingredient> blueberryMuffinIngredients = new ArrayList<>();
        blueberryMuffinIngredients.add(findIngredientByName(ingredientsList, "Blueberry Muffin"));

        Food blueberryMuffin = new Food(208, "Blueberry Muffin", 4.25, true, false,
                0.0, "Individual", "None",
                blueberryMuffinIngredients, new ArrayList<>(), "");

        menu.addItem(breakfastSandwich);
        menu.addItem(chickenPestoSandwich);
        menu.addItem(spicyChickenSandwich);
        menu.addItem(avocadoToast);
        menu.addItem(chickenTeriyaki);
        menu.addItem(butterCroissant);
        menu.addItem(chocolateChipCookie);
        menu.addItem(blueberryMuffin);
    }

    private static void initializeDrinks(Menu menu, ArrayList<Ingredient> ingredientsList) {

        // Drink 1
        ArrayList<Ingredient> milkTeaIngredients = new ArrayList<>();
        milkTeaIngredients.add(findIngredientByName(ingredientsList, "Black Tea"));
        milkTeaIngredients.add(findIngredientByName(ingredientsList, "Milk"));

        Drink classicMilkTea = new Drink(101, "Classic Milk Tea", 4.50, true, false, 0.0, "Small", 1.0, 1.0, "Cold",
                milkTeaIngredients, new ArrayList<>());

        // Drink 2
        ArrayList<Ingredient> thaiMilkTeaIngredients = new ArrayList<>();
        thaiMilkTeaIngredients.add(findIngredientByName(ingredientsList, "Thai Tea"));
        thaiMilkTeaIngredients.add(findIngredientByName(ingredientsList, "Milk"));

        Drink thaiMilkTea = new Drink(102, "Thai Milk Tea", 4.75, true, false, 0.0, "Small", 1.0, 1.0, "Cold",
                thaiMilkTeaIngredients, new ArrayList<>());

        // Drink 3
        ArrayList<Ingredient> matchaLatteIngredients = new ArrayList<>();
        matchaLatteIngredients.add(findIngredientByName(ingredientsList, "Matcha"));
        matchaLatteIngredients.add(findIngredientByName(ingredientsList, "Milk"));

        Drink matchaLatte = new Drink(103, "Matcha Latte", 5.00, true, false, 0.0, "Small", 0.75, 1.0, "Cold",
                matchaLatteIngredients, new ArrayList<>());

        // Drink 4
        ArrayList<Ingredient> brownSugarTeaIngredients = new ArrayList<>();
        brownSugarTeaIngredients.add(findIngredientByName(ingredientsList, "Black Tea"));
        brownSugarTeaIngredients.add(findIngredientByName(ingredientsList, "Milk"));
        brownSugarTeaIngredients.add(findIngredientByName(ingredientsList, "Brown Sugar"));

        Drink brownSugarMilkTea = new Drink(104, "Brown Sugar Milk Tea", 5.25, true, false, 0.0, "Small", 1.0, 1.0,
                "Cold", brownSugarTeaIngredients, new ArrayList<>());

        // Drink 5
        ArrayList<Ingredient> strawberryGreenTeaIngredients = new ArrayList<>();
        strawberryGreenTeaIngredients.add(findIngredientByName(ingredientsList, "Green Tea"));
        strawberryGreenTeaIngredients.add(findIngredientByName(ingredientsList, "Strawberry Syrup"));

        Drink strawberryGreenTea = new Drink(105, "Strawberry Green Tea", 4.75, true, false, 0.0, "Small", 0.75, 1.0,
                "Cold", strawberryGreenTeaIngredients, new ArrayList<>());

        // Drink 6
        ArrayList<Ingredient> mangoGreenTeaIngredients = new ArrayList<>();
        mangoGreenTeaIngredients.add(findIngredientByName(ingredientsList, "Green Tea"));
        mangoGreenTeaIngredients.add(findIngredientByName(ingredientsList, "Mango Syrup"));

        Drink mangoGreenTea = new Drink(106, "Mango Green Tea", 4.75, true, false, 0.0, "Small", 0.75, 1.0,
                "Cold", mangoGreenTeaIngredients, new ArrayList<>());

        // Drink 7
        ArrayList<Ingredient> vietnameseCoffeeIngredients = new ArrayList<>();
        vietnameseCoffeeIngredients.add(findIngredientByName(ingredientsList, "Espresso"));
        vietnameseCoffeeIngredients.add(findIngredientByName(ingredientsList, "Condensed Milk"));

        Drink vietnameseCoffee = new Drink(107, "Vietnamese Coffee", 4.50, true, false, 0.0, "Small", 1.0, 0.50,
                "Cold", vietnameseCoffeeIngredients, new ArrayList<>());

        // Drink 8
        ArrayList<Ingredient> cafeLatteIngredients = new ArrayList<>();
        cafeLatteIngredients.add(findIngredientByName(ingredientsList, "Espresso"));
        cafeLatteIngredients.add(findIngredientByName(ingredientsList, "Milk"));

        Drink cafeLatte = new Drink(108, "Cafe Latte", 4.25, true, false, 0.0, "Small", 0.0, 0.0,
                "Hot", cafeLatteIngredients, new ArrayList<>());

        // Drink 9
        ArrayList<Ingredient> mochaIngredients = new ArrayList<>();
        mochaIngredients.add(findIngredientByName(ingredientsList, "Espresso"));
        mochaIngredients.add(findIngredientByName(ingredientsList, "Milk"));
        mochaIngredients.add(findIngredientByName(ingredientsList, "Chocolate"));

        Drink mocha = new Drink(109, "Mocha", 4.75, true, false, 0.0, "Small", 0.50, 0.0,
                "Hot", mochaIngredients, new ArrayList<>());

        // Drink 10
        ArrayList<Ingredient> americanoIngredients = new ArrayList<>();
        americanoIngredients.add(findIngredientByName(ingredientsList, "Espresso"));
        americanoIngredients.add(findIngredientByName(ingredientsList, "Water"));

        Drink americano = new Drink(110, "Americano", 3.25, true, false, 0.0, "Small", 0.0, 0.0,
                "Hot", americanoIngredients, new ArrayList<>());

        menu.addItem(classicMilkTea);
        menu.addItem(thaiMilkTea);
        menu.addItem(matchaLatte);
        menu.addItem(brownSugarMilkTea);
        menu.addItem(strawberryGreenTea);
        menu.addItem(mangoGreenTea);
        menu.addItem(vietnameseCoffee);
        menu.addItem(cafeLatte);
        menu.addItem(mocha);
        menu.addItem(americano);
    }

    private static Ingredient findIngredientByName(ArrayList<Ingredient> ingredientsList, String name) {
        for (Ingredient ingredient : ingredientsList) {
            String ingredientName = ingredient.getName().trim();
            if (ingredientName.equalsIgnoreCase(name)) {
                return ingredient;
            }
        }

        throw new InvalidIngredientException(name + " was not found");
    }

    // TODO: createOrder()
    private static void createOrder() {
        System.out.println();
        System.out.println("===============================");
        System.out.println("         Create Order");
        System.out.println("===============================");

        myCafe.getMenu().displayAvailableItems();

        System.out.print("\nEnter Item ID: ");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("EXIT")) {
            System.out.println("Program exited...");
            System.exit(0);
        }

        try {
            int itemID = Integer.parseInt(input);

            MenuItem menuItem = myCafe.getMenu().findItemByID(itemID);

            if (menuItem == null) {
                System.out.println("No item with ID: " + itemID);
                return;
            }

            System.out.println();
            System.out.println("Selected: " + menuItem.getName());
            System.out.printf("Price: $%.2f\n", menuItem.calcPrice());

            if (!menuItem.canServe(myCafe.getInventory())) {
                System.out.println("Sorry, item out of stock...");
                return;
            }

            MenuItem selectedItem = menuItem.customerCopy();

        } catch (NumberFormatException e) {
            System.out.println("Invalid Item ID. Please re-enter!");
        }
    }

    // TODO: manageOrder()
    private static void manageOrder() {
        System.out.println("Manage Order");
    }

    private static void viewMenu() {
        System.out.println();
        System.out.println("===============================");
        System.out.println("           Cafe Menu");
        System.out.println("===============================");

        myCafe.getMenu().displayAvailableItems();

        System.out.println("===============================");
        System.out.println();
    }

    // TODO: manageMenu()
    private static void manageMenu() {
        System.out.println("Manage Menu");
    }

    // TODO: manageInventory()
    private static void manageInventory() {
        System.out.println("Manage Inventory");
    }

    // TODO: cafeSummary()
    private static void cafeSummary() {
        System.out.println("Cafe Summary");
    }

    public static void main(String[] args) {

        initializeCafe();

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
