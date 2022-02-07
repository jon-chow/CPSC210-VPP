package ui.menus;

import model.Player;
import model.goodsandservices.Item;

import java.util.ArrayList;

public class InventoryMenu {
    // TODO: give option to give pet items from inventory
    // EFFECTS: displays the amount of money the player has
    public static void checkMoney(Player player) {
        System.out.println("You currently have $" + player.getMoney() + ".");
    }

    // EFFECTS: displays a list of items in the player's inventory
    public static void viewInventory(Player player) {
        ArrayList<Item> inventory = player.getInventory();
        int inventorySize = inventory.size();

        if (inventorySize != 0) {
            System.out.println("Here's a list of items in your inventory:");
            System.out.println("[ ITEM NAME || ITEM TYPE || QUANTITY ]");
            for (int i = 0; i < inventorySize; i++) {
                Item item = inventory.get(i);
                String itemName = item.getName();
                String itemType = item.getType();
                int quantity = player.getInventoryQuantity().get(i);

                System.out.println("- " + itemName + " || "
                        + itemType + " || x"
                        + quantity);
            }
        } else {
            System.out.println("[ Your inventory is currently empty... ]\n");
        }
    }
}
