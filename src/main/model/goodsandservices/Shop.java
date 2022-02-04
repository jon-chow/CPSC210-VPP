package model.goodsandservices;

import model.Player;

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
        if (!checkIsInShop(item)) {
            ArrayList<Item> newShopItems = getShopItems();
            ArrayList<Integer> newPriceOfItems = getPriceOfItems();
            ArrayList<Integer> newQuantityInStock = getQuantityInStock();

            newShopItems.add(item);
            newPriceOfItems.add(price);
            newQuantityInStock.add(quantity);

            setShopItems(newShopItems);
            setPriceOfItems(newPriceOfItems);
            setQuantityInStock(newQuantityInStock);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes item from shopItems,
    //          removes its price from priceOfItems, and
    //          removes its quantity from quantityInStock
    public void removeShopItem(Item item) {
        if (checkIsInShop(item)) {
            ArrayList<Item> newShopItems = getShopItems();
            ArrayList<Integer> newPriceOfItems = getPriceOfItems();
            ArrayList<Integer> newQuantityInStock = getQuantityInStock();
            int index = shopItems.indexOf(item);

            newShopItems.remove(index);
            newPriceOfItems.remove(index);
            newQuantityInStock.remove(index);

            setShopItems(newShopItems);
            setPriceOfItems(newPriceOfItems);
            setQuantityInStock(newQuantityInStock);
        }
    }

    // MODIFIES: this, Player
    // REQUIRES: quantity > 0
    // EFFECTS:  sells item to player,
    //           decreasing its value in quantityInStock by quantity.
    //           Also, removes item if its quantityInStock == 0
    public void sellItemTo(Item item, int quantity, Player player) {
        if (checkIsInShop(item) && player.buyItemFrom(item, quantity, this)) {
            changeItemQuantity(item, -quantity);
        }
    }

    // MODIFIES: this
    // EFFECTS:  changes the price of the item in priceOfItems
    public void setItemPrice(Item item, int price) {
        if (checkIsInShop(item)) {
            ArrayList<Integer> newPrices = getPriceOfItems();
            int index = shopItems.indexOf(item);

            newPrices.set(index, price);
            setPriceOfItems(newPrices);
        }
    }

    // MODIFIES: this
    // EFFECTS:  adds value to the quantity of item in quantityInStock
    public void changeItemQuantity(Item item, int value) {
        if (checkIsInShop(item)) {
            ArrayList<Integer> newStocks = getQuantityInStock();
            int index = shopItems.indexOf(item);
            int currentlyStocked = newStocks.get(index);
            int newStocked = currentlyStocked + value;

            newStocks.set(index, newStocked);
            setQuantityInStock(newStocks);
        }
    }

    // MODIFIES: this
    // REQUIRES: quantity > 0
    // EFFECTS:  restocks all items by quantity
    public void restockAll(int quantity) {
        for (Item item : shopItems) {
            changeItemQuantity(item, quantity);
        }
    }

    // EFFECTS: returns true if item exists in shopItems
    public boolean checkIsInShop(Item item) {
        return shopItems.contains(item);
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
