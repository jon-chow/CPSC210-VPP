package model;

import java.util.ArrayList;

import model.goodsandservices.Item;
import model.goodsandservices.Shop;
import model.persistence.Writable;
import model.pets.Pet;

import org.json.JSONObject;

import static model.persistence.ConverterJsonArrays.*;

// represents a player
public class Player implements Writable {
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
    // EFFECTS: removes a quantity of item from inventory and
    //          gives that quantity of item for pet to consume
    //          returns true if given successfully
    public boolean giveItemTo(Item item, Pet pet, int quantity) {
        int itemQuantityInInventory = inventoryQuantity.get(inventory.indexOf(item));

        if (itemQuantityInInventory >= quantity) {
            removeFromInventory(item, quantity);

            for (int i = 0; i < quantity; i++) {
                pet.consumeItem(item);
            }

            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this, Shop
    // REQUIRES: item exists in shop, and quantity >= 0
    // EFFECTS:  buys item from shop, spending money and
    //           places item in inventory, while decreasing shop's stock
    //           returns true if item was bought successfully
    public boolean buyItemFrom(Item item, int quantity, Shop shop) {
        int price = shop.getItemPrice(item) * quantity;

        if (money >= price && shop.getItemQuantity(item) >= quantity) {
            money -= price;
            addToInventory(item, quantity);
            shop.changeItemQuantity(item, -quantity);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: converts all player data to a JSONObject and returns it
    @Override
    public JSONObject toJsonObj() {
        JSONObject playerObject = new JSONObject();

        JSONObject inventory = new JSONObject();
        inventory.put("quantities", arrayListIntToJson(inventoryQuantity));
        inventory.put("items", arrayListItemToJson(this.inventory));

        playerObject.put("inventory", inventory);
        playerObject.put("money", money);
        playerObject.put("name", playerName);

        return playerObject;
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
