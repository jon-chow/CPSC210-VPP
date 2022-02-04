package ui.menus;

import model.goodsandservices.Shop;

public class ShopMenu {
    private static final String SHOP_NAME = "";

    // EFFECTS: initiates a shop
    public static Shop initShop() {
        Shop shop = new Shop(SHOP_NAME);

        return shop;
    }
}
