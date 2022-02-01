package model;

import model.pets.Pet;

import java.util.ArrayList;

public class Player {
    private int money;

    private String playerName;

    private ArrayList<Item> inventory;

    private ArrayList<Integer> inventoryQuantity;

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public Player() {
        this.money = 5000;
        this.playerName = "Player";
        this.inventory = new ArrayList<>();
        this.inventoryQuantity = new ArrayList<>();
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void renamePlayer(String name) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void addToInventory(Item item, int quantity) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void removeFromInventory(Item item, int quantity) {

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
    public boolean checkEnoughMoney(int cost) {
        return false;
    }

    // GETTERS
    public int getMoney() {
        return money;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public ArrayList<Integer> getInventoryQuantity() {
        return inventoryQuantity;
    }

    // SETTERS
    public void setMoney(int money) {
        this.money = money;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public void setInventoryQuantity(ArrayList<Integer> inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

}
