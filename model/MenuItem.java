package model;

import exceptions.InvalidNameException;
import exceptions.InvalidNumericValueException;
import interfaces.Discountable;
import management.Inventory;

public abstract class MenuItem implements Discountable{
    private int itemID;
    private String name;
    private double price;
    private boolean inStock;
    private boolean discountable;
    private double discountRate;

    public MenuItem(int itemID, String name, double price, boolean inStock, boolean discountable, double rate) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.discountable = discountable;
        this.discountRate = rate;
    }

    /*-----Abstract methods-----*/
    public abstract double calcPrice();

    public abstract String getCategory();

    public abstract boolean canServe(Inventory inventory);

    public abstract MenuItem customerCopy();

    public abstract void consumeIngredients(Inventory inventory);

    /*-----Interface Methods-----*/
    @Override 
    public boolean isDiscountable() {
        return discountable;
    }

    @Override
    public double calcDiscount() {
        if (!isDiscountable()) {
            return 0.0;
        }
        return calcPrice() * discountRate;
    }

    @Override
    public double calcPriceAfterDiscount() {
        return calcPrice() - calcDiscount();
    }

    /*-----Getters and Setters-----*/
    public int getItemID() {
        return this.itemID;
    }

    public void setItemID(int newID) {
        if (newID <= 0) {
            throw new InvalidNumericValueException("Item ID must be positive");
        }
        this.itemID = newID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        if (newName.isBlank()) {
            throw new InvalidNameException("Name cannot be empty");
        }
        this.name = newName;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double newPrice) {
        if (newPrice < 0) {
            throw new InvalidNumericValueException("Price cannot be negative");
        }
        this.price = newPrice;
    }

    public boolean getInStock() {
        return this.inStock;
    }

    public void setInStock(boolean status) {
        this.inStock = status;
    }

    public void setDiscountable(boolean newStatus) {
        this.discountable = newStatus;
    }

    public double getDiscountRate() {
        return this.discountRate;
    }

    public void setDiscountRate(double rate) {
        if (rate < 0 || rate > 1) {
            throw new InvalidNumericValueException("Discount rate must be between 0 and 1");
        }

        this.discountRate = rate;
    }
}