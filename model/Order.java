package model;

import java.util.ArrayList;

import exceptions.MaximumInstanceException;
import management.Inventory;
import management.SystemConstant;

public class Order {

    // Order fields
    private double tax;
    private double total;
    private double subtotal;
    private int orderID;
    private String status;
    private ArrayList<MenuItem> items;

    private static int instanceCount = 0;

    // Order constructor
    public Order(int orderID) {
        if (instanceCount > SystemConstant.MAXIMUM_INSTANCES) {
            throw new MaximumInstanceException(
                    "Cannot have more than " + SystemConstant.MAXIMUM_INSTANCES + " Order objects");
        }

        this.orderID = orderID;
        this.items = new ArrayList<>();
        this.status = "Pending";

        instanceCount++;
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
