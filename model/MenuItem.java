package model;

public abstract class MenuItem {
    protected int itemID;
    protected String name;
    protected double price;
    protected boolean inStock;

    public MenuItem(int itemID, String name, double price, boolean inStock) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
    }

    /*-----Abstract methods-----*/
    public abstract double calcPrice();

    public abstract String getCategory();

    public abstract boolean canServe(Inventory inventory);

    public abstract void customize(Ingredient ingredient);

    /*-----Getters and Setters-----*/
    public int getItemID() {
        return this.itemID;
    }

    public void setItemID(int newID) {
        this.itemID = newID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public boolean getInStock() {
        return this.inStock;
    }

    public void setInStock(boolean status) {
        this.inStock = status;
    }
}