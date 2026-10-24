package model;

import exceptions.MaximumInstanceException;
import interfaces.Discountable;
import java.util.ArrayList;
import management.Inventory;
import management.SystemConstant;

public class Order implements Discountable {

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
        if (instanceCount >= SystemConstant.MAXIMUM_INSTANCES) {
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
        return this.orderID;
    }

    public ArrayList<MenuItem> getItems() {
        return this.items;
    }

    public String getStatus() {
        return this.status;
    }

    // Order items
    public void addItem(MenuItem item) {
        this.items.add(item);
    }

    public void removeItem(MenuItem item) {
        this.items.remove(item);
    }

    // Interface methods
    @Override
    public boolean isDiscountable() {
        for (MenuItem item : this.items) {
            if (item.isDiscountable()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double calcDiscount() {
        if (!this.isDiscountable()) {
            return 0.0;
        }
        return this.calcSubtotal() * SystemConstant.ORDER_DISCOUNT_RATE;
    }

    @Override
    public double calcPriceAfterDiscount() {
        return this.calcSubtotal() - this.calcDiscount();
    }

    // Order prices
    public double calcSubtotal() {
        this.subtotal = 0.0;
        for (MenuItem item : items) {
            this.subtotal += item.calcPriceAfterDiscount();
        }
        return this.subtotal;
    }

    public double calcTax() {
        this.tax = this.calcPriceAfterDiscount() * SystemConstant.TAX_RATE;
        return this.tax;
    }

    public double calcTotal() {
        this.total = this.calcPriceAfterDiscount() + this.calcTax();
        return this.total;
    }

    // Order status
    public void submitOrder(Inventory inventory) {
        for (MenuItem item : this.items) {
            if (!item.canServe(inventory)) {
                throw new IllegalStateException(item.getName() + " cannot be served");
            }
        }
        this.status = "Submitted";
    }

    public void cancelOrder() {
        this.status = "Cancelled";
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}
