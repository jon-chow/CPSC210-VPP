package ui.menus;

import model.Player;
import model.goodsandservices.Item;
import model.goodsandservices.Shop;

import java.io.IOException;

import static ui.TerminalApp.scanner;
import static ui.configurables.Commands.*;

public class ShopMenu {
    // EFFECTS: initiates a new shop called shopName
    public static Shop initShop(String shopName) throws IOException {
        Shop shop = new Shop(shopName);

        shop.stockWithRandomItems(2, 15);
        shop.stockWithRandomItems(3, 10);

        return shop;
    }

    // EFFECTS: opens the Shop menu of shop for player
    public static void openShopMenu(Shop shop, Player player) {
        System.out.println("\nWelcome to " + shop.getShopName() + ", " + player.getPlayerName() + "!");
        showBuyables(shop);
    }

    // EFFECTS: displays all buyable items from the shop
    //          with their name, type, price, and quantity in stock
    private static void showBuyables(Shop shop) {
        boolean hasExited = false;

        System.out.println("Here's a list of items you can buy at " + shop.getShopName() + ":");
        System.out.println("[ ITEM NAME || ITEM TYPE || PRICE || QUANTITY IN-STOCK ]");
        printItems(shop);

        System.out.println("\nTo buy something, enter in the item name, item type,"
                            + " and the quantity you want to purchase.\nEx. 'chicken food 2'");

        System.out.println("To leave the shop, enter in 'exit'.");

        while (!hasExited) {
            String command = scanner.nextLine();

            if (command != null) {
                switch (command) {
                    case EXIT_MENU_KEY: System.out.println("You have left " + shop.getShopName() + ".");
                        hasExited = true;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static void printItems(Shop shop) {
        for (int i = 0; i < shop.getShopItems().size(); i++) {
            Item item = shop.getShopItems().get(i);
            String itemName = item.getName();
            String itemType = item.getType();
            int price = shop.getPriceOfItems().get(i);
            int quantityInStock = shop.getQuantityInStock().get(i);

            System.out.println("[ " + itemName + " || "
                                    + itemType + " || "
                                    + price + " || "
                                    + quantityInStock + " ]");
        }
    }
}
