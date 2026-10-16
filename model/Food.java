package model;

import java.util.ArrayList;

import management.Inventory;

public class Food {
    private String size;
    private String temperature;
    private ArrayList<Ingredient> sauces;
    private String specialNotes;

    public Food(String size, String temp, ArrayList<Ingredient> sauces, String specialNotes) {
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

    public double calcPrice() {
        return 0.0;
    }

    public boolean canServe(Inventory inventory) {
        return true;
    }
}
