package ui.menus;

import model.goodsandservices.Item;
import model.goodsandservices.Shop;

import java.io.IOException;

public class ShopMenu {
    // EFFECTS: initiates a new shop called shopName
    public static Shop initShop(String shopName) throws IOException {
        Shop shop = new Shop(shopName);

        shop.stockWithRandomItems(3, 20);

        return shop;
    }
}
