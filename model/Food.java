package model;

import java.util.ArrayList;

import management.Inventory;

public class Food extends MenuItem {
    private String size;
    private String temperature;
    private ArrayList<Ingredient> sauces;
    private String specialNotes;

    public Food(int itemID, String name, double price, boolean inStock, String size, String temp,
            ArrayList<Ingredient> sauces, String specialNotes) {
        super(itemID, name, price, inStock);
        this.size = size;
        this.temperature = temp;
        this.sauces = sauces;
        this.specialNotes = specialNotes;
    }

    public void addSauce(Ingredient sauce) {
        this.sauces.add(sauce);
    }

    public void removeSauce(Ingredient sauce) {
        this.sauces.remove(sauce);
    }

    @Override 
    public double calcPrice() {
        return 0.0;
    }

    @Override 
    public boolean canServe(Inventory inventory) {
        return true;
    }
}
