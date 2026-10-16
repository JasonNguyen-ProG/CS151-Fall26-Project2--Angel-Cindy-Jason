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

    public abstract double calcPrice();

    public abstract String getCategory();

    public abstract boolean canServe(Inventory inventory);

    public abstract void customize(Ingredient ingredient);
}