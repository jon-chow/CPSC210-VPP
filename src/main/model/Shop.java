package model;

import java.util.ArrayList;

public class Shop {
    private String shopName;

    private ArrayList<Item> shopItems;
    private ArrayList<Integer> priceOfItems;
    private ArrayList<Integer> quantityInStock;

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public Shop(String shopName) {
        this.shopName = shopName;
        this.shopItems = new ArrayList<>();
        this.priceOfItems = new ArrayList<>();
        this.quantityInStock = new ArrayList<>();
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void addShopItem(Item item, int price, int quantity) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void removeShopItem(Item item) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void changeItemPrice(Item item, int price) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void incrementItemQuantity(Item item, int value) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void decrementItemQuantity(Item item, int value) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
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
