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

    // DONE: createOrder()
    private static void createOrder() {
        int orderID = myCafe.getOrders().size() + 1;
        Order currentOrder = new Order(orderID);

        boolean ordering = true;

        while (ordering) {
            System.out.println();
            System.out.println("===============================");
            System.out.println("          Order #" + orderID);
            System.out.println("===============================");
            System.out.println("1. Add Item");
            System.out.println("2. View Current Order");
            System.out.println("3. Remove Item");
            System.out.println("4. Submit Order");
            System.out.println("5. Cancel Order");

            System.out.print("\nSelect option (1 - 5): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("Program exited...");
                System.exit(0);
            }

            switch (input) {
                case "1":
                    addItemToOrder(currentOrder);
                    break;

                case "2":
                    viewCurrentOrder(currentOrder);
                    break;

                case "3":
                    removeItemFromOrder(currentOrder);
                    break;

                case "4":
                    if (submitCurrentOrder(currentOrder)) {
                        ordering = false;
                    }
                    break;

                case "5":
                    currentOrder.cancelOrder();
                    System.out.println("Order cancelled...");
                    ordering = false;
                    break;

                default:
                    System.out.println("Invalid option! Please choose options 1 - 5");
            }
        }
    }

    // createOrder() helpers
    private static void addItemToOrder(Order currOrder) {
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

            if (selectedItem instanceof Food) {
                customizeFood((Food) selectedItem);
            } else if (selectedItem instanceof Drink) {
                customizeDrink((Drink) selectedItem);
            }

            currOrder.addItem(selectedItem);

            System.out.println(selectedItem.getName() + " added to Order #" + currOrder.getOrderID());

        } catch (NumberFormatException e) {
            System.out.println("Invalid Item ID. Please re-enter!");
        }
    }

    private static void viewCurrentOrder(Order currOrder) {
        System.out.println();
        System.out.println("===============================");
        System.out.println("          Order #" + currOrder.getOrderID());
        System.out.println("===============================");

        if (currOrder.getItems().isEmpty()) {
            System.out.println("Order is currently empty.");
            return;
        }

        int itemNum = 1;
        for (MenuItem item : currOrder.getItems()) {
            System.out.printf("%d. %-25s $%.2f\n", itemNum, item.getName(), item.calcPriceAfterDiscount());
            itemNum++;
        }

        System.out.println("-------------------------------");

        System.out.printf("Subtotal:        $%.2f\n", currOrder.calcSubtotal());
        System.out.printf("Order Discount: -$%.2f\n", currOrder.calcDiscount());
        System.out.printf("Tax:             $%.2f\n", currOrder.calcTax());
        System.out.printf("Total:           $%.2f\n", currOrder.calcTotal());
        System.out.println("===============================");
    }

    private static void removeItemFromOrder(Order currOrder) {
        if (currOrder.getItems().isEmpty()) {
            System.out.println("Order is currently empty.");
            return;
        }

        System.out.println("\nCurrent Items:");

        for (int i = 0; i < currOrder.getItems().size(); i++) {
            MenuItem item = currOrder.getItems().get(i);

            System.out.printf("%d. %s - $%.2f%n", i + 1, item.getName(), item.calcPriceAfterDiscount());
        }

        System.out.print("\nChoose item to remove: ");
        String input = scanner.nextLine().trim();

        try {
            int choice = Integer.parseInt(input);

            if (choice < 1 || choice > currOrder.getItems().size()) {
                System.out.println("Invalid item!");
                return;
            }

            MenuItem item = currOrder.getItems().get(choice - 1);

            currOrder.removeItem(item);

            System.out.println(item.getName() + " removed from order.");

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }

    private static boolean submitCurrentOrder(Order currOrder) {
        if (currOrder.getItems().isEmpty()) {
            System.out.println("Cannot submit an empty order!");
            return false;
        }

        try {
            currOrder.submitOrder(myCafe.getInventory());
            myCafe.submitOrder(currOrder);

            System.out.println();
            System.out.println("===============================");
            System.out.println("      Order Submitted!");
            System.out.println("===============================");

            viewCurrentOrder(currOrder);

            return true;
        } catch (IllegalStateException e) {
            System.out.println("Unable to submit order: " + e.getMessage());
        }

        return false;
    }

    // addItemToOrder() helpers
    private static void customizeFood(Food currFood) {
        boolean customizing = true;

        while (customizing) {
            System.out.println();
            System.out.println("===============================");
            System.out.println("        Customize Food");
            System.out.println("===============================");
            System.out.println("Food: " + currFood.getName());
            System.out.println("Size: " + currFood.getSize());
            System.out.println("Spice Level: " + currFood.getSpiceLvl());
            System.out.printf("Price: $%.2f\n", currFood.calcPrice());

            System.out.println();
            System.out.println("1. Add Extra Ingredient");
            System.out.println("2. Remove Ingredient");
            System.out.println("3. Substitute Ingredient");
            System.out.println("4. Change Spice Level");
            System.out.println("5. Add Special Request");
            System.out.println("6. Finish Customization");

            System.out.print("\nSelect option (1 - 6): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("Program exited...");
                System.exit(0);
            }

            switch (input) {
                case "1":
                    addExtraIngredient(currFood);
                    break;

                case "2":
                    removeIngredient(currFood);
                    break;

                case "3":
                    substituteIngredient(currFood);
                    break;

                case "4":
                    changeSpiceLvl(currFood);
                    break;

                case "5":
                    addFoodSpecialRequest(currFood);
                    break;

                case "6":
                    customizing = false;
                    break;

                default:
                    System.out.println("Invalid option! Please choose 1 - 6");
            }

        }
    }

    private static void customizeDrink(Drink currDrink) {
        boolean customizing = true;

        while (customizing) {
            System.out.println();
            System.out.println("===============================");
            System.out.println("       Customize Drink");
            System.out.println("===============================");
            System.out.println("Drink: " + currDrink.getName());
            System.out.println("Size: " + currDrink.getSize());
            System.out.println("Sweetness: " + (int) (currDrink.getSweetnessLvl() * 100) + "%");
            System.out.println("Ice Level: " + (int) (currDrink.getIceLvl() * 100) + "%");
            System.out.println("Temperature: " + currDrink.getTemperature());
            System.out.printf("Price: $%.2f\n", currDrink.calcPrice());

            System.out.println();
            System.out.println("1. Change Size");
            System.out.println("2. Adjust Sweetness");
            System.out.println("3. Adjust Ice Level");
            System.out.println("4. Change Temperature");
            System.out.println("5. Add Topping");
            System.out.println("6. Remove Topping");
            System.out.println("7. Finish Customization");

            System.out.print("\nSelect Option (1 - 7): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("Program exited...");
                System.exit(0);
            }

            switch (input) {
                case "1":
                    changeDrinkSize(currDrink);
                    break;

                case "2":
                    changeSweetnessLvl(currDrink);
                    break;

                case "3":
                    changeIceLvl(currDrink);
                    break;

                case "4":
                    changeDrinkTemperature(currDrink);
                    break;

                case "5":
                    addDrinkTopping(currDrink);
                    break;

                case "6":
                    removeDrinkTopping(currDrink);
                    break;

                case "7":
                    customizing = false;
                    break;

                default:
                    System.out.println("Invalid option! Please choose 1 - 7");
            }
        }
    }

    // customizeDrink() helpers
    private static void changeDrinkSize(Drink currDrink) {
        System.out.println();
        System.out.println("1. Small");
        System.out.println("2. Medium (+$1.00)");
        System.out.println("3. Large (+$2.00)");

        System.out.print("Choose size: ");
        String input = scanner.nextLine().trim();

        switch (input) {
            case "1":
                currDrink.setSize("Small");
                break;

            case "2":
                currDrink.setSize("Medium");
                break;

            case "3":
                currDrink.setSize("Large");
                break;

            default:
                System.out.println("Invalid size!");
                return;
        }

        System.out.println("Drink Size Changed to " + currDrink.getSize());
    }

    private static void changeSweetnessLvl(Drink currDrink) {
        System.out.println();
        System.out.println("1. 0%");
        System.out.println("2. 25%");
        System.out.println("3. 50%");
        System.out.println("4. 75%");
        System.out.println("5. 100%");

        System.out.print("\nChoose sweetness level: ");
        String input = scanner.nextLine().trim();

        switch (input) {
            case "1":
                currDrink.adjustSweetness(0.0);
                break;

            case "2":
                currDrink.adjustSweetness(0.25);
                break;

            case "3":
                currDrink.adjustSweetness(0.50);
                break;

            case "4":
                currDrink.adjustSweetness(0.75);
                break;

            case "5":
                currDrink.adjustSweetness(1.0);
                break;

            default:
                System.out.println("Invalid sweetness level!");
                return;
        }

        System.out.println("Sweetness level changed to " + (int) (currDrink.getSweetnessLvl() * 100) + "%");
    }

    private static void changeIceLvl(Drink currDrink) {
        System.out.println();
        System.out.println("1. 0%");
        System.out.println("2. 25%");
        System.out.println("3. 50%");
        System.out.println("4. 75%");
        System.out.println("5. 100%");

        System.out.print("\nChoose ice level: ");
        String input = scanner.nextLine().trim();

        switch (input) {
            case "1":
                currDrink.setIceLvl(0.0);
                break;

            case "2":
                currDrink.setIceLvl(0.25);
                break;

            case "3":
                currDrink.setIceLvl(0.50);
                break;

            case "4":
                currDrink.setIceLvl(0.75);
                break;

            case "5":
                currDrink.setIceLvl(1.0);
                break;

            default:
                System.out.println("Invalid ice level!");
                return;
        }

        System.out.println("Ice level changed to " + (int) (currDrink.getIceLvl() * 100) + "%");
    }

    private static void changeDrinkTemperature(Drink currDrink) {
        System.out.println();
        System.out.println("1. Hot");
        System.out.println("2. Cold");

        System.out.print("\nChoose temperature: ");
        String input = scanner.nextLine().trim();

        switch (input) {
            case "1":
                currDrink.setTemperature("Hot");
                break;

            case "2":
                currDrink.setTemperature("Cold");
                break;

            default:
                System.out.println("Invalid temperature!");
                return;
        }
    }

    private static void addDrinkTopping(Drink currDrink) {
        System.out.println();
        System.out.println("Available Toppings:");
        System.out.println("1. Boba (+$0.75)");
        System.out.println("2. Grass Jelly (+$0.75)");
        System.out.println("3. Pudding (+$0.75)");
        System.out.println("4. Whipped Cream (+$0.50)");
        System.out.println("5. Extra Espresso (+$1.00)");

        System.out.print("\nChoose topping: ");
        String input = scanner.nextLine().trim();

        Ingredient topping;

        switch (input) {
            case "1":
                topping = findIngredientByName(myCafe.getInventory().getIngredients(), "Boba");
                break;

            case "2":
                topping = findIngredientByName(myCafe.getInventory().getIngredients(), "Grass Jelly");
                break;

            case "3":
                topping = findIngredientByName(myCafe.getInventory().getIngredients(), "Pudding");
                break;

            case "4":
                topping = findIngredientByName(myCafe.getInventory().getIngredients(), "Whipped Cream");
                break;

            case "5":
                topping = findIngredientByName(myCafe.getInventory().getIngredients(), "Espresso");
                break;

            default:
                System.out.println("Invalid topping!");
                return;
        }

        currDrink.addTopping(topping);

        System.out.println(topping.getName() + " added");
        System.out.printf("New Price: $%.2f\n", currDrink.calcPrice());
    }

    private static void removeDrinkTopping(Drink currDrink) {
        ArrayList<Ingredient> currToppings = currDrink.getToppings();

        if (currToppings.isEmpty()) {
            System.out.println("This drink has no toppings!");
            return;
        }

        System.out.println("\nCurrent Toppings: ");
        for (int i = 0; i < currToppings.size(); i++) {
            System.out.println((i + 1) + ". " + currToppings.get(i).getName());
        }

        System.out.print("\nChoose topping to remove: ");
        String input = scanner.nextLine().trim();

        try {
            int choice = Integer.parseInt(input);
            if (choice < 1 || choice > currToppings.size()) {
                System.out.println("Invalid topping!");
                return;
            }

            Ingredient topping = currToppings.get(choice - 1);
            currDrink.removeTopping(topping);

            System.out.println(topping.getName() + " removed...");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }

    // customizeFood() helpers
    private static void addExtraIngredient(Food currFood) {
        System.out.println();
        System.out.println("Available Extra Ingredients:");
        System.out.println("1. Egg (+$1.25)");
        System.out.println("2. Cheddar Cheese (+$0.75)");
        System.out.println("3. Bacon (+$1.50)");
        System.out.println("4. Chicken (+$2.00)");
        System.out.println("5. Mozzarella (+$0.75)");
        System.out.println("6. Tomato (+$0.50)");
        System.out.println("7. Avocado (+$1.50)");

        System.out.print("\nChoose extra ingredient: ");
        String input = scanner.nextLine().trim();

        Ingredient extra;

        switch (input) {
            case "1":
                extra = findIngredientByName(myCafe.getInventory().getIngredients(), "Egg");
                break;

            case "2":
                extra = findIngredientByName(myCafe.getInventory().getIngredients(), "Cheddar Cheese");
                break;

            case "3":
                extra = findIngredientByName(myCafe.getInventory().getIngredients(), "Bacon");
                break;

            case "4":
                extra = findIngredientByName(myCafe.getInventory().getIngredients(), "Chicken");
                break;

            case "5":
                extra = findIngredientByName(myCafe.getInventory().getIngredients(), "Mozzarella");
                break;

            case "6":
                extra = findIngredientByName(myCafe.getInventory().getIngredients(), "Tomato");
                break;

            case "7":
                extra = findIngredientByName(myCafe.getInventory().getIngredients(), "Avocado");
                break;

            default:
                System.out.println("Invalid ingredient!");
                return;
        }

        currFood.addExtraIngredient(extra);
        System.out.println(extra.getName() + " added...");

        System.out.printf("New Price: $%.2f\n", currFood.calcPrice());
    }

    private static void removeIngredient(Food currFood) {
        ArrayList<Ingredient> currIngredients = currFood.getCurrentIngredients();

        if (currIngredients.isEmpty()) {
            System.out.println("No ingredients to remove");
            return;
        }

        System.out.println("\nCurrent Ingredients: ");
        for (int i = 0; i < currIngredients.size(); i++) {
            System.out.println((i + 1) + ". " + currIngredients.get(i).getName());
        }

        System.out.print("\nChoose ingredient to remove: ");
        String input = scanner.nextLine().trim();

        try {
            int choice = Integer.parseInt(input);

            if (choice < 1 || choice > currIngredients.size()) {
                System.out.println("Invalid ingredient!");
                return;
            }

            Ingredient ingredient = currIngredients.get(choice - 1);

            currFood.removeIngredient(ingredient);
            System.out.println(ingredient.getName() + " removed...");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }

    private static void substituteIngredient(Food currFood) {
        ArrayList<Ingredient> currIngredients = currFood.getCurrentIngredients();

        if (currIngredients.isEmpty()) {
            System.out.println("No ingredients to substitute");
            return;
        }

        System.out.println("\nCurrent Ingredients: ");
        for (int i = 0; i < currIngredients.size(); i++) {
            System.out.println((i + 1) + ". " + currIngredients.get(i).getName());
        }

        System.out.print("\nChoose ingredient to substitute");
        String input = scanner.nextLine().trim();

        try {
            int choice = Integer.parseInt(input);

            if (choice < 1 || choice > currIngredients.size()) {
                System.out.println("Invalid ingredient!");
                return;
            }

            Ingredient original = currIngredients.get(choice - 1);

            System.out.println();
            System.out.println("Replacement Ingredients:");
            System.out.println("1. Egg");
            System.out.println("2. Cheddar Cheese");
            System.out.println("3. Bacon");
            System.out.println("4. Chicken");
            System.out.println("5. Mozzarella");
            System.out.println("6. Tomato");
            System.out.println("7. Avocado");

            System.out.print("\nChoose substitution: ");
            String replacementInput = scanner.nextLine().trim();

            Ingredient substitution;

            switch (replacementInput) {
                case "1":
                    substitution = findIngredientByName(myCafe.getInventory().getIngredients(), "Egg");
                    break;

                case "2":
                    substitution = findIngredientByName(myCafe.getInventory().getIngredients(), "Cheddar Cheese");
                    break;

                case "3":
                    substitution = findIngredientByName(myCafe.getInventory().getIngredients(), "Bacon");
                    break;

                case "4":
                    substitution = findIngredientByName(myCafe.getInventory().getIngredients(), "Chicken");
                    break;

                case "5":
                    substitution = findIngredientByName(myCafe.getInventory().getIngredients(), "Mozzarella");
                    break;

                case "6":
                    substitution = findIngredientByName(myCafe.getInventory().getIngredients(), "Tomato");
                    break;

                case "7":
                    substitution = findIngredientByName(myCafe.getInventory().getIngredients(), "Avocado");
                    break;

                default:
                    System.out.println("Invalid substitution!");
                    return;
            }

            currFood.substituteIngredient(original, substitution);

            System.out.println(original.getName() + " replaced with " + substitution.getName());

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }

    private static void changeSpiceLvl(Food currFood) {
        System.out.println();
        System.out.println("1. None");
        System.out.println("2. Mild");
        System.out.println("3. Medium");
        System.out.println("4. Hot");

        System.out.print("Choose spice level: ");
        String input = scanner.nextLine().trim();

        switch (input) {
            case "1":
                currFood.setSpiceLvl("None");
                break;

            case "2":
                currFood.setSpiceLvl("Mild");
                break;

            case "3":
                currFood.setSpiceLvl("Medium");
                break;

            case "4":
                currFood.setSpiceLvl("Hot");
                break;

            default:
                System.out.println("Invalid spice level!");
                return;
        }

        System.out.println("Spice level changed to " + currFood.getSpiceLvl());
    }

    private static void addFoodSpecialRequest(Food currFood) {
        System.out.print("Enter special request: ");
        String request = scanner.nextLine().trim();

        if (request.equalsIgnoreCase("EXIT")) {
            System.out.println("Program exited...");
            System.exit(0);
        }

        currFood.setSpecialRequest(request);
        System.out.println("Special request added...");
    }

    //-------------------------------------------------//
    // DONE: manageOrder()
    private static void manageOrder() {
        boolean managing = true;

        while (managing) {
            System.out.println();
            System.out.println("===============================");
            System.out.println("         Manage Orders");
            System.out.println("===============================");
            System.out.println("1. View All Orders");
            System.out.println("2. Find Order by ID");
            System.out.println("3. Update Order Status");
            System.out.println("4. Cancel Order");
            System.out.println("5. Return to Main Menu");

            System.out.print("\nSelect option (1 - 5): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("Program exited...");
                System.exit(0);
            }

            switch (input) {
                case "1":
                    viewAllOrders();
                    break;

                case "2":
                    findOrderByID();
                    break;

                case "3":
                    updateOrderStatus();
                    break;

                case "4":
                    cancelExistingOrder();
                    break;

                case "5":
                    managing = false;
                    break;

                default:
                    System.out.println("Invalid option! Please choose 1 - 5");
            }
        }
    }

    // manageOrder() helpers
    private static void viewAllOrders() {
        ArrayList<Order> orders = myCafe.getOrders();

        if (orders.isEmpty()) {
            System.out.println("\nNo orders found!");
            return;
        }

        System.out.println();
        System.out.println("==============================================");
        System.out.println("                  All Orders");
        System.out.println("==============================================");

        for (Order order : orders) {
            System.out.printf("Order #%d | Status: %-10s | Items: %d | Total: $%.2f%n", order.getOrderID(),
                    order.getStatus(), order.getItems().size(), order.calcTotal());
        }

        System.out.println("==============================================");
    }

    private static void findOrderByID() {
        if (myCafe.getOrders().isEmpty()) {
            System.out.println("\nNo orders found!");
            return;
        }

        System.out.print("\nEnter Order ID: ");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("EXIT")) {
            System.out.println("Program exited...");
            System.exit(0);
        }

        try {
            int orderID = Integer.parseInt(input);

            Order order = findOrder(orderID);
            if (order == null) {
                System.out.println("Order #" + orderID + " not found!");
                return;
            }

            viewOrderDetails(order);

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid Order ID!");
        }
    }

    private static void updateOrderStatus() {
        if (myCafe.getOrders().isEmpty()) {
            System.out.println("\nNo orders found.");
            return;
        }

        System.out.print("\nEnter Order ID: ");
        String input = scanner.nextLine().trim();

        try {
            int orderID = Integer.parseInt(input);

            Order order = findOrder(orderID);

            if (order == null) {
                System.out.println("Order #" + orderID + " not found.");
                return;
            }

            if (order.getStatus().equalsIgnoreCase("Cancelled")) {
                System.out.println("Cancelled orders cannot be updated.");
                return;
            }

            System.out.println();
            System.out.println("1. Submitted");
            System.out.println("2. Preparing");
            System.out.println("3. Ready");
            System.out.println("4. Completed");

            System.out.print("\nSelect new status: ");
            String statusInput = scanner.nextLine().trim();

            switch (statusInput) {
                case "1":
                    order.updateStatus("Submitted");
                    break;

                case "2":
                    order.updateStatus("Preparing");
                    break;

                case "3":
                    order.updateStatus("Ready");
                    break;

                case "4":
                    order.updateStatus("Completed");
                    break;

                default:
                    System.out.println("Invalid status!");
                    return;
            }

            System.out.println("Order #" + orderID + " updated to " + order.getStatus() + ".");

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid Order ID!");
        }
    }

    private static void cancelExistingOrder() {
        if (myCafe.getOrders().isEmpty()) {
            System.out.println("\nNo orders found.");
            return;
        }

        System.out.print("\nEnter Order ID: ");
        String input = scanner.nextLine().trim();

        try {
            int orderID = Integer.parseInt(input);

            Order order = findOrder(orderID);

            if (order == null) {
                System.out.println("Order #" + orderID + " not found.");
                return;
            }

            if (order.getStatus().equalsIgnoreCase("Cancelled")) {
                System.out.println("Order #" + orderID + " is already cancelled.");
                return;
            }

            if (order.getStatus().equalsIgnoreCase("Completed")) {
                System.out.println("Completed orders cannot be cancelled.");
                return;
            }

            order.cancelOrder();

            System.out.println("Order #" + orderID + " cancelled.");

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid Order ID!");
        }
    }

    // findOrderByID() helper
    private static Order findOrder(int orderID) {
        for (Order order : myCafe.getOrders()) {
            if (order.getOrderID() == orderID) {
                return order;
            }
        }
        return null;
    }

    private static void viewOrderDetails(Order order) {
        System.out.println();
        System.out.println("===============================");
        System.out.println("          Order #" + order.getOrderID());
        System.out.println("===============================");
        System.out.println("Status: " + order.getStatus());
        System.out.println();

        int itemNum = 1;

        for (MenuItem item : order.getItems()) {
            System.out.printf( "%d. %-25s $%.2f%n", itemNum, item.getName(), item.calcPriceAfterDiscount());
            itemNum++;
        }

        System.out.println("-------------------------------");

        System.out.printf("Subtotal:        $%.2f%n", order.calcSubtotal());
        System.out.printf("Order Discount: -$%.2f%n", order.calcDiscount());
        System.out.printf("Tax:             $%.2f%n", order.calcTax());
        System.out.printf("Total:           $%.2f%n", order.calcTotal());
        System.out.println("===============================");
    }
    // -------------------------------------------------//
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
