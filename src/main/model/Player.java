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
        if (!(inventory.contains(item))) {
            inventory.add(item);
            inventoryQuantity.add(quantity);
        } else {
            int itemIndex = inventory.indexOf(item);
            inventoryQuantity.set(itemIndex, inventoryQuantity.get(itemIndex) + quantity);
        }
    }

    // MODIFIES: this
    // REQUIRES: item exists in inventory, and
    //           0 < quantity <= count of item in inventory
    // EFFECTS: removes a quantity number of item from the inventory
    //          removes item completely if inventory's quantity reaches 0
    public void removeFromInventory(Item item, int quantity) {
        int itemIndex = inventory.indexOf(item);
        int newQuantity = inventoryQuantity.get(itemIndex) - quantity;

        if (newQuantity != 0) {
            inventoryQuantity.set(itemIndex, newQuantity);
        } else {
            inventoryQuantity.remove(itemIndex);
            inventory.remove(itemIndex);
        }
    }

    // MODIFIES: this, Pet
    // REQUIRES: item exists in inventory
    // EFFECTS: removes one count of item from inventory and
    //          gives item for pet to consume
    public void giveItemTo(Item item, Pet pet) {
        removeFromInventory(item, 1);
        pet.consumeItem(item);
    }

    // MODIFIES: this, Shop
    // REQUIRES: item exists in shop, and quantity >= 0
    // EFFECTS:  buys item from shop, spending money and
    //           places item in inventory, while decreasing shop's stock
    //           returns true if item was bought successfully
    public boolean buyItemFrom(Item item, int quantity, Shop shop) {
        int price = shop.getItemPrice(item) * quantity;

        if (money >= price) {
            money -= price;
            addToInventory(item, quantity);
            shop.changeItemQuantity(item, -quantity);
            return true;
        } else {
            return false;
        }
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
