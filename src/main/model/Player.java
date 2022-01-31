package model;

import model.pets.Pet;

import java.util.ArrayList;

public class Player {
    private int money;

    private String playerName;

    private ArrayList<Item> inventory;

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public Player() {
        this.money = 5000;
        this.playerName = "Player";
        this.inventory = new ArrayList<>();
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void renamePlayer(String name) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void addToInventory(Item item) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void removeFromInventory(Item item) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void giveItemTo(Item item, Pet pet) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void buyItemFrom(Item item, Shop shop) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void interactWith(Pet pet) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void cleanUp() {

    }

    // GETTERS
    public int getMoney() {
        return money;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    // SETTERS
    public void setMoney(int money) {
        this.money = money;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

}
