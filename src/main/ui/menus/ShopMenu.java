package ui.menus;

import model.Player;
import model.goodsandservices.Item;
import model.goodsandservices.Shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static ui.TerminalApp.scanner;
import static ui.configurables.Commands.*;

public class ShopMenu {
    private static final String regex = "^[^\\s][a-zA-Z'\\d\\s]{1,}[^\\s]" + SHOP_SEPARATOR_KEY
            + "[^\\s][a-zA-Z\\d\\s]{1,}[^\\s]" + SHOP_SEPARATOR_KEY
            + "[1-9]\\d{0,}$";
    private static final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

    // EFFECTS: initiates a new shop called shopName
    public static Shop initShop(String shopName) throws IOException {
        Shop shop = new Shop(shopName);

        shop.stockWithRandomItems(2, 15);
        shop.stockWithRandomItems(3, 10);

        return shop;
    }

    // EFFECTS: opens the Shop menu of shop for player
    public static void openShopMenu(Shop shop, Player player) {
        System.out.println("\nWelcome to " + shop.getShopName() + ", " + player.getPlayerName() + "!"
                            + " You currently have $" + player.getMoney() + "!");
        showBuyables(shop, player);
    }

    // EFFECTS: displays all buyable items from the shop
    //          with their name, type, price, and quantity in stock
    private static void showBuyables(Shop shop, Player player) {
        printItems(shop);
        displayCommands();
        awaitCommands(shop, player);
    }

    // EFFECTS: displays all possible commands in the current menu
    private static void displayCommands() {
        System.out.println("\n- To view what's in-store, enter in '" + VIEW_ITEMS_KEY + "'.");
        System.out.println("- To check how much money you have, enter in '" + VIEW_MONEY_KEY + "'.");
        System.out.println("- To leave the shop, enter in '" + EXIT_MENU_KEY + "'.");
        System.out.println("- To buy something, enter in '" + BUY_ITEM_KEY + "' followed by the item name,"
                + "\nitem type, and the quantity you would like to purchase using '"
                + SHOP_SEPARATOR_KEY + "' as a separator."
                + "\nYour command should follow the format 'buy item name" + SHOP_SEPARATOR_KEY
                + "item type" + SHOP_SEPARATOR_KEY + "quantity'."
                + "\nEx. '" + BUY_ITEM_KEY + " Ball of Yarn" + SHOP_SEPARATOR_KEY + "Toy" + SHOP_SEPARATOR_KEY
                + "1', '" + BUY_ITEM_KEY + " chicken" + SHOP_SEPARATOR_KEY + "food" + SHOP_SEPARATOR_KEY + "99'");
    }

    // EFFECTS: awaits and handles user input for commands
    private static void awaitCommands(Shop shop, Player player) {
        boolean hasExited = false;

        while (!hasExited) {
            String command = scanner.nextLine();

            if (command.startsWith(BUY_ITEM_KEY + " ")) {
                command = command.replaceAll(BUY_ITEM_KEY + " ", "");

                if (command != null && pattern.matcher(command).find()) {
                    buyItems(command, shop, player);
                }
            } else {
                switch (command) {
                    case VIEW_ITEMS_KEY: printItems(shop);
                        break;
                    case VIEW_MONEY_KEY: InventoryMenu.checkMoney(player);
                        break;
                    case EXIT_MENU_KEY: System.out.println("You have left " + shop.getShopName() + ".");
                        hasExited = true;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    // REQUIRES: command has three clauses, separated by SEPARATOR_KEY,
    //           and of the format 'String''SEPARATOR_KEY''String''SEPARATOR_KEY''Integer'
    // EFFECTS: checks to see if buying the item is valid,
    //          then handles the player transaction if valid
    private static void buyItems(String command, Shop shop, Player player) {
        String[] extractedCommand = command.split(SHOP_SEPARATOR_KEY);
        String itemName = extractedCommand[0];
        String itemType = extractedCommand[1];
        int quantity = Integer.parseInt(extractedCommand[2]);
        Item itemToCheck = validItem(itemName, itemType, shop);

        if (itemToCheck != null) {
            if (player.buyItemFrom(itemToCheck, quantity, shop)) {
                System.out.println("You have successfully purchased "
                                    + itemToCheck.getName()
                                    + " (x" + quantity + ")!");
                System.out.println("Your remaining money is $" + player.getMoney() + "\n");
            } else {
                System.out.println("Transaction failed. You either don't have enough money,"
                                    + " or there is not enough of that item in stock.");
                System.out.println("Your currently have $" + player.getMoney() + "\n");
            }
        } else {
            System.out.println("Error! Cannot find item \"" + itemName + "\" of the type \""
                                + itemType + "\" in the shop!");
            System.out.println("Be sure to enter the item name and type correctly!\n");
        }
    }

    // EFFECTS: returns the item if there exists an item of the name itemName and type itemType
    private static Item validItem(String itemName, String itemType, Shop shop) {
        ArrayList<Item> shopItems = shop.getShopItems();

        for (int i = 0; i < shopItems.size(); i++) {
            Item item = shopItems.get(i);

            String comparingName = item.getName().toLowerCase();
            String comparingType = item.getType().toLowerCase();
            boolean foundItemName = (comparingName.equals(itemName.toLowerCase()));
            boolean foundItemType = (comparingType.equals(itemType.toLowerCase()));

            if (foundItemName && foundItemType) {
                return item;
            }
        }
        return null;
    }

    // EFFECTS: displays all items that can be purchased in shop
    private static void printItems(Shop shop) {
        System.out.println("\nHere's a list of items you can purchase at " + shop.getShopName() + ":");
        System.out.println("[ ITEM NAME || ITEM TYPE || PRICE || QUANTITY IN-STOCK ]");

        for (int i = 0; i < shop.getShopItems().size(); i++) {
            Item item = shop.getShopItems().get(i);
            String itemName = item.getName();
            String itemType = item.getType();
            int price = shop.getPriceOfItems().get(i);
            int quantityInStock = shop.getQuantityInStock().get(i);

            System.out.println("- " + itemName + " || "
                                    + itemType + " || $"
                                    + price + " || x"
                                    + quantityInStock);
        }
    }
}
