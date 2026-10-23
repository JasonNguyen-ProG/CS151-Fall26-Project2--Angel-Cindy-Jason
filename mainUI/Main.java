package mainUI;

import java.util.Scanner;
import management.Cafe;

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

            if (userEntry.equals("EXIT")) {
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
