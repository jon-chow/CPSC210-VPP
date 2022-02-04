package model;

import java.util.ArrayList;

import model.goodsandservices.Item;
import model.goodsandservices.Shop;
import model.pets.Pet;

public class Player {
    private int money;

    private String playerName;

    private ArrayList<Item> inventory;

    private ArrayList<Integer> inventoryQuantity;

    // EFFECTS: constructs a default player
    public Player() {
        this.money = 5000;
        this.playerName = "Player";
        this.inventory = new ArrayList<>();
        this.inventoryQuantity = new ArrayList<>();
    }

    // MODIFIES: this
    // REQUIRES: quantity > 0
    // EFFECTS: adds a quantity number of item to the inventory
    public void addToInventory(Item item, int quantity) {

    }

    // MODIFIES: this
    // REQUIRES: item exists in inventory, and
    //           0 < quantity <= count of item in inventory
    // EFFECTS: removes a quantity number of item from the inventory
    public void removeFromInventory(Item item, int quantity) {

    }

    // MODIFIES: this, Pet
    // REQUIRES: item exists in inventory
    // EFFECTS: removes one count of item from inventory and
    //          gives item to pet, affecting its care levels
    public void giveItemTo(Item item, Pet pet) {

    }

    // MODIFIES: this, Shop
    // REQUIRES: item exists in shop,
    //           quantity >= 0, and
    // EFFECTS:  buys item from shop, spending money and
    //           places item in inventory
    //           returns true if item was bought successfully
    public boolean buyItemFrom(Item item, int quantity, Shop shop) {
        return true;
    }

    // EFFECTS: returns true if money >= cost
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
