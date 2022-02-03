package model;

import java.util.ArrayList;

public class Shop {
    private String shopName;

    private ArrayList<Item> shopItems;
    private ArrayList<Integer> priceOfItems;
    private ArrayList<Integer> quantityInStock;

    // EFFECTS: constructs a shop with a shopName
    public Shop(String shopName) {
        this.shopName = shopName;
        this.shopItems = new ArrayList<>();
        this.priceOfItems = new ArrayList<>();
        this.quantityInStock = new ArrayList<>();
    }

    // MODIFIES: this
    // REQUIRES: price >= 0, and
    //           quantity > 0
    // EFFECTS: adds item to shopItems,
    //          adds price to priceOfItems, and
    //          adds quantity to quantityInStock
    public void addShopItem(Item item, int price, int quantity) {

    }

    // MODIFIES: this
    // REQUIRES: item exists in shopItems
    // EFFECTS: removes item from shopItems,
    //          removes its price from priceOfItems, and
    //          removes its quantity from quantityInStock
    public void removeShopItem(Item item) {

    }

    // MODIFIES: this, Player
    // REQUIRES: item exists in shopItems, and
    //           quantity > 0
    // EFFECTS:  sells item to player,
    //           decreasing its value in quantityInStock by quantity.
    //           Also, removes item if its quantityInStock == 0
    public void sellItemTo(Item item, int quantity, Player player) {

    }

    // MODIFIES: this
    // REQUIRES: item exists
    // EFFECTS:  changes the price of the item in priceOfItems
    public void changeItemPrice(Item item, int price) {

    }

    // MODIFIES: this
    // REQUIRES: item exists
    // EFFECTS:  adds value to the quantity of item in quantityInStock
    public void incrementItemQuantity(Item item, int value) {

    }

    // MODIFIES: this
    // REQUIRES: item exists
    // EFFECTS:  subtracts value from the quantity of item in quantityInStock
    public void decrementItemQuantity(Item item, int value) {

    }

    // EFFECTS: returns true if item exists in shopItems
    public boolean checkIsInShop(Item item) {
        return false;
    }

    // GETTERS
    public String getShopName() {
        return shopName;
    }

    public ArrayList<Item> getShopItems() {
        return shopItems;
    }

    public ArrayList<Integer> getPriceOfItems() {
        return priceOfItems;
    }

    public ArrayList<Integer> getQuantityInStock() {
        return quantityInStock;
    }

    // SETTERS
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setShopItems(ArrayList<Item> shopItems) {
        this.shopItems = shopItems;
    }

    public void setPriceOfItems(ArrayList<Integer> priceOfItems) {
        this.priceOfItems = priceOfItems;
    }

    public void setQuantityInStock(ArrayList<Integer> quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
