package model;

import java.util.ArrayList;
import management.Inventory;

public class Order {
    // Order fields
    private double tax;
    private double total;
    private double subtotal;
    private int orderID;
    private String status;
    private ArrayList<MenuItem> items;

    // Order constructor
    public Order(int orderID) {
        this.orderID = orderID;
        this.items = new ArrayList<>();
        this.status = "Pending";
    }

    // Order getters
    public int getOrderID() {
        return orderID;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public String getStatus() {
        return status;
    }

    // Order items
    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    // Order prices
    public double calcSubtotal() {
        subtotal = 0.0;
        for (MenuItem item : items) {
            subtotal += item.calcPriceAfterDiscount();
        }
        return subtotal;
    }

    public double calcTax() {
        tax = calcSubtotal() * 0.10; // Tax rate 10%
        return tax;
    }

    public double calcTotal() {
        total = calcSubtotal() + calcTax();
        return total;
    }

    // Order status
    public void submitOrder(Inventory inventory) {
        for (MenuItem item : items) {
            if (!item.canServe(inventory)) {
                throw new IllegalStateException(item.getName() + " cannot be served");
            }
        }
        status = "Submitted";
    }

    public void cancelOrder() {
        status = "Cancelled";
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}
